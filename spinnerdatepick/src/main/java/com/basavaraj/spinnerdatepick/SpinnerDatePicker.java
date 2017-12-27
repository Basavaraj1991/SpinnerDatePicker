package com.basavaraj.spinnerdatepick;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Basavaraj Navi on 27/12/17.
 * Project SpinnerDatePicker
 * Copyright (c) 2017 KaHa Technologies Pvt Ltd. All rights reserved.
 */

public class SpinnerDatePicker implements DatePicker.OnDateChangedListener{

    Context context;
    DatePicker picker;
    Button btnOk;
    TextView mTitle;
    Dialog dtPickerDlg;
    static String mdate;
    IDatePicker iDatePicker;

    private static SpinnerDatePicker instance;

    public SpinnerDatePicker(Context context) {
        this.context = context;
        dtPickerDlg = new Dialog(context);
        openSpinnerDatePicker();
    }

    public SpinnerDatePicker show(){
        dtPickerDlg.show();
        return instance;
    }

    public static SpinnerDatePicker getInstance(Context context){
        instance = new SpinnerDatePicker(context);
        return instance;
    }

    public SpinnerDatePicker callback(IDatePicker iDatePicker){
        this.iDatePicker = iDatePicker;
        return instance;
    }
    public  SpinnerDatePicker setTitle(String title){
        if (mTitle!=null){
           mTitle.setText(title);
        }
        return instance;
    }

    public SpinnerDatePicker setMaxDate(long maxDate){
        picker.setMaxDate(maxDate);
        return instance;
    }

    public SpinnerDatePicker setMinDate(long minDate){
        picker.setMinDate(minDate);
        return instance;
    }

    public  SpinnerDatePicker setButtonText(String text){
        if (btnOk!=null){
            btnOk.setText(text);
        }
        return instance;
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mdate = dayOfMonth+"-"+monthOfYear+"-"+year;
    }

    private void openSpinnerDatePicker() {
        dtPickerDlg = new Dialog(context);
        dtPickerDlg.setContentView(R.layout.picker);
        picker =(DatePicker) dtPickerDlg.findViewById(R.id.datePicker);
        btnOk =(Button) dtPickerDlg.findViewById(R.id.okbutton);
        mTitle = (TextView) dtPickerDlg.findViewById(R.id.title);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iDatePicker!=null && mdate!=null && !mdate.isEmpty()) {
                    iDatePicker.onOkClick(mdate);
                }else {
                    Toast.makeText(context, "Implement IDatePicker interface", Toast.LENGTH_SHORT).show();
                }
                dtPickerDlg.dismiss();
            }
        });
        Calendar c = Calendar.getInstance();
        int dd = c.get(Calendar.DAY_OF_MONTH);
        int mm = c.get(Calendar.MONTH);
        int yy = c.get(Calendar.YEAR);
        picker.init(yy,mm,dd,this);
        dtPickerDlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (iDatePicker!=null) {
                    iDatePicker.onDialogDismiss();
                }
            }
        });

    }

    public static String getDate(){
        if(mdate!=null && !mdate.isEmpty()){
            return mdate;
        }else {
            return null;
        }
    }


}
