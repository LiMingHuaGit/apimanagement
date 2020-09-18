package com.liminghua.apimanagement.entity;

/**
 * @ClassName Job
 * @Description: 定时任务实体类
 * @Author LiMinghua
 * @Date 2020/9/9
 * @Version V1.0
 **/
public class Job {
    /**
     * 主键
     */
    private int id;
    /**
     * job名称
     */
    private String name;
    /**
     * job分组
     */
    private JobGroup group;
    /**
     * 表达式
     */
    private String cron;
    /**
     * 任务元数据
     */
    private String parameter;
    /**
     * job描述
     */
    private String description;
    /**
     * vm参数
     */
    private String vmParam;
    /**
     * jar包路径
     */
    private String jarPath;
    /**
     * 任务状态：OPEN/CLOSE
     */
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JobGroup getGroup() {
        return group;
    }

    public void setGroup(JobGroup group) {
        this.group = group;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVmParam() {
        return vmParam;
    }

    public void setVmParam(String vmParam) {
        this.vmParam = vmParam;
    }

    public String getJarPath() {
        return jarPath;
    }

    public void setJarPath(String jarPath) {
        this.jarPath = jarPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
