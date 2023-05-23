package com.example.indevapp.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.indevapp.R;
import com.example.indevapp.activity.SetupTogetherActivity;
import com.example.indevapp.adapter.MyCrewAdapter;
import com.example.indevapp.adapter.RecommendAdapter;
import com.example.indevapp.bean.GoodsEntity;
import com.example.indevapp.util.FilterDialog;
import com.example.indevapp.util.GridSpacingItemDecoration;
import com.example.indevapp.util.SpacesItemDecoration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TogetherFrament extends Fragment {

    private View view;
    private RecyclerView rlv_my_crew;
    private RecyclerView rlv_recommend_crew;

    private MyCrewAdapter myCrewAdapter;
    private RecommendAdapter recommendAdapter;
    private ArrayList<GoodsEntity> goodsEntityList = new ArrayList<GoodsEntity>();
    private ImageView iv_filter;
    private FilterDialog filterDialog;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_together,container,false);
        initData();
        initView();
        initControl();
        return view;
    }

    private void initView() {
        initRecyclerView();
        iv_filter = view.findViewById(R.id.iv_filter);
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SetupTogetherActivity.class);
                startActivity(intent);
            }
        });

        iv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterDialog= new FilterDialog(getActivity());
                filterDialog.show();
            }
        });
    }

    private void initRecyclerView() {
        rlv_my_crew = view.findViewById(R.id.rlv_my_crew);
        rlv_recommend_crew = view.findViewById(R.id.rlv_recommend_crew);
        myCrewAdapter = new MyCrewAdapter(getActivity(),goodsEntityList);
        rlv_my_crew.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));
        rlv_my_crew.addItemDecoration(new SpacesItemDecoration(10));
        rlv_my_crew.setAdapter(myCrewAdapter);

        recommendAdapter=new RecommendAdapter(getActivity(),goodsEntityList);
        rlv_recommend_crew.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rlv_recommend_crew.addItemDecoration(new GridSpacingItemDecoration(2,20,false));
        rlv_recommend_crew.setAdapter(recommendAdapter);
    }

    private void initData() {
        for (int i=0;i<10;i++){
            GoodsEntity goodsEntity=new GoodsEntity();
            goodsEntity.setTitle("제목 " + i);
            goodsEntity.setContents("내용 " + i);
            goodsEntity.setDate("2023-05-24");
            goodsEntity.setComment("댓글 " + i % 5);
            goodsEntityList.add(goodsEntity);
        }
    }

    private void initControl() {
        FloatingActionButton btn_createInBodyTogether = (FloatingActionButton) view.findViewById(R.id.fab);
        btn_createInBodyTogether.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SetupTogetherActivity.class);
                startActivity(intent);
            }
        });
    }
}
