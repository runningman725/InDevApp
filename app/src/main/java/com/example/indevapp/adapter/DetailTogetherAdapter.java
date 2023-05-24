package com.example.indevapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.indevapp.Model.DetailTogetherUserBean;
import com.example.indevapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class DetailTogetherAdapter extends RecyclerView.Adapter<DetailTogetherAdapter.TogetherView> {

    private Context context;
    private ArrayList<String> togetherList = new ArrayList<>();

    private DetailTogetherUserBean bean;
    private Handler handler;
    private DetailTogetherAdapter.TogetherView holder;

    public DetailTogetherAdapter(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    @NonNull
    @Override
    public DetailTogetherAdapter.TogetherView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_detail_together, null);
        return new DetailTogetherAdapter.TogetherView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailTogetherAdapter.TogetherView holder, @SuppressLint("RecyclerView") int position) {
        this.holder = holder;
        new Thread(new Runnable() {
            @Override
            public void run() {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection("User").document(togetherList.get(position));
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                HashMap map = (HashMap) document.getData();

                                bean = new DetailTogetherUserBean();
                                bean.setUid((String) map.get("uid"));
                                bean.setSmmChange((double) map.get("smmChange"));
                                bean.setSex((String) map.get("sex"));
                                bean.setWeightChange((double) map.get("weightChange"));
                                bean.setAge((double) map.get("age"));
                                bean.setPbfChange((double) map.get("pbfChange"));
                                bean.setHeight((double) map.get("height"));

                                Message msg = Message.obtain();
                                Bundle b = new Bundle();
                                b.putSerializable("bean", bean);
                                msg.setData(b);
                                handler.sendMessage(msg);
                            } else {
                                Log.d("TAG", "No such document");
                            }
                        } else {
                            Log.d("TAG", "get failed with ", task.getException());
                        }
                    }
                });

            }
        }).start();

    }

    private void minusDataFunc(ImageView imageView, double minus) {
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.width = (int) (minus * ConvertDPtoPX(context, 100) / 10);
        imageView.setLayoutParams(lp);
    }

    private void plusDataFunc(ImageView imageView, double plus) {
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.width = (int) (plus * ConvertDPtoPX(context, 100) / 10);
        imageView.setLayoutParams(lp);
    }

    public static int ConvertDPtoPX(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    public double changeTwoDigit(double val) {
        BigDecimal b = new BigDecimal(val);
        //保留2位小数
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    @Override
    public int getItemCount() {
        if (null != togetherList && togetherList.size() > 0) {
            return togetherList.size();
        } else {
            return 0;
        }
    }

    public void addData(ArrayList<String> tgList) {
        if (null != tgList) {
            this.togetherList.clear();
            this.togetherList.addAll(tgList);
        }
        notifyDataSetChanged();
    }

    public void setData(DetailTogetherUserBean bean) {

        Glide.with(context)
                .load("https://www.eatingwell.com/thmb/m5xUzIOmhWSoXZnY-oZcO9SdArQ=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/article_291139_the-top-10-healthiest-foods-for-kids_-02-4b745e57928c4786a61b47d8ba920058.jpg")
                .centerCrop()
                .into(holder.iv_user);
        holder.tv_user.setText(bean.getUid());

        double weightDouble = changeTwoDigit(bean.getWeightChange());
        double weightRate = changeTwoDigit(bean.getPbfChange());
        double weightMuscle = changeTwoDigit(bean.getSmmChange());

        holder.tv_weight_change.setText(String.valueOf(weightDouble));
        holder.tv_weight_rate.setText(String.valueOf(weightRate));
        holder.tv_weight_muscle.setText(String.valueOf(weightMuscle));

        double weightMinus = weightDouble; //데이터 api에서 획득하여 추가,절대치
        double weightPlus = 0;
        double rateMinus = 0;
        double ratePlus = 0;
        double muscleMinus = 0;
        double musclePluls = 0;

        if (weightDouble < 0) {
            minusDataFunc(holder.iv_weight_minus, weightMinus);
        } else {
            plusDataFunc(holder.iv_weight_plus, weightPlus);
        }

        if (weightRate < 0) {
            minusDataFunc(holder.iv_rate_minus, rateMinus);
        } else {
            plusDataFunc(holder.iv_rate_plus, ratePlus);
        }

        if (weightMuscle < 0) {
            minusDataFunc(holder.iv_muscle_minus, muscleMinus);
        } else {
            plusDataFunc(holder.iv_muscle_plus, musclePluls);
        }

    }

    public class TogetherView extends RecyclerView.ViewHolder {
        ImageView iv_user;
        TextView tv_user;
        TextView tv_weight_change;
        TextView tv_weight_rate;
        TextView tv_weight_muscle;
        ImageView iv_weight_minus;
        ImageView iv_weight_plus;
        ImageView iv_rate_minus;
        ImageView iv_rate_plus;
        ImageView iv_muscle_minus;
        ImageView iv_muscle_plus;

        public TogetherView(@NonNull View itemView) {
            super(itemView);
            iv_user = itemView.findViewById(R.id.iv_user);
            tv_user = itemView.findViewById(R.id.tv_user);
            tv_weight_change = itemView.findViewById(R.id.tv_weight_change);
            tv_weight_rate = itemView.findViewById(R.id.tv_weight_rate);
            tv_weight_muscle = itemView.findViewById(R.id.tv_weight_muscle);
            iv_weight_minus = itemView.findViewById(R.id.iv_weight_minus);
            iv_weight_plus = itemView.findViewById(R.id.iv_weight_plus);
            iv_rate_minus = itemView.findViewById(R.id.iv_rate_minus);
            iv_rate_plus = itemView.findViewById(R.id.iv_rate_plus);
            iv_muscle_minus = itemView.findViewById(R.id.iv_muscle_minus);
            iv_muscle_plus = itemView.findViewById(R.id.iv_muscle_plus);
        }
    }
}
