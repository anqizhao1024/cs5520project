package edu.neu.khoury.madsea.anqi.zhao.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

import edu.neu.khoury.madsea.anqi.zhao.R;
import edu.neu.khoury.madsea.anqi.zhao.source.TaskDao;
import edu.neu.khoury.madsea.anqi.zhao.util.Contant;
import edu.neu.khoury.madsea.anqi.zhao.vo.Task;

public class EditActivity extends AppCompatActivity {
    final static String pageTitle = "Edit Task";
    private EditText taskTitle;
    private EditText detail;
    private Spinner tags;
    private CheckBox checkBox;
    private EditText times;
    private Button btnCancel;
    private Button btnEdit;
    private Button btnDelete;

    private TextView dateTimeText;
    private TextView timeTimeText;
    private TextView tag;
    private Button dateBtn;
    private Button timeBtn;


    Task task = null;


    private int year, month, day, hour, minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(pageTitle);
        setContentView(R.layout.activity_edit);
        initDateTime();

        task = (Task) getIntent().getSerializableExtra("data");
        dateBtn = findViewById(R.id.dateBtn);
        timeBtn = findViewById(R.id.timeBtn);
        taskTitle = findViewById(R.id.title);
        dateTimeText = findViewById(R.id.dateTimeText);
        timeTimeText = findViewById(R.id.timeTimeText);
        tag = findViewById(R.id.tag);

        taskTitle.setText(task.getTaskTitle());
        detail = findViewById(R.id.detail);
        detail.setText(task.getDetails());
        tags = findViewById(R.id.tags);
        checkBox = findViewById(R.id.checkbox);
        if ("1".equals(task.getIsRemind())) {
            checkBox.setChecked(true);
        }
        times = findViewById(R.id.times);
        times.setText(task.getRemindTimes());
        btnCancel = findViewById(R.id.btnCancel);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);


        String dealLine = task.getDealLine();
        String dealArr[] = dealLine.split(" ");
        dateTimeText.setText(dealArr[0]);
        timeTimeText.setText(dealArr[1]);

        ArrayAdapter simpleAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Contant.tagArr);
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
                task.setTag(tag.getText().toString().trim());

                String dateStr = dateTimeText.getText().toString().trim();
                String timeStr = timeTimeText.getText().toString().trim();

                if (dateStr.equals("")) {
                    Toast.makeText(EditActivity.this, "please select a date", Toast.LENGTH_LONG).show();
                    return;
                }
                if (timeStr.equals("")) {
                    Toast.makeText(EditActivity.this, "please select a time", Toast.LENGTH_LONG).show();
                    return;
                }
                task.setDealLine(dateStr + " " + timeStr);


                if (checkBox.isChecked()) {
                    task.setIsRemind("1");
                } else {
                    task.setIsRemind("0");
                }
                task.setRemindTimes(times.getText().toString().trim());

                if (TaskDao.editTask(task)) {
                    Toast.makeText(EditActivity.this, "edit success", Toast.LENGTH_LONG).show();
                    EditActivity.this.finish();
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

        /**
         * do delete
         */
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder bb = new AlertDialog.Builder(EditActivity.this);
                bb.setTitle("MESSAGE");
                bb.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (task != null) {
                            if (TaskDao.deleteTask(task)) {
                                Toast.makeText(EditActivity.this, "delete success", Toast.LENGTH_LONG).show();
                                EditActivity.this.finish();
                            } else {
                                Toast.makeText(EditActivity.this, "delete error", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

                bb.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                bb.setMessage("are you sure delete the task ?");
                bb.show();
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
                new DatePickerDialog(EditActivity.this, new DatePickerDialog.OnDateSetListener() {

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
                new TimePickerDialog(EditActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
