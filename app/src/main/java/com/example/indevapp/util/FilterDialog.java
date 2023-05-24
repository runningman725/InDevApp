package com.example.indevapp.util;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, district);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_district.setAdapter(adapter);
        sp_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String[] district = context.getResources().getStringArray(R.array.district);
                String cardNumber = sp_district.getSelectedItem().toString();
                Toast.makeText(getContext(), "你点击的是:"+cardNumber + pos, Toast.LENGTH_LONG).show();
                sp_district.setSelection(pos);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

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
