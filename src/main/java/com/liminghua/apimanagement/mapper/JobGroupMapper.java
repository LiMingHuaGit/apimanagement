package com.liminghua.apimanagement.mapper;

import com.liminghua.apimanagement.entity.JobGroup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName JobGroupMapper
 * @Description: 任务组Mapper抽象类
 * @Author LiMinghua
 * @Date 2020/9/10
 * @Version V1.0
 **/
@Mapper
@Component
public interface JobGroupMapper {

    /**
     * 获取所有任务组
     *
     * @Description 获取所有任务组
     * @return: java.util.List<com.liminghua.apimanagement.entity.JobGroup>
     * @auther: LiMinghua
     * @date: 2020/9/10 17:19
     */
    public List<JobGroup> getAllGroup();
}
