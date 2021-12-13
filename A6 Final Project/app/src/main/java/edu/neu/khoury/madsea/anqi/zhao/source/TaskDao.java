package edu.neu.khoury.madsea.anqi.zhao.source;

import android.icu.util.TaiwanCalendar;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import edu.neu.khoury.madsea.anqi.zhao.vo.Task;

public class TaskDao {
    /**
     * add a task to sqlite
     *
     * @param task
     * @return
     */
    public static boolean addTask(Task task) {
        return task.save();
    }

    /**
     * edit task
     *
     * @param task
     * @return
     */
    public static boolean editTask(Task task) {
        return task.update(task.getId()) > 0;
    }

    /**
     * delete a task by task id
     *
     * @param
     * @return
     */
    public static boolean deleteTask(Task task) {
        return LitePal.delete(Task.class, task.getId()) > 0;
    }


    /**
     * query all task
     *
     * @return
     */
    public static List<Task> queryTask() {
        List<Task> list = LitePal.order("id desc").find(Task.class);
        return list;
    }

    public static List<Task> queryTaskSort() {
        List<Task> list = LitePal.order("dealLine desc").find(Task.class);
        return list;
    }

    public static List<Task> searchByTaskTitle(String searchTxt, String selectTag) {
        List<Task> list = new ArrayList<>();
        list = LitePal.where("taskTitle like ?", "%" + searchTxt + "%").find(Task.class);
        if (!selectTag.equals("")) {
            list = LitePal.where("taskTitle like ? and tag = ?", "%" + searchTxt + "%", selectTag).find(Task.class);
        }
        return list;
    }

    public static List<Task> searchByTaskTitleSortBy(String searchTxt, String selectTag) {
        List<Task> list = new ArrayList<>();
        list = LitePal.where("taskTitle like ?", "%" + searchTxt + "%").order("dealLine desc").find(Task.class);
        if (!selectTag.equals("")) {
            list = LitePal.where("taskTitle like ? and tag = ?", "%" + searchTxt + "%", selectTag).order("dealLine desc").find(Task.class);
        }
        return list;
    }

    public static List<Task> searchByTaskTitleSortByCreateTime(String searchTxt,String selectTag) {
        List<Task> list = new ArrayList<>();
        list = LitePal.where("taskTitle like ?", "%" + searchTxt + "%").order("createTime ASC").find(Task.class);
        if (!selectTag.equals("")) {
            list = LitePal.where("taskTitle like ? and tag = ?", "%" + searchTxt + "%", selectTag).order("createTime ASC").find(Task.class);
        }
        return list;
    }
}
