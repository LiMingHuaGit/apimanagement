package com.liminghua.apimanagement.service;

import com.liminghua.apimanagement.entity.Api;

import java.util.List;

/**
 * ApiService
 * @author LiMinghua
 */
public interface ApiService {
    /**
     * 获取所有接口信息
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/6/8 10:38
     */
    public String getAllInterface();

    /**
     * 根据接口请求类型查找接口列表
     *
     * @Description 根据接口请求类型查找接口列表
     * @param apiType 接口类型
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/8/14 10:49
     */
    public String getInterfaceByType(String apiType);

    /**
     * 更新标识字段控制接口开关
     *
     * @Description //更新操作，修改接口表中的标识字段-apiSwitch
     * @param apiSwitch 接口开关（T、F）
     * @param apiId 接口id（int）
     * @return: java.lang.Integer
     * @auther: LiMinghua
     * @date: 2020/6/8 14:06
     */
    public Integer changeSwitch(int apiSwitch,int apiId);


    /**
     * 根据id获取接口详细数据
     *
     * @Description //根据id获取接口详细数据
     * @param id 主档id
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/8/14 15:33
     */
    public String getInterfaceById(Integer id);

    /**
     *更新接口信息
     *
     * @Description //更新接口信息
     * @param api 接口实体
     * @return: java.lang.Integer
     * @auther: LiMinghua
     * @date: 2020/8/17 15:37
     */
    public Integer updateApiById(Api api);

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
