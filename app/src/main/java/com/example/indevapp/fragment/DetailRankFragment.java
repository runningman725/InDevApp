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

import com.example.indevapp.R;

public class DetailRankFragment extends Fragment {

    private static final int TOGETHER=0;
    private static final int BODYRANK=1;
    private int type=0;

    private FrameLayout framelayout;

    private DetailTogetherFragment detailTogetherFragment = new DetailTogetherFragment();
    private DetailBodyrankFragment detailBodyrankFragment = new DetailBodyrankFragment();
    private SwitchCompat switch_compat;
    private Fragment fragment;

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
//        fragment= new DetailTogetherFragment();
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment);
        if (fragment == null) {
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.framelayout,new DetailTogetherFragment()).commit();
            Log.e("tag", "onCreateView: fragment111");
        }
        switch_compat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                hideAllFragments(transaction);
                if(isChecked){
                    fragment = new DetailBodyrankFragment();
                    //Toast.makeText(getContext(),"checked",Toast.LENGTH_LONG).show();
                } else {
                    fragment = new DetailTogetherFragment();
                    //Toast.makeText(getContext(),"not checked",Toast.LENGTH_LONG).show();
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
