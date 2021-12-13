package edu.neu.khoury.madsea.anqi.zhao.source;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.List;

import edu.neu.khoury.madsea.anqi.zhao.R;
import edu.neu.khoury.madsea.anqi.zhao.activity.EditActivity;
import edu.neu.khoury.madsea.anqi.zhao.vo.Task;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holdView;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_todo, null);
            holdView = new ViewHolder();
            holdView.checkBox = convertView.findViewById(R.id.checkbox);
            holdView.title = convertView.findViewById(R.id.title);
            holdView.detail = convertView.findViewById(R.id.detail);
            holdView.linearLayout = convertView.findViewById(R.id.linearLayout);
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


        holdView.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("data", datas.get(position));
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}


