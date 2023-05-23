package com.example.indevapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.indevapp.R;

import java.util.ArrayList;

public class DetailTogetherAdapter extends RecyclerView.Adapter<DetailTogetherAdapter.TogetherView> {

    private Context context;
    private ArrayList<String> togetherList= new ArrayList<>();
    public DetailTogetherAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DetailTogetherAdapter.TogetherView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_detail_together, null);
        return new DetailTogetherAdapter.TogetherView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailTogetherAdapter.TogetherView holder, @SuppressLint("RecyclerView") int position) {
        Log.d("TAG", "qm111 333333 item data: "+togetherList+"===position=="+position);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                FirebaseFirestore db = FirebaseFirestore.getInstance();
//                DocumentReference docRef = db.collection("User").document(togetherList.get(position));
//                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if (task.isSuccessful()) {
//                            DocumentSnapshot document = task.getResult();
//                            if (document.exists()) {
//                                Log.d("TAG", "qm111 item data: " + document.getData());
//                                HashMap map = (HashMap) document.getData();
//
////                                Message msg = Message.obtain();
////                                Bundle b = new Bundle();
////                                b.putSerializable("obj", detailTogetherBean);
////                                msg.setData(b);
////                                handler.sendMessage(msg);
//                            } else {
//                                Log.d("TAG", "No such document");
//                            }
//                        } else {
//                            Log.d("TAG", "get failed with ", task.getException());
//                        }
//                    }
//                });
//
//            }
//        }).start();
        Glide.with(context)
                .load("https://www.eatingwell.com/thmb/m5xUzIOmhWSoXZnY-oZcO9SdArQ=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/article_291139_the-top-10-healthiest-foods-for-kids_-02-4b745e57928c4786a61b47d8ba920058.jpg")
                .centerCrop()
                .into(holder.iv_user);
        holder.tv_user.setText("111");
        holder.tv_weight_change.setText("222");
        holder.tv_weight_rate.setText("333");
        holder.tv_weight_muscle.setText("444");

        int weightMinus=0; //데이터 api에서 획득하여 추가,절대치
        int weightPlus=0;
        int rateMinus=0;
        int ratePlus=0;
        int muscleMinus=0;
        int musclePluls=0;

        minusDataFunc(holder.iv_weight_minus,weightMinus);
        minusDataFunc(holder.iv_rate_minus,rateMinus);
        minusDataFunc(holder.iv_muscle_minus,muscleMinus);

        plusDataFunc(holder.iv_weight_plus, weightPlus);
        plusDataFunc(holder.iv_rate_plus, ratePlus);
        plusDataFunc(holder.iv_muscle_plus, musclePluls);
    }

    private void minusDataFunc(ImageView imageView, int minus) {
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.width = minus*ConvertDPtoPX(context,100)/10;
        imageView.setLayoutParams(lp);
    }

    private void plusDataFunc(ImageView imageView, int plus) {
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.width = plus*ConvertDPtoPX(context,100)/10;
        imageView.setLayoutParams(lp);
    }

    public static int ConvertDPtoPX(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    @Override
    public int getItemCount() {
        return togetherList.size();
    }

    public void addData(ArrayList<String> tgList) {
        if (null != tgList) {
            this.togetherList.clear();
            this.togetherList.addAll(tgList);
        }
        notifyDataSetChanged();
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
