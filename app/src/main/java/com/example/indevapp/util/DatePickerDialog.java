package com.example.indevapp.util;

import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.indevapp.R;

import java.util.Calendar;

public class DatePickerDialog extends AlertDialog implements DatePicker.OnDateChangedListener{

    private Context context;
    protected DatePickerDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    protected DatePickerDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected DatePickerDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_datepicker);
        DatePicker dp_test = (DatePicker) findViewById(R.id.datepicker);
        Calendar calendar = Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int monthOfYear=calendar.get(Calendar.MONTH);
        int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
        dp_test.init(year,monthOfYear,dayOfMonth,this);
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        onSelectedDateListener.onSelectedDate(year,(monthOfYear+1),dayOfMonth);
    }
    private OnSelectedDateListener onSelectedDateListener;

    public void setOnSelectedDateListener(OnSelectedDateListener onSelectedDateListener) {
        this.onSelectedDateListener = onSelectedDateListener;
    }

    public interface OnSelectedDateListener {
        void onSelectedDate(int year, int monthOfYear, int dayOfMonth);
    }
}
