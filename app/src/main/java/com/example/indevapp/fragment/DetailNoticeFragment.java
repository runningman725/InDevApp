package com.example.indevapp.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
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
import com.example.indevapp.activity.CreatePostActivity;
import com.example.indevapp.activity.SetupTogetherActivity;
import com.example.indevapp.adapter.DetailNoticeAdapter;
import com.example.indevapp.bean.GoodsEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

public class DetailNoticeFragment extends Fragment {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    private static final String MY_SECRET_KEY = "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";

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
        initControl();
        return view;
    }

    private void initData() {
        if (goodsEntityList.size() != 0) {
            goodsEntityList.clear();
        }

        GoodsEntity goodsEntity;
        goodsEntity=new GoodsEntity();
        goodsEntity.setTitle("공지 사항");
        goodsEntity.setContents("이번주 토요일은 정기적으로 운동하는 날입니다..");
        goodsEntity.setDate("2023-05-24");
        goodsEntity.setComment("댓글 2");
        goodsEntityList.add(goodsEntity);

        goodsEntity=new GoodsEntity();
        goodsEntity.setTitle("금요일 저녁 운동합시다!");
        goodsEntity.setContents("운동하실 분은...");
        goodsEntity.setDate("2023-05-21");
        goodsEntity.setComment("댓글 1");
        goodsEntityList.add(goodsEntity);

        goodsEntity=new GoodsEntity();
        goodsEntity.setTitle("제목 1");
        goodsEntity.setContents("글 내용.. ");
        goodsEntity.setDate("2023-05-22");
        goodsEntity.setComment("댓글 0");
        goodsEntityList.add(goodsEntity);

        goodsEntity=new GoodsEntity();
        goodsEntity.setTitle("제목 2");
        goodsEntity.setContents("글 내용.. ");
        goodsEntity.setDate("2023-05-22");
        goodsEntity.setComment("댓글 0");
        goodsEntityList.add(goodsEntity);
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

    private void initControl() {
        FloatingActionButton btn_createInBodyTogether = (FloatingActionButton) view.findViewById(R.id.fab);
        btn_createInBodyTogether.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreatePostActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton btn_chatGPT = (FloatingActionButton) view.findViewById(R.id.fabChatGPT);
        btn_chatGPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String question = "평균 신장 173cm 평균 몸무게 70kg인 사람들이 지방량 -2kg, 근육량 +1kg 를 위해 일주일간 어떤 운동을 하면 될까? 현재 달성률은  20%. 3일간의 운동 세트 수와 시간을 알려줘";
                String question = "What kind of exercise can people with an average height of 173cm and an average weight of 70kg do for a week to lose fat mass -2kg and muscle mass +1kg?";
                //String question = "평균 신장 173cm 평균 몸무게 70kg인 사람들이 지방량 -2kg, 근육량 +1kg 를 위해 일주일간 어떤 운동을 하면 될까?";
                callAPI(question);
            }
        });
    }

    void callAPI(String question){
        //okhttp
        JSONObject object = new JSONObject();
        try {
            object.put("model", "text-davinci-003");
            object.put("prompt", question);
            object.put("max_tokens", 4000);
            object.put("temperature", 0);
        } catch (JSONException e){
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(object.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .header("Authorization", "Bearer "+MY_SECRET_KEY)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addResponse("Failed to load response due to "+e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getString("text");
                        addResponse(result.trim());
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                } else {
                    addResponse("Failed to load response due to "+response.body().string());
                }
            }
        });
    }

    void addResponse(String response){
        Log.e("TAG", response);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                GoodsEntity goodsEntity=new GoodsEntity();
                goodsEntity.setTitle("Chat GPT Guide");
                goodsEntity.setContents(response.toString());
                goodsEntity.setDate("2023-05-24");
                goodsEntity.setComment("댓글 0");
                goodsEntityList.add(0, goodsEntity);

                noticeAdapter.notifyDataSetChanged();
            }
        });
    }
}
