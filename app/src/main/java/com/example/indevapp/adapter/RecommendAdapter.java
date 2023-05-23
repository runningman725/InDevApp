package com.example.indevapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.indevapp.R;
import com.example.indevapp.activity.CrewDetailActivity;
import com.example.indevapp.bean.GoodsEntity;

import java.util.ArrayList;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder> {

    private Context context;
    private ArrayList<GoodsEntity> reccomendGoodsEntityList;
    public RecommendAdapter(FragmentActivity context, ArrayList<GoodsEntity> reccomendGoodsEntityList) {
        this.context = context;
        this.reccomendGoodsEntityList = reccomendGoodsEntityList;
    }

    @NonNull
    @Override
    public RecommendAdapter.RecommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_recommend_crew, null);
        return new RecommendAdapter.RecommendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendAdapter.RecommendViewHolder holder, int position) {
        holder.tv_crew_name.setText(reccomendGoodsEntityList.get(position).getTitle());
        Glide.with(context)
                .load(reccomendGoodsEntityList.get(position).getImgPath())
                .centerCrop()
                .into(holder.img_crew);

        Log.e("qm", "onBindViewHolder: =-=");
        ViewGroup.LayoutParams lp = holder.img_crew.getLayoutParams();
        int width = context.getResources().getDisplayMetrics().widthPixels;
        lp.width = width/2-30;
        lp.height = width/2-30;
        holder.img_crew.setLayoutParams(lp);

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
        return reccomendGoodsEntityList.size();
    }

    public class RecommendViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_crew;
        private TextView tv_crew_name;
        public RecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            img_crew = itemView.findViewById(R.id.img_crew);
            tv_crew_name = itemView.findViewById(R.id.tv_crew_name);
        }
    }
}
