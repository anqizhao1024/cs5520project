package edu.neu.khoury.madsea.anqi.zhao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.List;

public class TaskAdapter extends BaseAdapter {

    Context context;
    List<Task> datas;
    LayoutInflater layoutInflater;

    public TaskAdapter(Context context, List<Task> datas) {
        this.context = context;
        this.datas = datas;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holdView;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_task, null);
            holdView = new ViewHolder();
            holdView.checkBox = convertView.findViewById(R.id.checkbox);
            holdView.title = convertView.findViewById(R.id.title);
            holdView.detail = convertView.findViewById(R.id.detail);
            convertView.setTag(holdView);
        } else {
            holdView = (ViewHolder) convertView.getTag();
        }
        Task item = datas.get(position);
        if (item.getIsRemind().equals("1")) {
            holdView.checkBox.setChecked(true);
        }
        holdView.title.setText(item.getTaskTitle());
        holdView.detail.setText(item.getDetails());
        return convertView;
    }
}


