package com.example.indevapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indevapp.R;
import com.example.indevapp.bean.GoodsEntity;

import java.util.ArrayList;

public class DetailNoticeAdapter extends RecyclerView.Adapter<DetailNoticeAdapter.NoticeViewHolder> {

    private Context context;
    private ArrayList<GoodsEntity> goodsEntityList;

    public DetailNoticeAdapter(Context context, ArrayList<GoodsEntity> goodsEntityList) {
        this.context = context;
        this.goodsEntityList = goodsEntityList;
    }

    @NonNull
    @Override
    public DetailNoticeAdapter.NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_detail_notice, null);
        return new DetailNoticeAdapter.NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailNoticeAdapter.NoticeViewHolder holder, int position) {
        holder.tv_title.setText("1111");
        holder.tv_contents.setText("222");
        holder.tv_time.setText("333");
        holder.tv_comment_count.setText("444");
    }

    @Override
    public int getItemCount() {
        return goodsEntityList.size();
    }

    public class NoticeViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private TextView tv_contents;
        private TextView tv_time;
        private TextView tv_comment_count;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_contents = itemView.findViewById(R.id.tv_contents);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_comment_count = itemView.findViewById(R.id.tv_comment_count);
        }
    }
}
