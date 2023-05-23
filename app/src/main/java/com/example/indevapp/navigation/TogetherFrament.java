package com.example.indevapp.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.example.indevapp.Model.Together;
import com.example.indevapp.R;
import com.example.indevapp.activity.SetupTogetherActivity;
import com.example.indevapp.adapter.MyCrewAdapter;
import com.example.indevapp.adapter.RecommendAdapter;
import com.example.indevapp.bean.GoodsEntity;
import com.example.indevapp.util.FilterDialog;
import com.example.indevapp.util.GridSpacingItemDecoration;
import com.example.indevapp.util.SpacesItemDecoration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TogetherFrament extends Fragment {
    private View view;
    private RecyclerView rlv_my_crew;
    private RecyclerView rlv_recommend_crew;

    private MyCrewAdapter myCrewAdapter;
    private RecommendAdapter recommendAdapter;
    private ArrayList<GoodsEntity> totalGoodsEntityList = new ArrayList<GoodsEntity>();
    private ArrayList<GoodsEntity> goodsEntityList = new ArrayList<GoodsEntity>();
    private ArrayList<GoodsEntity> reccomenGoodsEntityList = new ArrayList<GoodsEntity>();
    private ImageView iv_filter;
    private FilterDialog filterDialog;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_together,container,false);
        initData();
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
        Log.e("TAG", "initRecyclerView");
        for (int i=0;i<4;i++) {
            goodsEntityList.add(totalGoodsEntityList.get(i));
        }
        for (int i=4;i<totalGoodsEntityList.size();i++) {
            reccomenGoodsEntityList.add(totalGoodsEntityList.get(i));
        }

        rlv_my_crew = view.findViewById(R.id.rlv_my_crew);
        rlv_recommend_crew = view.findViewById(R.id.rlv_recommend_crew);
        myCrewAdapter = new MyCrewAdapter(getActivity(),goodsEntityList);
        rlv_my_crew.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));
        rlv_my_crew.addItemDecoration(new SpacesItemDecoration(10));
        rlv_my_crew.setAdapter(myCrewAdapter);

        recommendAdapter=new RecommendAdapter(getActivity(),reccomenGoodsEntityList);
        rlv_recommend_crew.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rlv_recommend_crew.addItemDecoration(new GridSpacingItemDecoration(2,20,false));
        rlv_recommend_crew.setAdapter(recommendAdapter);
    }

    private void initData() {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("Together").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Together together = new Together();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        together.setArea(document.getData().get("area") == null ? "" : document.getData().get("area") .toString());
                        together.setEndDate(document.getData().get("endDate") == null ? "" : document.getData().get("endDate") .toString());
                        together.setImage(document.getData().get("image") == null ? "" : document.getData().get("image").toString());
                        together.setIntro(document.getData().get("intro") == null ? "" : document.getData().get("intro").toString());
                        together.setLeaderID(document.getData().get("leaderId") == null ? "" : document.getData().get("leaderId").toString());
                        together.setSportsList(document.getData().get("sportsList") == null ? "" : document.getData().get("sportsList").toString());
                        together.setStartDate(document.getData().get("startDate") == null ? "" : document.getData().get("startDate").toString());
                        together.setTime(document.getData().get("time") == null ? "" : document.getData().get("time").toString());
                        together.setTitle(document.getData().get("title") == null ? "" : document.getData().get("title").toString());
                        together.setTogetherCnt(document.getData().get("togetherCnt") == null ? "" : document.getData().get("togetherCnt").toString());
                        together.setTogetherList(document.getData().get("togetherList") == null ? "" : document.getData().get("togetherList").toString());
                        together.setUid(document.getData().get("uid") == null ? "" : document.getData().get("uid").toString());
                        together.setWorkoutLevel(document.getData().get("workoutLevel") == null ? "" : document.getData().get("workoutLevel").toString());

                        GoodsEntity goodsEntity=new GoodsEntity();
                        goodsEntity.setTitle(together.getTitle() == null ? "" : together.getTitle());
                        goodsEntity.setContents(together.getIntro() == null ? "" : together.getIntro());
                        goodsEntity.setImgPath(together.getImage() == null ? "" : together.getImage());
                        goodsEntity.setDate(together.getEndDate() == null ? "" : together.getEndDate());
                        goodsEntity.setComment("댓글 2");
                        totalGoodsEntityList.add(goodsEntity);

                        Log.d("FirebaseFirestore", goodsEntity.getTitle(), task.getException());
                        Log.d("FirebaseFirestore", goodsEntity.getImgPath(), task.getException());
                        Log.d("FirebaseFirestore", "Success getting documents: ", task.getException());
                    }
                    initView();
                } else {
                    Log.e("FirebaseFirestore", "Error getting documents: ", task.getException());
                }
            }
        });
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
