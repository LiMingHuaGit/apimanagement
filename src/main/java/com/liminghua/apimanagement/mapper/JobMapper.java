package com.liminghua.apimanagement.mapper;

import com.liminghua.apimanagement.entity.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName JobMapper
 * @Description: Mapper抽象类
 * @Author LiMinghua
 * @Date 2020/9/9
 * @Version V1.0
 **/

@Mapper
@Component
public interface JobMapper {

    /**
     * 获取所有job
     *
     * @Description 获取所有job
     * @param: []
     * @return: java.util.List<com.liminghua.apimanagement.entity.Job>
     * @auther: LiMinghua
     * @date: 2020/9/9 11:28
     */
    public List<Job> getAllJob();

    /**
     * 根据id获取job
     *
     * @Description 根据id获取job
     * @param id 主键
     * @return: com.liminghua.apimanagement.entity.Job
     * @auther: LiMinghua
     * @date: 2020/9/9 14:05
     */
    public Job getJobById(int id);

    /**
     * 添加任务
     *
     * @Description 添加任务
     * @param job 任务实体
     * @return: int
     * @auther: LiMinghua
     * @date: 2020/9/10 10:10
     */
    public int addJob(@Param("Job")Job job);

    /**
     * 更新任务
     *
     * @Description 更新任务
     * @param job 任务实体
     * @return: int
     * @auther: LiMinghua
     * @date: 2020/9/10 11:54
     */
    public int updateJob(@Param("Job")Job job);

    /**
     * 根据任务组获取任务
     *
     * @Description 根据任务组获取任务
     * @param groupId 任务组id
     * @return: java.util.List<com.liminghua.apimanagement.entity.Job>
     * @auther: LiMinghua
     * @date: 2020/9/11 9:07
     */
    public List<Job> getJobByGroupId(int groupId);
}
