package com.example.indevapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.indevapp.Model.Together;
import com.example.indevapp.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class SetupTogetherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setuptogether);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

        // 지역 리스트업
        String strArea[] = {"강남구","강동구","강서구","강북구", "관악구", "노원구", "동대문구", "도봉구", "마포구", "성동구", "성북구", "영등포구", "종로구", "중구", "중랑구"};
        List<String> list = new ArrayList<String>();
        for(int i=0; i<strArea.length; i++) {
            list.add(i, strArea[i]);
        }
        Spinner spinnerArea = (Spinner)findViewById(R.id.spinner_area);
        // 스피너에 뿌려질 List형식의 Data를 담을 Adapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        // Spinner 클릭시 DropDown 모양을 설정
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 스피너에 어답터를 연결
        spinnerArea.setAdapter(spinnerArrayAdapter);

        // 인원 수 리스트업
        String strPerson[] = {"2","4","8","10", "12", "14", "20", "30", "40"};
        List<String> listPerson = new ArrayList<String>();
        for(int i=0; i<strPerson.length; i++) {
            listPerson.add(i, strPerson[i]);
        }
        Spinner spinnerPerson = (Spinner)findViewById(R.id.spinner_person);
        // 스피너에 뿌려질 List형식의 Data를 담을 Adapter
        ArrayAdapter<String> spinnerPersonArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listPerson);
        // Spinner 클릭시 DropDown 모양을 설정
        spinnerPersonArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 스피너에 어답터를 연결
        spinnerPerson.setAdapter(spinnerPersonArrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return  true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void Click_button_save(View view) {
        // 타이틀
        EditText title = (EditText) findViewById(R.id.edittext_title);
        String strTitle = title.getText().toString();

        // 투게더 소개 내용
        EditText introduce = (EditText) findViewById(R.id.edittext_introduce);
        String strIntroduce = introduce.getText().toString();

        // 투게더 지역 및 인원
        Spinner spinnerArea = (Spinner)findViewById(R.id.spinner_area);
        String strArea = spinnerArea.getSelectedItem().toString();

        Spinner spinnerPerson = (Spinner)findViewById(R.id.spinner_person);
        String strPerson = spinnerPerson.getSelectedItem().toString();

        // 투게더 기간
        DatePicker datePickerStart = (DatePicker)findViewById(R.id.datepicker_periodStart);
        String strTogetherStart = datePickerStart.getYear() + "" + datePickerStart.getMonth() + "" + datePickerStart.getDayOfMonth() ;

        DatePicker datePickerEnd = (DatePicker)findViewById(R.id.datepicker_periodEnd);
        String strTogetherEnd = datePickerEnd.getYear() + "" + datePickerEnd.getMonth() + "" + datePickerEnd.getDayOfMonth() ;

        // 선호 시간대
        RelativeLayout timezone = (RelativeLayout)findViewById(R.id.layout_time);
        View viewTemp;
        String strSelectedTimeZone = "";
        for (int i=0;i<timezone.getChildCount();i++) {
            viewTemp = timezone.getChildAt(i);
            try {
                ToggleButton togglebutton = (ToggleButton) viewTemp;
                if (togglebutton.isChecked()) {
                    strSelectedTimeZone += togglebutton.getText().toString() + ",";
                    //lstSelectedTimeZone.add(togglebutton.getText().toString());
                }
            }catch (Exception e) {

            }
        }
        strSelectedTimeZone.substring(0, strSelectedTimeZone.length() - 1);

        // 운동 종류
        RelativeLayout workoutType = (RelativeLayout)findViewById(R.id.layout_workouttype);
        View viewWorkout;
        String strWorkout = "";
        //List<String> lstSelectedWorkout = new ArrayList<>();
        for (int i=0;i<workoutType.getChildCount();i++) {
            viewWorkout = workoutType.getChildAt(i);
            try {
                ToggleButton togglebutton = (ToggleButton) viewWorkout;
                if (togglebutton.isChecked()) {
                    //lstSelectedWorkout.add(togglebutton.getText().toString());
                    strWorkout += togglebutton.getText().toString() + ",";
                }
            }catch (Exception e) {

            }
        }
        strWorkout.substring(0, strWorkout.length() - 1);

        // 운동 강도
        RelativeLayout workoutLevel = (RelativeLayout)findViewById(R.id.layout_workoutlevel);
        View viewWorkoutLevel;
        //List<String> lstSelectedWorkoutLevel = new ArrayList<String>();
        String strSelectedWorkoutLevel = "";
        for (int i=0;i<workoutLevel.getChildCount();i++) {
            viewWorkoutLevel = workoutLevel.getChildAt(i);
            try {
                ToggleButton togglebutton = (ToggleButton) viewWorkoutLevel;
                if (togglebutton.isChecked()) {
                    //lstSelectedWorkoutLevel.add(togglebutton.getText().toString());
                    strSelectedWorkoutLevel += togglebutton.getText().toString() + ",";
                }
            }catch (Exception e) {

            }
        }
        strSelectedWorkoutLevel.substring(0, strSelectedWorkoutLevel.length() - 1);

        try {
            // 투게더 모델
            Together together = new Together();
            together.setLeaderID("00000001");
            together.setTitle(strTitle);
            together.setIntro(strIntroduce);
            together.setArea(strArea);
            together.setTogetherCnt(strPerson);
            together.setTime(strSelectedTimeZone);
            //together.setTime(lstSelectedTimeZone.toArray(new String[lstSelectedTimeZone.size()]));
            together.setStartDate(strTogetherStart);
            together.setEndDate(strTogetherEnd);
            together.setSportsList(strWorkout);
            //together.setSportsList(lstSelectedWorkout.toArray(new String[lstSelectedWorkout.size()]));
            together.setWorkoutLevel(strSelectedWorkoutLevel);
            //together.setWorkoutLevel(lstSelectedWorkoutLevel.toArray(new String[lstSelectedWorkoutLevel.size()]));
            together.setTogetherList("00000001");
            together.setUid("6");

            FirebaseFirestore firebaseFireStore = FirebaseFirestore.getInstance();
            firebaseFireStore.collection("Together").document("6").set(together);

        }catch (Exception e) {
            Log.e("TAG", e.getMessage());
        }
    }
}
