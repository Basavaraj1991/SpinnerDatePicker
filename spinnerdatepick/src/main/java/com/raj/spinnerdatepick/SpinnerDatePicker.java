package com.raj.spinnerdatepick;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.basavaraj.spinnerdatepick.R;

import java.util.Calendar;

/**
 * Created by Basavaraj Navi on 27/12/17.
 * Project SpinnerDatePicker
 */

public class SpinnerDatePicker implements DatePicker.OnDateChangedListener{

    Context context;
    DatePicker picker;
    Button btnOk;
    Button cancel;
    TextView mTitle;
    Dialog dtPickerDlg;
    static String mdate;
    IDatePicker iDatePicker;
    LinearLayout buttonLayout;
    CardView mCardView;
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

    public  SpinnerDatePicker setTitleBackgroundColor(int titleBackgroundColor){
        if (mTitle!=null){
            mTitle.setBackgroundColor(titleBackgroundColor);
        }
        return instance;
    }

    public  SpinnerDatePicker setTitleTextdColor(int titleTextdColor){
        if (mTitle!=null){
            mTitle.setTextColor(titleTextdColor);
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

    public  SpinnerDatePicker setOkButtonText(String text){
        if (btnOk!=null){
            btnOk.setText(text);
        }
        return instance;
    }

    public  SpinnerDatePicker setCancelButtonText(String text){
        if (cancel!=null){
            cancel.setText(text);
        }
        return instance;
    }

    public  SpinnerDatePicker setCancelButtonTextColor(int cancelButtonTextColor){
        if (cancel!=null){
            cancel.setTextColor(cancelButtonTextColor);
        }
        return instance;
    }

    public  SpinnerDatePicker setOkButtonTextColor(int okButtonTextColor){
        if (btnOk!=null){
            btnOk.setTextColor(okButtonTextColor);
        }
        return instance;
    }


    public  SpinnerDatePicker setButtonLayoutGravity(int gravity){
        if (buttonLayout!=null){
            buttonLayout.setGravity(gravity);
        }
        return instance;
    }

    public  SpinnerDatePicker setTitleGravity(int gravity){
        if (mTitle!=null){
            mTitle.setGravity(gravity);
        }
        return instance;
    }

    public  SpinnerDatePicker setCardViewBackgroundColor(int color){
        if (mCardView!=null){
            mCardView.setCardBackgroundColor(color);
        }
        return instance;
    }

    public  SpinnerDatePicker setCalendarBackgroundColor(int color){
        if (picker!=null){
            picker.setBackgroundColor(color);
        }
        return instance;
    }


    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mdate = dayOfMonth+"-"+monthOfYear+"-"+year;
    }

    private void openSpinnerDatePicker() {
        dtPickerDlg = new Dialog(context);
        dtPickerDlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dtPickerDlg.setContentView(R.layout.picker);
        picker =(DatePicker) dtPickerDlg.findViewById(R.id.datePicker);
        btnOk =(Button) dtPickerDlg.findViewById(R.id.okbutton);
        mTitle = (TextView) dtPickerDlg.findViewById(R.id.title);
        cancel = (Button) dtPickerDlg.findViewById(R.id.cancel);
        buttonLayout = (LinearLayout) dtPickerDlg.findViewById(R.id.buttonLayout);
        mCardView = (CardView) dtPickerDlg.findViewById(R.id.cardView);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iDatePicker!=null && mdate!=null && !mdate.isEmpty()) {
                    iDatePicker.onOkClick(mdate);
                }else {
                    if (iDatePicker !=null && picker!=null) {
                        mdate = picker.getDayOfMonth() + "-" + picker.getMonth() + "-" + picker.getYear();
                        iDatePicker.onOkClick(mdate);
                    }
                }
                dtPickerDlg.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

}
