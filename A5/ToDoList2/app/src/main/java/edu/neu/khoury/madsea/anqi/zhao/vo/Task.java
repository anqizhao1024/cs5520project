package edu.neu.khoury.madsea.anqi.zhao.vo;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * task data
 */
public class Task implements Serializable {
    private int _id;
    private String taskTitle;
    private String details;
    private String tag;
    private String dealLine;
    private String isRemind;
    private String remindTimes;

    public Task() {
    }

    public Task(int _id, String taskTitle, String details, String tag, String dealLine, String isRemind, String remindTimes) {
        this._id = _id;
        this.taskTitle = taskTitle;
        this.details = details;
        this.tag = tag;
        this.dealLine = dealLine;
        this.isRemind = isRemind;
        this.remindTimes = remindTimes;
    }

    public Task(String taskTitle, String details, String tag, String dealLine, String isRemind, String remindTimes) {
        this.taskTitle = taskTitle;
        this.details = details;
        this.tag = tag;
        this.dealLine = dealLine;
        this.isRemind = isRemind;
        this.remindTimes = remindTimes;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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
}
