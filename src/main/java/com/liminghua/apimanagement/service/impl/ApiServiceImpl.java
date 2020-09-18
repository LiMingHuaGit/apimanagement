package com.liminghua.apimanagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.liminghua.apimanagement.entity.Api;
import com.liminghua.apimanagement.mapper.ApiMapper;
import com.liminghua.apimanagement.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ApiServiceImpl
 * @ClassName ApiServiceImpl
 * @Author LiMinghua
 * @Date 2020/6/8
 * @Version V1.0
 **/
@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    ApiMapper apiMapper;

    @Override
    public String getAllInterface() {
        return JSON.toJSONString(apiMapper.getAllInterface());
    }

    @Override
    public String getInterfaceByType(String apiType) {
        return JSON.toJSONString(apiMapper.getInterfaceByType(apiType));
    }

    @Override
    public Integer changeSwitch(int apiSwitch, int apiId) {
        return apiMapper.changeSwitch(apiSwitch,apiId);
    }

    @Override
    public String getInterfaceById(Integer id) {
        return JSON.toJSONString(apiMapper.getInterfaceById(id));
    }

    @Override
    public Integer updateApiById(Api api) {
        return apiMapper.updateApiById(api);
    }

    @Override
    public Integer deleteApiById(Integer id) {
        return apiMapper.deleteApiById(id);
    }

}

