package com.liminghua.apimanagement.service;


import com.liminghua.apimanagement.entity.Job;
import com.liminghua.apimanagement.entity.JobGroup;

import java.util.List;

/**
 * @author LiMinghua
 */
public interface JobService {
    /**
     *  查找所有任务
     *
     * @Description 查找所有任务
     * @param: []
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/9/9 11:27
     */
    public String getAllJob();

    /**
     * 根据id查找任务
     *
     * @Description 根据id查找任务
     * @param id 主键
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/9/9 14:17
     */
    public String getJobById(int id);

    /**
     * 添加任务
     *
     * @Description 添加任务
     * @param job 任务实体
     * @return: int
     * @auther: LiMinghua
     * @date: 2020/9/10 10:10
     */
    public int addJob(Job job);

    /**
     * 更新任务
     *
     * @Description 更新任务
     * @param job 任务实体
     * @return: int
     * @auther: LiMinghua
     * @date: 2020/9/10 14:30
     */
    public int updateJob(Job job);

    /**
     * 获取所有任务组
     *
     * @Description 获取所有任务组
     * @return: List<JobGroup>
     * @auther: LiMinghua
     * @date: 2020/9/10 17:24
     */
    public String getAllGroup();

    /**
     * 根据任务组获取任务
     *
     * @Description 根据任务组获取任务
     * @param groupId 任务组主键
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/9/11 9:10
     */
    public String getJobByGroupId(int groupId);
}
