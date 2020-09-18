package com.liminghua.apimanagement.controller;

import com.alibaba.fastjson.JSON;
import com.liminghua.apimanagement.dto.GroupWithJobList;
import com.liminghua.apimanagement.entity.Job;
import com.liminghua.apimanagement.entity.JobGroup;
import com.liminghua.apimanagement.mapper.JobGroupMapper;
import com.liminghua.apimanagement.mapper.JobMapper;
import com.liminghua.apimanagement.service.impl.JobServiceImpl;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName JobController
 * @Description: 定时任务相关请求分发
 * @Author LiMinghua
 * @Date 2020/9/9
 * @Version V1.0
 **/

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping(value = "/job")
@Slf4j
public class JobController {
    @Autowired
    JobServiceImpl jobService;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Autowired
    JobMapper jobMapper;
    @Autowired
    JobGroupMapper jobGroupMapper;

    /**
     * 获取所有任务
     *
     * @Description 获取所有任务
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/9/10 17:37
     */
    @RequestMapping(value = "query/all", method = RequestMethod.GET)
    public String getAllJob(){
        return jobService.getAllJob();
    }

    /**
     * 根据id获取任务
     *
     * @Description 根据id获取任务
     * @param req 请求体
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/9/10 17:37
     */
    @RequestMapping(value = "query/id", method = RequestMethod.GET)
    public String getJobById(@org.jetbrains.annotations.NotNull HttpServletRequest req){
        String id = req.getParameter("id");
        return jobService.getJobById(Integer.parseInt(id));
    }

    /**
     * 添加任务
     *
     * @Description 添加任务
     * @param job 任务实体
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/9/10 17:36
     */
    @RequestMapping(value = "insert/job", method = RequestMethod.POST)
    public String addJob(Job job){
        return String.valueOf(jobService.addJob(job));
    }

    /**
     * 更新任务
     *
     * @Description 更新任务
     * @param job 任务实体
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/9/10 17:35
     */
    @RequestMapping(value = "update/job", method = RequestMethod.POST)
    public String updateJob(Job job){
        return String.valueOf(jobService.updateJob(job));
    }

    /**
     * 获取所有任务组
     *
     * @Description 获取所有任务组
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/9/10 17:35
     */
    @RequestMapping(value = "query/group/all", method = RequestMethod.GET)
    public String getAllGroup(){
        return jobService.getAllGroup();
    }

    /**
     * 根据任务组获取任务
     *
     * @Description 根据任务组获取任务
     * @param id 任务组id
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/9/11 9:14
     */
    @RequestMapping("/query/job/group/{id}")
    public String getJobByGroupId(@PathVariable @NotNull Integer id){
        return jobService.getJobByGroupId(id);
    }

    @RequestMapping("/query/job/jobListByGroup")
    public String getGroupWithJobList(){
        List<GroupWithJobList>  rs= new ArrayList<GroupWithJobList>();
        List<JobGroup> groupList = jobGroupMapper.getAllGroup();
        for(JobGroup group:groupList){
            GroupWithJobList groupWithJobList = new GroupWithJobList();
            List<Job> jobList = jobMapper.getJobByGroupId(group.getId());
            groupWithJobList.setGroupId(group.getId());
            groupWithJobList.setGroupName(group.getName());
            groupWithJobList.setGroupIndex("2-"+group.getId());
            groupWithJobList.setJobList(jobList);
            rs.add(groupWithJobList);
        }
        return JSON.toJSONString(rs);
    }

    /**
     * 初始化启动所有的job
     *
     * @Description 初始化启动所有的job
     * @return: void
     * @auther: LiMinghua
     * @date: 2020/9/10 17:38
     */
    @PostConstruct
    public void initialize() {
        try {
            reStartAllJobs();
            log.info("init success");
        } catch (SchedulerException e) {
            log.error("printStackTrace ", e);
        }
    }

