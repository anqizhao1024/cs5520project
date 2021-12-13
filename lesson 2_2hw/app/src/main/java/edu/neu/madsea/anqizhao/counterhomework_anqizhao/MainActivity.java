package edu.neu.madsea.anqizhao.counterhomework_anqizhao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private TextView showCount;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("saved_count", Integer.toString(mCount));
        outState.putInt("saved_count_val", mCount);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showCount = findViewById(R.id.show_count);
        if (savedInstanceState != null) {
            showCount.setText(savedInstanceState.getString("saved_count"));
            mCount = savedInstanceState.getInt("saved_count_val");
        }
    }

    public void countUp(View view) {
        mCount++;
        showCount.setText(Integer.toString(mCount));
    }
}