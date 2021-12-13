package edu.neu.khoury.madsea.anqi.zhao.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.neu.khoury.madsea.anqi.zhao.R;
import edu.neu.khoury.madsea.anqi.zhao.remind.NotificationManager;
import edu.neu.khoury.madsea.anqi.zhao.source.TaskAdapter;
import edu.neu.khoury.madsea.anqi.zhao.source.TaskDao;
import edu.neu.khoury.madsea.anqi.zhao.util.Contant;
import edu.neu.khoury.madsea.anqi.zhao.vo.Task;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_KEY_TODO_ID = "MainActivity";
    final static String pageTitle = "My Task";
    private ListView listView;
    private Button addBtn;
    private Button searchBtn;
    private EditText searchText;
    private List<Task> datas = new ArrayList<>();
    private TaskAdapter adapter;
    private Spinner tags;
    private Spinner sortTags;
    private String selectTag = "";
    private int selectSortIndex = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(pageTitle);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        addBtn = findViewById(R.id.addBtn);
        tags = findViewById(R.id.tags);
        sortTags = findViewById(R.id.sortTags);
        searchBtn = findViewById(R.id.searchBtn);
        searchText = findViewById(R.id.searchText);

        ArrayAdapter simpleAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Contant.selectTag);
        tags.setAdapter(simpleAdapter);
        ArrayAdapter sortAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Contant.sortBy);
        sortTags.setAdapter(sortAdapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchAction();
            }
        });

        adapter = new TaskAdapter(this, datas);
        listView.setAdapter(adapter);
        initData();


        tags.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    selectTag = Contant.selectTag[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sortTags.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectSortIndex = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        NotificationManager.createNotificationChannel(getApplicationContext());
    }


    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        datas.clear();
        List<Task> tasks = TaskDao.queryTask();

        for (Task t : tasks) {
            Log.e("test", t.toString());
        }


        datas.addAll(tasks);
        adapter.notifyDataSetChanged();
    }

    private void sort() {
        String searchTxt = searchText.getText().toString().trim();
        List<Task> tasks = TaskDao.searchByTaskTitleSortBy(searchTxt, selectTag);
        datas.clear();
        datas.addAll(tasks);
        adapter.notifyDataSetChanged();
    }

    private void sortByCreate() {
        String searchTxt = searchText.getText().toString().trim();
        List<Task> tasks = TaskDao.searchByTaskTitleSortByCreateTime(searchTxt, selectTag);
        datas.clear();
        datas.addAll(tasks);
        adapter.notifyDataSetChanged();
    }


    private void search() {
        String searchTxt = searchText.getText().toString().trim();
        List<Task> tasks = TaskDao.searchByTaskTitle(searchTxt, selectTag);
        datas.clear();
        datas.addAll(tasks);
        adapter.notifyDataSetChanged();

    }

    private void searchAction() {
        switch (selectSortIndex) {
            case 999:
                search();
                break;
            case 1:
                sort();
                break;
            case 2:
                sortByCreate();
                break;
        }
    }
}