    /**
     * 重新启动所有的job
     *
     * @Description 重新启动所有的job
     * @return: void
     * @auther: LiMinghua
     * @date: 2020/9/10 17:38
     */
    private void reStartAllJobs() throws SchedulerException {
        //只允许一个线程进入操作
        synchronized (log) {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            Set<JobKey> set = scheduler.getJobKeys(GroupMatcher.anyGroup());
            //暂停所有JOB
            scheduler.pauseJobs(GroupMatcher.anyGroup());
            //删除从数据库中注册的所有JOB
            for (JobKey jobKey : set) {
                scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup()));
                scheduler.deleteJob(jobKey);
            }
            //从数据库中注册的所有JOB
            for (Job job : jobMapper.getAllJob()) {
                log.info("Job register name : {} , group : {} , cron : {}", job.getName(), job.getGroup().getName(), job.getCron());
                JobDataMap map = jobService.getJobDataMap(job);
                JobKey jobKey = jobService.getJobKey(job);
                JobDetail jobDetail = jobService.getJobDetail(jobKey, job.getDescription(), map);
                if ("OPEN".equals(job.getStatus())) {
                    scheduler.scheduleJob(jobDetail, jobService.getTrigger(job));
                } else {
                    log.info("Job jump name : {} , Because {} status is {}", job.getName(), job.getName(), job.getStatus());
                }
            }
        }
    }

    /**
     * 根据ID重启某个Job
     *
     * @Description 根据ID重启某个Job
     * @param id 主键
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/9/10 14:52
     */
    @RequestMapping("/refresh/{id}")
    public String refresh(@PathVariable @NotNull Integer id) throws SchedulerException {

        String result;
        //任务状态为开启时才重新注册
        String stuts = "OPEN";
        Job job = jobMapper.getJobById(id);
        if (Objects.isNull(job)) {
            return "error: id is not exist ";
        }
        synchronized (log) {
            JobKey jobKey = jobService.getJobKey(job);
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.pauseJob(jobKey);
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup()));
            scheduler.deleteJob(jobKey);
            JobDataMap map = jobService.getJobDataMap(job);
            JobDetail jobDetail = jobService.getJobDetail(jobKey, job.getDescription(), map);
            if (stuts.equals(job.getStatus())) {
                scheduler.scheduleJob(jobDetail, jobService.getTrigger(job));
                result = "Refresh Job : " + job.getName() + "\t jarPath: " + job.getJarPath() + " success !";
            } else {
                result = "Refresh Job : " + job.getName() + "\t jarPath: " + job.getJarPath() + " failed ! , " +
                        "Because the Job status is " + job.getStatus();
            }
        }
        return result;
    }

    /**
     * 重启数据库中所有的Job
     *
     * @Description 重启数据库中所有的Job
     * @param
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/9/10 14:55
     */
    @RequestMapping("/refresh/all")
    public String refreshAll() {

        String result = "";
        try {
            reStartAllJobs();
            result = "success";
        } catch (SchedulerException e) {
            result = "exception : " + e.getMessage();
        }
        return "refresh all jobs : " + result;
    }

    @RequestMapping("/pause/all")
    public String pauseAll() {
        String result = "";
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            Set<JobKey> set = scheduler.getJobKeys(GroupMatcher.anyGroup());
            //暂停所有JOB
            scheduler.pauseJobs(GroupMatcher.anyGroup());
            result = "success";
        } catch (SchedulerException e) {
            result = "exception : " + e.getMessage();
        }
        return "pause all jobs : " + result;
    }

    @RequestMapping("/pause/{id}")
    public String pause(@PathVariable @NotNull Integer id) {
        String result = "";
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey key = jobService.getJobKey(jobMapper.getJobById(id));
            scheduler.pauseJob(key);
            result = "success";
        } catch (SchedulerException e) {
            result = "exception : " + e.getMessage();
        }
        return "pause job id = "+id+": " + result;
    }

    @RequestMapping("/resume/{id}")
    public String resume(@PathVariable @NotNull Integer id) {
        String result = "";
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey key = jobService.getJobKey(jobMapper.getJobById(id));
            scheduler.resumeJob(key);
            result = "success";
        } catch (SchedulerException e) {
            result = "exception : " + e.getMessage();
        }
        return "resume job id = "+id+": " + result;
    }

    @RequestMapping("/resume/all")
    public String resumeAll() {
        String result = "";
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            Set<JobKey> set = scheduler.getJobKeys(GroupMatcher.anyGroup());
            //暂停所有JOB
            scheduler.resumeJobs(GroupMatcher.anyGroup());
            result = "success";
        } catch (SchedulerException e) {
            result = "exception : " + e.getMessage();
        }
        return "resume all jobs : " + result;
    }

}
