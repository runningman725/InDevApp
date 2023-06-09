package com.example.indevapp.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indevapp.DetailRankEvent;
import com.example.indevapp.Model.DetailTogetherUserBean;
import com.example.indevapp.R;
import com.example.indevapp.adapter.DetailTogetherAdapter;
import com.example.indevapp.bean.GoodsEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetailTogetherFragment extends Fragment {

    private static final String TGLIST="togetherList";
    private RecyclerView rlv_together;
    private DetailTogetherAdapter adapter;
    private String together="";
    private ArrayList<String> tgList;
    private SharedPreferences.Editor editor;

    private ArrayList<DetailTogetherUserBean> detailTogetherUserBean = new ArrayList<DetailTogetherUserBean>();

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            DetailTogetherUserBean bean = (DetailTogetherUserBean) msg.getData().getSerializable("bean");
            adapter.setData(bean);
            return false;
        }
    });

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editor= getActivity().getSharedPreferences("IB",Activity.MODE_PRIVATE).edit();
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            togetherList = bundle.getString("togetherList");
//        }
        DataInit();
    }

    private void DataInit() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        HashMap map = (HashMap) document.getData();
                        DetailTogetherUserBean bean = new DetailTogetherUserBean();
                        bean.setUid(map.get("uid") == null ? "" : (String) map.get("uid"));
                        bean.setSmmChange(map.get("smmChange") == null ? 0.0 : (double) map.get("smmChange"));
                        bean.setSex(map.get("sex") == null ? "" : (String) map.get("sex"));
                        bean.setWeightChange(map.get("weightChange") == null ? 0.0 : (double) map.get("weightChange"));
                        bean.setAge(map.get("age") == null ? 0.0 : (double) map.get("age"));
                        bean.setPbfChange(map.get("pbfChange") == null ? 0.0 : (double) map.get("pbfChange"));
                        bean.setHeight(map.get("height") == null ? 0.0 : (double) map.get("height"));

                        detailTogetherUserBean.add((bean));
                        Log.e("TAG", bean.getUid() + ", " + bean.getWeightChange());
                    }
                    adapter.addData(detailTogetherUserBean);
                }
                //

            }
        });
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_together, container, false);
        rlv_together = view.findViewById(R.id.rlv_together);
        //EventBus.getDefault().register(this);

        //SharedPreferences sp= getActivity().getSharedPreferences("IB",Activity.MODE_PRIVATE);

        //String togetherList = sp.getString(TGLIST,"");

        /*
        tgList = new ArrayList<>();
        for (String member: togetherList.split(",")){
            tgList.add(member);
        }
         */

        rlv_together.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));

        adapter = new DetailTogetherAdapter(getActivity(), handler);
        rlv_together.setAdapter(adapter);
        /*
        if(!TextUtils.isEmpty(togetherList)){
            adapter.addData(tgList);
        }

         */
        return view;
    }

    /*
    @Subscribe(sticky = true)
    public void onEventMainThread(DetailRankEvent event) {
        together = event.getTogetherList();
        tgList = new ArrayList<>();
        for (String member: together.split(",")){
            tgList.add(member);
        }
        editor.putString(TGLIST,together);
        editor.commit();
        adapter.addData(tgList);

    }

     */

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

}
