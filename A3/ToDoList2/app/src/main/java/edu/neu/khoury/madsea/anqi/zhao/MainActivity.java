package edu.neu.khoury.madsea.anqi.zhao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final static String pageTitle = "My Task";
    private ListView listView;
    private Button addBtn;
    private List<Task> datas = new ArrayList<>();
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(pageTitle);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        addBtn = findViewById(R.id.addBtn);

        adapter = new TaskAdapter(this, datas);
        listView.setAdapter(adapter);

        initData();
        adapter.notifyDataSetChanged();
    }


    private void initData() {
        Task task1 = new Task("title1", "test detail1", "", "2021-11-05 22:22:10", "0", "2");
        Task task2 = new Task("title2", "test detail2 test detail2 test detail2", "", "2021-11-05 22:22:10", "1", "2");
        Task task3 = new Task("title3", "test detail3  33333333", "", "2021-11-05 22:22:10", "0", "2");
        Task task4 = new Task("title4", "test detail4 4444444444444", "", "2021-11-05 22:22:10", "1", "2");

        datas.add(task1);
        datas.add(task2);
        datas.add(task3);
        datas.add(task4);
    }
}
