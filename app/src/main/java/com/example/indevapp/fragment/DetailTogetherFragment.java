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

import com.example.indevapp.R;
import com.example.indevapp.adapter.DetailTogetherAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailTogetherFragment extends Fragment {

    private RecyclerView rlv_together;
    private DetailTogetherAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_together, container, false);
        rlv_together = view.findViewById(R.id.rlv_together);
        adapter = new DetailTogetherAdapter(getActivity());
        rlv_together.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));
        rlv_together.setAdapter(adapter);
        return view;
    }

}
