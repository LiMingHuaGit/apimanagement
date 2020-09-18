package com.liminghua.apimanagement.controller;
import com.liminghua.apimanagement.entity.Api;
import com.liminghua.apimanagement.service.impl.ApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName ApiController
 * @Description: 接口请求控制器
 * @Author LiMinghua
 * @Date 2020/8/12
 * @Version V1.0
 **/

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping(value = "/api")
public class ApiController {
    @Autowired
    ApiServiceImpl apiService;

    /**
     * 获取所有接口
     *
     * @Description //获取所有接口
     * @param: []
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/8/14 15:39
     */
    @RequestMapping(value = "query/all", method = RequestMethod.GET)
    public String getAllInterface(){
        return apiService.getAllInterface();
    }

    /**
     * 根据请求类型获取接口
     *
     * @Description //根据请求类型获取接口
     * @param req apiType
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/8/14 15:40
     */
    @RequestMapping(value = "query/apiType", method = RequestMethod.GET)
    public String getInterfaceByType(HttpServletRequest req){
        String apiType = req.getParameter("apiType");
        return apiService.getInterfaceByType(apiType);
    }

    /**
     * 接口开关
     *
     * @Description //接口开关
     * @param apiSwitch  接口开关
     * @param apiId 接口id
     * @return: int
     * @auther: LiMinghua
     * @date: 2020/8/14 15:41
     */
    @RequestMapping(value = "update/apiSwitch", method = RequestMethod.POST)
    public int changeSwitch(HttpServletResponse response,
                                  @RequestParam(value = "apiSwitch", required = true) String apiSwitch,
                                  @RequestParam(value = "apiId", required = true) String apiId){
        System.out.println(apiSwitch);
        System.out.println(apiId);
        return apiService.changeSwitch(Integer.parseInt(apiSwitch),Integer.parseInt(apiId));
    }

    /**
     * 根据id查接口信息
     *
     * @Description //根据id查接口信息
     * @param req 主档id
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/8/14 15:43
     */
    @RequestMapping(value = "query/id", method = RequestMethod.GET)
    public String getInterfaceById(HttpServletRequest req){
        String id = req.getParameter("id");
        return apiService.getInterfaceById(Integer.parseInt(id));
    }

    /**
     *更新接口信息
     *
     * @Description //更新接口信息
     * @param api 接口实体
     * @return: java.lang.Integer
     * @auther: LiMinghua
     * @date: 2020/8/17 15:37
     */
    @RequestMapping(value = "update/updateApiById", method = RequestMethod.POST)
    public int updateApiById(Api api){
        return apiService.updateApiById(api);
    }

    /**
     * 根据id删除接口
     * @Description //根据id删除接口
     * @param params 前台传入的json数据
     * @return: int
     * @auther: LiMinghua
     * @date: 2020/8/18 14:32
     */
    @RequestMapping(value = "delete/deleteApiById", method = RequestMethod.POST)
    public int deleteApiById(@RequestBody Map params){
        return apiService.deleteApiById((Integer) params.get("id"));
    }
}
