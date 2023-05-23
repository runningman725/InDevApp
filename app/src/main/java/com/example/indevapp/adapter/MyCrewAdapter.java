package com.example.indevapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.indevapp.R;
import com.example.indevapp.activity.CrewDetailActivity;
import com.example.indevapp.bean.GoodsEntity;

import java.util.ArrayList;

public class MyCrewAdapter extends RecyclerView.Adapter<MyCrewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<GoodsEntity> goodsEntityList;

    public MyCrewAdapter(Context context, ArrayList<GoodsEntity> goodsEntityList) {
        this.context = context;
        this.goodsEntityList = goodsEntityList;
    }

    @NonNull
    @Override
    public MyCrewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_my_crew, null);
        return new MyCrewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCrewAdapter.ViewHolder holder, int position) {
        holder.tv_crew_name.setText("111");
        Glide.with(context)
                .load("https://www.eatingwell.com/thmb/m5xUzIOmhWSoXZnY-oZcO9SdArQ=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/article_291139_the-top-10-healthiest-foods-for-kids_-02-4b745e57928c4786a61b47d8ba920058.jpg")
                .centerCrop()
                .into(holder.img_crew);
        Log.e("qm", "onBindViewHolder: =-=");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CrewDetailActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return goodsEntityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_crew;
        private TextView tv_crew_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_crew = itemView.findViewById(R.id.img_crew);
            tv_crew_name = itemView.findViewById(R.id.tv_crew_name);

        }
    }
}
