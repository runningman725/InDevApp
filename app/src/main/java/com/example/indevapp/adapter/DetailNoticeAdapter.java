package com.example.indevapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indevapp.R;
import com.example.indevapp.activity.PostActivity;
import com.example.indevapp.activity.SetupTogetherActivity;
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

        holder.tv_title.setText("제목 " + position);
        holder.tv_contents.setText("내용" + position);
        holder.tv_time.setText("2023-05-23");
        holder.tv_comment_count.setText("댓글 " + position % 5);
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

            /*
            tv_title.setText(goodsEntityList.get(getAdapterPosition()).getTitle());
            tv_contents.setText(goodsEntityList.get(getAdapterPosition()).getContents());
            tv_time.setText(goodsEntityList.get(getAdapterPosition()).getDate());
            tv_comment_count.setText(goodsEntityList.get(getAdapterPosition()).getComment());
            */

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Context context = v.getContext();
                    Intent intent = new Intent(v.getContext(), PostActivity.class);
                    intent.putExtra("title", goodsEntityList.get(getAdapterPosition()).getTitle());
                    intent.putExtra("content", goodsEntityList.get(getAdapterPosition()).getContents());
                    intent.putExtra("date", goodsEntityList.get(getAdapterPosition()).getDate());
                    intent.putExtra("comment", goodsEntityList.get(getAdapterPosition()).getComment());

                    context.startActivity(intent);
                }
            });
        }
    }
}
