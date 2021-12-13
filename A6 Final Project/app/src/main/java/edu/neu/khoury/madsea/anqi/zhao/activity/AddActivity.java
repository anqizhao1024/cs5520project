package edu.neu.khoury.madsea.anqi.zhao.activity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.neu.khoury.madsea.anqi.zhao.R;
import edu.neu.khoury.madsea.anqi.zhao.remind.AlertReceiver;
import edu.neu.khoury.madsea.anqi.zhao.source.TaskDao;
import edu.neu.khoury.madsea.anqi.zhao.util.Contant;
import edu.neu.khoury.madsea.anqi.zhao.vo.Task;

public class AddActivity extends AppCompatActivity {
    final static String pageTitle = "New Task";
    private EditText taskTitle;
    private EditText detail;
    private Spinner tags;
    private TextView dateTimeText;
    private TextView timeTimeText;
    private TextView tag;
    private CheckBox checkBox;
    private EditText times;
    private Button btnCancel;
    private Button btnSave;
    private Button dateBtn;
    private Button timeBtn;

    private int year, month, day, hour, minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(pageTitle);
        setContentView(R.layout.activity_add);
        initDateTime();
        taskTitle = findViewById(R.id.title);
        detail = findViewById(R.id.detail);
        tags = findViewById(R.id.tags);
        dateTimeText = findViewById(R.id.dateTimeText);
        timeTimeText = findViewById(R.id.timeTimeText);
        tag = findViewById(R.id.tag);
        checkBox = findViewById(R.id.checkbox);
        times = findViewById(R.id.times);
        dateBtn = findViewById(R.id.dateBtn);
        timeBtn = findViewById(R.id.timeBtn);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        dateTimeText.setText(sdf1.format(new Date()));
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        timeTimeText.setText(sdf2.format(new Date()));

        ArrayAdapter simpleAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Contant.tagArr);
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

                String dateStr = dateTimeText.getText().toString().trim();
                String timeStr = timeTimeText.getText().toString().trim();

                if (dateStr.equals("")) {
                    Toast.makeText(AddActivity.this, "please select a date", Toast.LENGTH_LONG).show();
                    return;
                }
                if (timeStr.equals("")) {
                    Toast.makeText(AddActivity.this, "please select a time", Toast.LENGTH_LONG).show();
                    return;
                }
                task.setDealLine(dateStr + " " + timeStr);
                task.setTag(tag.getText().toString().trim());
                if (checkBox.isChecked()) {
                    task.setIsRemind("1");
                } else {
                    task.setIsRemind("0");
                }
                task.setRemindTimes(times.getText().toString().trim());
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month - 1);
                c.set(Calendar.DAY_OF_MONTH, day);
                c.set(Calendar.HOUR_OF_DAY, hour);
                c.set(Calendar.MINUTE, minute);
                //start alarm
                startAlarm(c, task);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                task.setCreateTime(simpleDateFormat.format(new Date()));


                if (TaskDao.addTask(task)) {
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
                tag.setText(Contant.tagArr[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //select date
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int tempYear, int tempMonthOfYear, int dayOfMonth) {
                        int temp = tempMonthOfYear + 1;
                        String monthStr = String.valueOf(temp);
                        dateTimeText.setText(tempYear + "-" + monthStr + "-" + dayOfMonth);
                        year = tempYear;
                        month = tempMonthOfYear;
                        day = dayOfMonth;
                    }
                }, year, month - 1, day).show();
            }
        });

        //select time
        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AddActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int tempHourOfDay, int tempMinute) {
                        String tempTime = tempHourOfDay + ":" + minute;
                        hour = tempHourOfDay;
                        minute = tempMinute;
                        timeTimeText.setText(tempTime);
                    }
                }, hour, minute, true).show();
            }
        });

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


    /**
     * init date time
     */
    private void initDateTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);


        Log.e("test", year + "-" + month + "-" + day + "  " + hour + ":" + minute);
    }
}
