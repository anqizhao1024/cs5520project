package edu.neu.khoury.madsea.anqi.zhao;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    final static String pageTitle = "New Task";
    private EditText taskTitle;
    private EditText detail;
    private Spinner tags;
    private EditText dealTime;
    private CheckBox checkBox;
    private EditText times;
    private Button btnCancel;
    private Button btnSave;

    final static String[] tagArr = {"please select a tag", "tag1", "tag2"};
    int selectTagIndex = 0;
    private TaskDatasource taskDatasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(pageTitle);
        taskDatasource = new TaskDatasource(this);
        setContentView(R.layout.activity_add);

        taskTitle = findViewById(R.id.title);
        detail = findViewById(R.id.detail);
        tags = findViewById(R.id.tags);
        dealTime = findViewById(R.id.dealTime);
        checkBox = findViewById(R.id.checkbox);
        times = findViewById(R.id.times);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

        ArrayAdapter simpleAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tagArr);
        tags.setAdapter(simpleAdapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();
                String titleStr = taskTitle.getText().toString().trim();
                if (titleStr.equals("")) {
                    Toast.makeText(AddActivity.this, "please input task title", Toast.LENGTH_LONG).show();
                    return;
                }
                task.setTaskTitle(titleStr);
                task.setDetails(detail.getText().toString().trim());
                task.setDealLine(dealTime.getText().toString().trim());

                if (selectTagIndex == 0) {
                    Toast.makeText(AddActivity.this, "please select a tag", Toast.LENGTH_LONG).show();
                    return;
                }
                task.setTag(tagArr[selectTagIndex]);
                if (checkBox.isChecked()) {
                    task.setIsRemind("1");
                } else {
                    task.setIsRemind("0");
                }
                task.setRemindTimes(times.getText().toString().trim());

                if (TaskDao.addTask(taskDatasource, task)) {
                    Toast.makeText(AddActivity.this, "add success", Toast.LENGTH_LONG).show();
                    AddActivity.this.finish();
                } else {
                    Toast.makeText(AddActivity.this, "add error", Toast.LENGTH_LONG).show();
                }
            }

        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddActivity.this.finish();
            }
        });

        tags.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectTagIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
