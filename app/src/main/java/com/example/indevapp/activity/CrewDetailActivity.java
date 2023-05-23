package com.example.indevapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.indevapp.Model.DetailTogetherBean;
import com.example.indevapp.R;
import com.example.indevapp.adapter.DetailPagerAdapter;
import com.example.indevapp.fragment.DetailNoticeFragment;
import com.example.indevapp.fragment.DetailRankFragment;
import com.example.indevapp.fragment.DetailSignFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/*
디테일화면
 */
public class CrewDetailActivity extends AppCompatActivity {

    private TabLayout detailTabLayout;
    private Toolbar toolbar_detail;
    private DetailNoticeFragment fragmentNotice;
    private DetailRankFragment fragmentRank;
    private DetailSignFragment fragmentSign;
    private ViewPager detailViewPager;

    private ArrayList<Fragment> fragments = new ArrayList<>();

    private DetailTogetherBean detailTogetherBean;
    private TextView tv_name;
    private TextView tv_sport;
    private TextView tv_time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crew_detail);

        initData();
        initView();

    }

    private void initData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Together").document("1");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                        HashMap map = (HashMap) document.getData();
                        detailTogetherBean = new DetailTogetherBean();
                        detailTogetherBean.setArea((String) map.get("area"));
                        detailTogetherBean.setImage((String) map.get("image"));
                        detailTogetherBean.setEndDate((String) map.get("endDate"));
                        detailTogetherBean.setTogetherList((String) map.get("togetherList"));
                        detailTogetherBean.setIntro((String) map.get("intro"));
                        detailTogetherBean.setStartDate((String) map.get("startDate"));
                        detailTogetherBean.setSportsList((String) map.get("sportsList"));

                        Log.e("TAG", "onComplete: detailTogetherBean==" + detailTogetherBean.toString());

                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });

        fragmentNotice = new DetailNoticeFragment();
        fragmentRank = new DetailRankFragment();
        fragmentSign = new DetailSignFragment();
        fragments.add(fragmentNotice);
        fragments.add(fragmentRank);
        fragments.add(fragmentSign);
    }

    private void initView() {
        tv_name = findViewById(R.id.tv_name);
        tv_sport = findViewById(R.id.tv_sport);
        tv_time = findViewById(R.id.tv_time);

//        tv_time.setText(detailTogetherBean.getStartDate()+"~"+detailTogetherBean.getEndDate());

        detailTabLayout = findViewById(R.id.detailTabLayout);
        detailViewPager = findViewById(R.id.detailViewPager);
        toolbar_detail = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);

        //左侧按钮：可见+更换图标+点击监听
        actionBar.setDisplayHomeAsUpEnabled(true);//显示toolbar的返回按钮
        //toolBar.setNavigationIcon(R.mipmap.back_white);
        toolbar_detail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        detailTabLayout = findViewById(R.id.detailTabLayout);
        detailTabLayout.addTab(detailTabLayout.newTab().setText("게시판"));
        detailTabLayout.addTab(detailTabLayout.newTab().setText("현황"));
        detailTabLayout.addTab(detailTabLayout.newTab().setText("출석"));

        detailViewPager.setAdapter(new DetailPagerAdapter(getSupportFragmentManager(), fragments));
        detailViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(detailTabLayout));

        detailTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
//                Fragment fragment = null;
//                if (pos == 0) {
//                    fragment = fragmentNotice;
//                } else if (pos == 1) {
//                    fragment = fragmentRank;
//                } else if(pos == 2){
//                    fragment = fragmentSign;
//                }
//                getSupportFragmentManager().beginTransaction().add(R.id.)
                Log.e("qm", "onTabSelected: ===" + pos);
                detailViewPager.setCurrentItem(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
