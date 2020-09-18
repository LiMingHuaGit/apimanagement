package com.liminghua.apimanagement.dto;

import com.liminghua.apimanagement.entity.Job;

import java.util.List;

/**
 * @ClassName GroupWithJob
 * @Description: 按任务组分类的JobList
 * @Author LiMinghua
 * @Date 2020/9/11
 * @Version V1.0
 **/
public class GroupWithJobList {
    public int groupId;
    public String groupIndex;
    public String groupName;
    public List<Job> jobList;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(String groupIndex) {
        this.groupIndex = groupIndex;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }
}
