package com.liminghua.apimanagement.entity;

/**
 * @ClassName Group
 * @Description: TODO
 * @Author LiMinghua
 * @Date 2020/9/9
 * @Version V1.0
 **/
public class JobGroup {
    /**
     * 主键
     */
    private int id;
    /**
     * 任务分组名称
     */
    private String name;
    /**
     * 分组描述
     */
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
