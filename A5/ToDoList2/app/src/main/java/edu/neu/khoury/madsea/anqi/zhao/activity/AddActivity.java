package edu.neu.khoury.madsea.anqi.zhao.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.neu.khoury.madsea.anqi.zhao.R;
import edu.neu.khoury.madsea.anqi.zhao.remind.AlertReceiver;
import edu.neu.khoury.madsea.anqi.zhao.source.TaskDao;
import edu.neu.khoury.madsea.anqi.zhao.source.TaskDatasource;
import edu.neu.khoury.madsea.anqi.zhao.vo.Task;

public class AddActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener {
    final static String pageTitle = "New Task";
    private EditText taskTitle;
    private EditText detail;
    private Spinner tags;
    private EditText dealTime;
    private CheckBox checkBox;
    private EditText times;
    private Button btnCancel;
    private Button btnSave;
    private DatePicker datePicker;
    private TimePicker timePicker;


    final static String[] tagArr = {"please select a tag", "tag1", "tag2"};
    int selectTagIndex = 0;
    private TaskDatasource taskDatasource;


    private int Year;
    private int month;
    private int day;
    private int hour;
    private int min;
    private int sound = 0;

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
        datePicker = findViewById(R.id.date);
        timePicker = findViewById(R.id.time);


        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dealTime.setText(sdf.format(new Date()));
        Calendar c = Calendar.getInstance();
        Year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE);
        sound = c.get(Calendar.SECOND);


        datePicker.init(Year, month, day, this);
        timePicker.setIs24HourView(true);

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

                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, Year);
                c.set(Calendar.MONTH, month - 1);
                c.set(Calendar.DAY_OF_MONTH, day);
                c.set(Calendar.HOUR_OF_DAY, hour);
                c.set(Calendar.MINUTE, min);
//                c.set(Calendar.SECOND, sound);

                //start alarm
                startAlarm(c,task);

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

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                min = minute;
                if (minute < 10) {
                    hour = hourOfDay;
                    min = minute;
                    dealTime.setText(Year + "-" + month + "-" + day + " " + hour + ":" + min);
                } else {
                    dealTime.setText(Year + "-" + month + "-" + day + " " + hour + ":" + min);
                }
            }
        });
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        month = monthOfYear + 1;
        Year = year;
        day = dayOfMonth;
        dealTime.setText(Year + "-" + month + "-" + day + " " + hour + ":" + min);
    }

    private void startAlarm(Calendar c, Task task) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        intent.putExtra("data", task);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }
}
