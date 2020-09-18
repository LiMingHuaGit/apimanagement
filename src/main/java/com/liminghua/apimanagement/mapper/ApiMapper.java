package com.liminghua.apimanagement.mapper;

import com.liminghua.apimanagement.entity.Api;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ApiMapper
 * @author LiMinghua
 * @date: 2020/6/8 10:14
 */
@Mapper
@Component
public interface ApiMapper {

    /**
     * 获取所有接口信息
     * @return: java.util.List<com.liminghua.apimanagement.entity.Api>
     * @auther: LiMinghua
     * @date: 2020/6/8 10:15
     */
    public List<Api> getAllInterface();


    /**
     * 更新标识字段控制接口开关
     *
     * @Description //更新操作，修改接口表中的标识字段-apiSwitch
     * @param apiSwitch  接口开关（T、F）
     * @param apiId  接口id（int）
     * @return: java.lang.Integer
     * @auther: LiMinghua
     * @date: 2020/6/8 14:06
     */
    public Integer changeSwitch(int apiSwitch,int apiId);

    /**
     * 根据接口请求类型查找接口列表
     *
     * @Description //根据接口请求类型查找接口列表
     * @param apiType 接口请求类型
     * @return: java.util.List<com.liminghua.apimanagement.entity.Api>
     * @auther: LiMinghua
     * @date: 2020/8/14 10:45
     */
    public List<Api> getInterfaceByType(String apiType);

    /**
     * 根据id获取接口详细数据
     *
     * @Description //根据id获取接口详细数据
     * @param id 主档id
     * @return: java.util.List<com.liminghua.apimanagement.entity.Api>
     * @auther: LiMinghua
     * @date: 2020/8/14 15:35
     */
    public Api getInterfaceById(Integer id);

    /**
     * 更新接口信息
     *
     * @Description //更新接口信息
     * @param api 接口实体
     * @return: java.lang.Integer
     * @auther: LiMinghua
     * @date: 2020/8/17 14:37
     */
    public Integer updateApiById(@Param("Api") Api api);

    /**
     *根据id删除接口
     *
     * @Description //根据id删除接口
     * @param id 主键id
     * @return: java.lang.Integer
     * @auther: LiMinghua
     * @date: 2020/8/18 14:24
     */
    public Integer deleteApiById(Integer id);
}
