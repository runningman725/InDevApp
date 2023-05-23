package com.example.indevapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
import com.example.indevapp.R;
import com.example.indevapp.adapter.DetailTogetherAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class DetailTogetherFragment extends Fragment {

    private RecyclerView rlv_together;
    private DetailTogetherAdapter adapter;
    private String togetherList="";
    private ArrayList<String> tgList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            togetherList = bundle.getString("togetherList");
//        }
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_together, container, false);
        rlv_together = view.findViewById(R.id.rlv_together);
        EventBus.getDefault().register(this);
        Log.e("tag", "DetailTogetherFragment onCreateView: ===tgList=="+tgList);

        adapter = new DetailTogetherAdapter(getActivity());
        rlv_together.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));
        adapter.addData(tgList);
        rlv_together.setAdapter(adapter);
        return view;
    }

    @Subscribe(sticky = true)
    public void onEventMainThread(DetailRankEvent event) {
        togetherList = event.getTogetherList();
        Log.e("TAG", "onEventMainThread: data2"+togetherList);
        tgList = new ArrayList<>();
        for (String member: togetherList.split(",")){
            tgList.add(member);
        }
        adapter.addData(tgList);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

}
