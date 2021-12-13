package edu.neu.khoury.madsea.anqi.zhao.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.neu.khoury.madsea.anqi.zhao.R;
import edu.neu.khoury.madsea.anqi.zhao.remind.NotificationManager;
import edu.neu.khoury.madsea.anqi.zhao.source.TaskAdapter;
import edu.neu.khoury.madsea.anqi.zhao.source.TaskDao;
import edu.neu.khoury.madsea.anqi.zhao.source.TaskDatasource;
import edu.neu.khoury.madsea.anqi.zhao.vo.Task;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_KEY_TODO_ID = "MainActivity";
    final static String pageTitle = "My Task";
    private ListView listView;
    private Button addBtn;
    private List<Task> datas = new ArrayList<>();
    private TaskAdapter adapter;
    private TaskDatasource taskDatasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(pageTitle);
        setContentView(R.layout.activity_main);
        taskDatasource = new TaskDatasource(this);
        listView = findViewById(R.id.listView);
        addBtn = findViewById(R.id.addBtn);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "dsadsadsa", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(MainActivity.this, EditActivity.class);
//                intent.putExtra("data",datas.get(position));
//                startActivity(intent);
//            }
//        });

        adapter = new TaskAdapter(this, datas);
        listView.setAdapter(adapter);
        initData();

        NotificationManager.createNotificationChannel(getApplicationContext());
    }


    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        datas.clear();
        List<Task> tasks = TaskDao.queryTask(taskDatasource);
        datas.addAll(tasks);
        adapter.notifyDataSetChanged();
//        Task task1 = new Task("title1", "test detail1", "", "2021-11-05 22:22:10", "0", "2");
//        Task task2 = new Task("title2", "test detail2 test detail2 test detail2", "", "2021-11-05 22:22:10", "1", "2");
//        Task task3 = new Task("title3", "test detail3  33333333", "", "2021-11-05 22:22:10", "0", "2");
//        Task task4 = new Task("title4", "test detail4 4444444444444", "", "2021-11-05 22:22:10", "1", "2");
//
//        datas.add(task1);
//        datas.add(task2);
//        datas.add(task3);
//        datas.add(task4);
    }
}
