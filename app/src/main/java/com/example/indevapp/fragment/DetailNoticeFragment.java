package com.example.indevapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indevapp.R;
import com.example.indevapp.adapter.DetailNoticeAdapter;
import com.example.indevapp.bean.GoodsEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DetailNoticeFragment extends Fragment {

    private RecyclerView rlv_notes;
    private FloatingActionButton fab;
    private View view;
    private DetailNoticeAdapter noticeAdapter;
    private ArrayList<GoodsEntity> goodsEntityList = new ArrayList<GoodsEntity>();

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_notice,container,false);
        initData();
        iniView();
        return view;
    }

    private void initData() {
        for (int i=0;i<10;i++){
            GoodsEntity goodsEntity=new GoodsEntity();
            goodsEntity.setGoodsName("模拟数据"+i);
            goodsEntity.setGoodsPrice("100"+i);
            goodsEntityList.add(goodsEntity);
        }
    }
    private void iniView() {
        rlv_notes = view.findViewById(R.id.rlv_notes);
        fab = view.findViewById(R.id.fab);

        noticeAdapter = new DetailNoticeAdapter(getActivity(),goodsEntityList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlv_notes.setLayoutManager(linearLayoutManager);
        rlv_notes.setAdapter(noticeAdapter);

    }
}
