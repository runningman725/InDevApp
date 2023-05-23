package com.example.indevapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.indevapp.R;
import com.example.indevapp.adapter.DetailPagerAdapter;
import com.example.indevapp.fragment.DetailNoticeFragment;
import com.example.indevapp.fragment.DetailRankFragment;
import com.example.indevapp.fragment.DetailSignFragment;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crew_detail);

        initData();
        initView();

    }

    private void initData() {
        fragmentNotice = new DetailNoticeFragment();
        fragmentRank = new DetailRankFragment();
        fragmentSign = new DetailSignFragment();
        fragments.add(fragmentNotice);
        fragments.add(fragmentRank);
        fragments.add(fragmentSign);
    }

    private void initView() {
        detailTabLayout = findViewById(R.id.detailTabLayout);
        detailViewPager = findViewById(R.id.detailViewPager);
        toolbar_detail = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar_detail);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar == null){
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

        detailViewPager.setAdapter(new DetailPagerAdapter(getSupportFragmentManager(),fragments));
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
                Log.e("qm", "onTabSelected: ==="+pos);
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
