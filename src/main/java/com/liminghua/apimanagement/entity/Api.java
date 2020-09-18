package com.liminghua.apimanagement.entity;

/**
 * @ClassName Api
 * @Description: 接口实体
 * @Author LiMinghua
 * @Date 2020/6/8
 * @Version V1.0
 **/
public class Api {
    private int id;
    private String apiName;
    private String apiUrl;
    private int apiSwitch;
    private String apiDescription;
    private String apiMethod;
    private String apiClassName;
    private String apiType;
    private Tag apiTag;

    public Tag getApiTag() {
        return apiTag;
    }

    public void setApiTag(Tag apiTag) {
        this.apiTag = apiTag;
    }

    public String getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(String apiResponse) {
        this.apiResponse = apiResponse;
    }

    private String apiResponse;


    public int getApiSwitch() {
        return apiSwitch;
    }

    public void setApiSwitch(int apiSwitch) {
        this.apiSwitch = apiSwitch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiDescription() {
        return apiDescription;
    }

    public void setApiDescription(String apiDescription) {
        this.apiDescription = apiDescription;
    }

    public String getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(String apiMethod) {
        this.apiMethod = apiMethod;
    }

    public String getApiClassName() {
        return apiClassName;
    }

    public void setApiClassName(String apiClassName) {
        this.apiClassName = apiClassName;
    }

    public String getApiType() {
        return apiType;
    }

    public void setApiType(String apiType) {
        this.apiType = apiType;
    }

}
