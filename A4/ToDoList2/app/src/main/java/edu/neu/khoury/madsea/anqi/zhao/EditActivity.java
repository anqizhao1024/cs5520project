package edu.neu.khoury.madsea.anqi.zhao;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {
    final static String pageTitle = "Edit Task";
    private EditText taskTitle;
    private EditText detail;
    private Spinner tags;
    private EditText dealTime;
    private CheckBox checkBox;
    private EditText times;
    private Button btnCancel;
    private Button btnEdit;

    final static String[] tagArr = {"please select a tag", "tag1", "tag2"};
    int selectTagIndex = 0;
    private TaskDatasource taskDatasource;
    Task task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(pageTitle);
        taskDatasource = new TaskDatasource(this);
        setContentView(R.layout.activity_edit);


        task = (Task) getIntent().getSerializableExtra("data");

        taskTitle = findViewById(R.id.title);
        taskTitle.setText(task.getTaskTitle());
        detail = findViewById(R.id.detail);
        detail.setText(task.getDetails());
        tags = findViewById(R.id.tags);
        dealTime = findViewById(R.id.dealTime);
        dealTime.setText(task.getDealLine());
        checkBox = findViewById(R.id.checkbox);
        if ("1".equals(task.getIsRemind())) {
            checkBox.setChecked(true);
        }
        times = findViewById(R.id.times);
        times.setText(task.getRemindTimes());
        btnCancel = findViewById(R.id.btnCancel);
        btnEdit = findViewById(R.id.btnEdit);

        ArrayAdapter simpleAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tagArr);
        tags.setAdapter(simpleAdapter);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleStr = taskTitle.getText().toString().trim();
                if (titleStr.equals("")) {
                    Toast.makeText(EditActivity.this, "please input task title", Toast.LENGTH_LONG).show();
                    return;
                }
                task.setTaskTitle(titleStr);
                task.setDetails(detail.getText().toString().trim());
                task.setDealLine(dealTime.getText().toString().trim());

                if (selectTagIndex == 0) {
                    Toast.makeText(EditActivity.this, "please select a tag", Toast.LENGTH_LONG).show();
                    return;
                }
                task.setTag(tagArr[selectTagIndex]);
                if (checkBox.isChecked()) {
                    task.setIsRemind("1");
                } else {
                    task.setIsRemind("0");
                }
                task.setRemindTimes(times.getText().toString().trim());

                if (TaskDao.editTask(taskDatasource, task)) {
                    Toast.makeText(EditActivity.this, "edit success", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(EditActivity.this, "edit error", Toast.LENGTH_LONG).show();
                }
            }

        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.this.finish();
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
