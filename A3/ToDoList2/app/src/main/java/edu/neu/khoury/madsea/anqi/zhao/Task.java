package edu.neu.khoury.madsea.anqi.zhao;


/**
 * task data
 */
public class Task {
    private String taskTitle;
    private String details;
    private String tag;
    private String deadLine;
    private String isRemind;
    private String remindTimes;


    public Task(String taskTitle, String details, String tag, String deadLine, String isRemind, String remindTimes) {
        this.taskTitle = taskTitle;
        this.details = details;
        this.tag = tag;
        this.deadLine = deadLine;
        this.isRemind = isRemind;
        this.remindTimes = remindTimes;
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

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
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
