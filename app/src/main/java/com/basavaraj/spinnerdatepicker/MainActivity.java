package com.basavaraj.spinnerdatepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.raj.spinnerdatepick.IDatePicker;
import com.raj.spinnerdatepick.SpinnerDatePicker;

public class MainActivity extends AppCompatActivity implements IDatePicker {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.open);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpinnerDatePicker.getInstance(MainActivity.this)
                        .setMaxDate(System.currentTimeMillis())
                        .callback(MainActivity.this)
                        .show();
            }
        });
    }

    @Override
    public void onOkClick(String date) {
        textView.setText(date);
    }

    @Override
    public void onDialogDismiss() {

    }
}
