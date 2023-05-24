package com.example.indevapp.fragment;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.indevapp.DetailRankEvent;
import com.example.indevapp.Model.DetailTogetherBean;
import com.example.indevapp.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class DetailRankFragment extends Fragment {

    private static final int TOGETHER=0;
    private static final int BODYRANK=1;
    private int type=0;

    private FrameLayout framelayout;

    private DetailTogetherFragment detailTogetherFragment = new DetailTogetherFragment();
    private DetailBodyrankFragment detailBodyrankFragment = new DetailBodyrankFragment();
    private SwitchCompat switch_compat;
    private Fragment fragment;

    private String togetherList;

    @Subscribe(sticky = true)
    public void onEventMainThread(DetailRankEvent event) {
        togetherList = event.getTogetherList();
        Log.e("TAG", "onEventMainThread: data222"+togetherList);
    }

        @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_rank,container,false);
        framelayout = view.findViewById(R.id.framelayout);
        switch_compat = view.findViewById(R.id.switch_compat);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fragment = fm.findFragmentById(R.id.framelayout);
        if (fragment != null) {
            transaction.remove(fragment);
            fm.popBackStack();
            transaction.commit();
        }

        if (fragment == null) {
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.framelayout,new DetailTogetherFragment()).commit();
        }
        switch_compat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                hideAllFragments(transaction);
                if(isChecked){
                    fragment = new DetailBodyrankFragment();
                } else {
                    fragment = new DetailTogetherFragment();
                }

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment).commit();
            }
        });

        return view;
    }

    private void hideAllFragments(FragmentTransaction transaction) {
        if (detailTogetherFragment != null) transaction.hide(detailTogetherFragment);
        if (detailBodyrankFragment != null) transaction.hide(detailBodyrankFragment);
    }

}
