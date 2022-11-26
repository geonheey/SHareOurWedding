package com.example.shareourwedding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<CoupleInfo> arrayList;
    private Context context;
    //어댑터에서 액티비티 액션을 가져올 때 context가 필요한데 어댑터에는 context가 없다.
    //선택한 액티비티에 대한 context를 가져올 때 필요하다.

    public CustomAdapter(ArrayList<CoupleInfo> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    //실제 리스트뷰가 어댑터에 연결된 다음에 뷰 홀더를 최초로 만들어낸다.
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        /*Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.iv_profile);*/
        holder.tv_hname.setText(arrayList.get(position).getHname());
        holder.tv_wname.setText(arrayList.get(position).getWname());
        holder.tv_date.setText(arrayList.get(position).getDate());
        holder.tv_place.setText(String.valueOf(arrayList.get(position).getPlace()));
    }

    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        //ImageView iv_profile;
        TextView tv_hname;
        TextView tv_wname;
        TextView tv_date;
        TextView tv_place;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            //this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.tv_hname = itemView.findViewById(R.id.tv_hname);
            this.tv_wname = itemView.findViewById(R.id.tv_wname);
            this.tv_date = itemView.findViewById(R.id.tv_date);
            this.tv_place = itemView.findViewById(R.id.tv_place);
        }
    }
}
