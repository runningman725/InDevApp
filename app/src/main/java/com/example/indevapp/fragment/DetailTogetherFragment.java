package com.example.indevapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indevapp.R;
import com.example.indevapp.adapter.DetailTogetherAdapter;

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
        return view;
    }
}
