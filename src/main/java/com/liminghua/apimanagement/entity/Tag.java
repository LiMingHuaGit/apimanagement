package com.liminghua.apimanagement.entity;

import com.alibaba.druid.sql.visitor.functions.Substring;

/**
 * @ClassName Tag
 * @Description: 标签实体
 * @Author LiMinghua
 * @Date 2020/8/18
 * @Version V1.0
 **/
public class Tag {
    private int id;
    private String tagName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }


}
