package com.example.indevapp.util;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.indevapp.R;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class FilterDialog extends AlertDialog {

    private Spinner sp_district;
    private CheckBox cb_morning;
    private CheckBox cb_afternoon;
    private CheckBox cb_night;
    private Spinner sp_sports;
    private TextView tv_starttime;
    private TextView tv_endtime;
    private TextView tv_confirm;

    private Context context;

    public FilterDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public FilterDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public FilterDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_dialog);
        sp_district = findViewById(R.id.sp_district);
        cb_morning = findViewById(R.id.cb_morning);
        cb_afternoon = findViewById(R.id.cb_afternoon);
        cb_night = findViewById(R.id.cb_night);
        sp_sports = findViewById(R.id.sp_sports);
        tv_starttime = findViewById(R.id.tv_starttime);
        tv_endtime = findViewById(R.id.tv_endtime);
        tv_confirm = findViewById(R.id.tv_confirm);

        sp_district.setSelection(-1,true);

        String[] district = context.getResources().getStringArray(R.array.district);
        List<String> list = new ArrayList<String>();
        for(int i=0; i<district.length; i++) {
            list.add(i, district[i]);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_district.setAdapter(adapter);

        tv_starttime.setOnClickListener(new OnDatePickListener(tv_starttime));
        tv_endtime.setOnClickListener(new OnDatePickListener(tv_endtime));

        cb_morning.setOnCheckedChangeListener(new TimeCheckListener());
        cb_afternoon.setOnCheckedChangeListener(new TimeCheckListener());
        cb_night.setOnCheckedChangeListener(new TimeCheckListener());

        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    private class OnDatePickListener implements View.OnClickListener {

        private TextView view;

        public OnDatePickListener(TextView view) {
            this.view =view;
        }

        @Override
        public void onClick(View v) {
            DatePickerDialog datePickerDialog= new DatePickerDialog(context);
            datePickerDialog.setOnSelectedDateListener(new DatePickerDialog.OnSelectedDateListener() {
                @Override
                public void onSelectedDate(int year, int monthOfYear, int dayOfMonth) {
                    view.setText(year+"/"+monthOfYear+"/"+dayOfMonth);
                }
            });
            datePickerDialog.show();
            //放在show()之后，不然有些属性是没有效果的，比如height和width
            Window dialogWindow = datePickerDialog.getWindow();
            WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
            // 设置宽度
            p.gravity = Gravity.CENTER;//设置位置
            //p.alpha = 0.8f;//设置透明度
            dialogWindow.setAttributes(p);
        }
    }

    private class TimeCheckListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(cb_morning.isChecked()){
                cb_morning.setTextColor(context.getColor(R.color.white));
            } else {
                cb_morning.setTextColor(context.getColor(R.color.inbody_black));
            }
            if(cb_afternoon.isChecked()){
                cb_afternoon.setTextColor(context.getColor(R.color.white));
            } else {
                cb_afternoon.setTextColor(context.getColor(R.color.inbody_black));
            }
            if(cb_night.isChecked()){
                cb_night.setTextColor(context.getColor(R.color.white));
            } else {
                cb_night.setTextColor(context.getColor(R.color.inbody_black));
            }

        }
    }
}
