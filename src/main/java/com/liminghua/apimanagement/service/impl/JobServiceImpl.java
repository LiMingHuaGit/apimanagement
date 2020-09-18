package com.liminghua.apimanagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.liminghua.apimanagement.entity.Job;
import com.liminghua.apimanagement.entity.JobGroup;
import com.liminghua.apimanagement.mapper.JobGroupMapper;
import com.liminghua.apimanagement.mapper.JobMapper;
import com.liminghua.apimanagement.quartz.job.DynamicJob;
import com.liminghua.apimanagement.service.JobService;
import org.jetbrains.annotations.NotNull;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName JobServiceImpl
 * @Description: Service实现类
 * @Author LiMinghua
 * @Date 2020/9/9
 * @Version V1.0
 **/
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobMapper jobMapper;
    @Autowired
    JobGroupMapper jobGroupMapper;

    @Override
    public String getAllJob() {
        return JSON.toJSONString(jobMapper.getAllJob());
    }

    @Override
    public String getJobById(int id) {
        return JSON.toJSONString(jobMapper.getJobById(id));
    }

    @Override
    public int addJob(Job job) {
        return jobMapper.addJob(job);
    }

    @Override
    public int updateJob(Job job) {
        return jobMapper.updateJob(job);
    }

    @Override
    public String getAllGroup() {
        return JSON.toJSONString(jobGroupMapper.getAllGroup());
    }

    @Override
    public String getJobByGroupId(int groupId) {
        return JSON.toJSONString(jobMapper.getJobByGroupId(groupId));
    }

    /**
     * 获取JobDataMap.(Job参数对象)
     *
     * @Description 获取JobDataMap.(Job参数对象)
     * @param job 任务实体
     * @return: org.quartz.JobDataMap
     * @auther: LiMinghua
     * @date: 2020/9/9 17:26
     */
    public JobDataMap getJobDataMap(@NotNull Job job) {

        JobDataMap map = new JobDataMap();
        map.put("name", job.getName());
        map.put("jobGroup", job.getGroup().getName());
        map.put("cronExpression", job.getCron());
        map.put("parameter", job.getParameter());
        map.put("jobDescription", job.getDescription());
        map.put("vmParam", job.getVmParam());
        map.put("jarPath", job.getJarPath());
        map.put("status", job.getStatus());
        return map;
    }

    /**
     * 获取JobDetail,JobDetail是任务的定义,而Job是任务的执行逻辑,JobDetail里会引用一个Job Class来定义
     *
     * @Description 获取JobDetail,JobDetail是任务的定义,而Job是任务的执行逻辑,JobDetail里会引用一个Job Class来定义
     * @param jobKey JobKey
     * @param description 描述
     * @param map 元数据
     * @return: org.quartz.JobDetail
     * @auther: LiMinghua
     * @date: 2020/9/9 17:33
     */
    public JobDetail getJobDetail(JobKey jobKey, String description, JobDataMap map) {

        return JobBuilder.newJob(DynamicJob.class)
                .withIdentity(jobKey)
                .withDescription(description)
                .setJobData(map)
                .storeDurably()
                .build();
    }

    /**
     * 获取Trigger (Job的触发器,执行规则)
     *
     * @Description 获取Trigger (Job的触发器,执行规则)
     * @param job 任务实体
     * @return: org.quartz.Trigger
     * @auther: LiMinghua
     * @date: 2020/9/9 17:36
     */
    public Trigger getTrigger(@NotNull Job job) {

        return TriggerBuilder.newTrigger()
                .withIdentity(job.getName(), job.getGroup().getName())
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()))
                .build();
    }

    /**
     * 获取JobKey,包含Name和Group
     *
     * @Description 获取JobKey,包含Name和Group
     * @param job 任务实体
     * @return: org.quartz.JobKey
     * @auther: LiMinghua
     * @date: 2020/9/9 17:37
     */
    public JobKey getJobKey(@NotNull Job job) {
        return JobKey.jobKey(job.getName(), job.getGroup().getName());
    }
}
