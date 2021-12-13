package edu.neu.khoury.madsea.anqi.zhao.vo;


import org.litepal.crud.LitePalSupport;

import java.io.Serializable;


/**
 * task data
 */
public class Task extends LitePalSupport implements Serializable {
    private Long id;
    private String taskTitle;
    private String details;
    private String tag;
    private String dealLine;
    private String isRemind;
    private String remindTimes;
    private String createTime;

    public Task() {
    }

    public Task(Long id, String taskTitle, String details, String tag, String dealLine, String isRemind, String remindTimes, String createTime) {
        this.id = id;
        this.taskTitle = taskTitle;
        this.details = details;
        this.tag = tag;
        this.dealLine = dealLine;
        this.isRemind = isRemind;
        this.remindTimes = remindTimes;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDealLine() {
        return dealLine;
    }

    public void setDealLine(String dealLine) {
        this.dealLine = dealLine;
    }

    public String getIsRemind() {
        return isRemind;
    }

    public void setIsRemind(String isRemind) {
        this.isRemind = isRemind;
    }

    public String getRemindTimes() {
        return remindTimes;
    }

    public void setRemindTimes(String remindTimes) {
        this.remindTimes = remindTimes;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskTitle='" + taskTitle + '\'' +
                ", details='" + details + '\'' +
                ", tag='" + tag + '\'' +
                ", dealLine='" + dealLine + '\'' +
                ", isRemind='" + isRemind + '\'' +
                ", remindTimes='" + remindTimes + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
