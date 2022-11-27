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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>
{
    private ArrayList<CoupleInfo> list = new ArrayList<>();
    private Context context;

    public CustomAdapter(ArrayList<CoupleInfo> list, Context context)
    {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //Context context = parent.getContext();
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        //CustomViewHolder holder = new CustomViewHolder(view);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position)
    {

        holder.onBind(list.get(position));
    }


    @Override
    public int getItemCount()
    {
        return list.size();
    }

    void addItem(CoupleInfo data) {
        // 외부에서 item을 추가시킬 함수입니다.
        list.add(data);
    }


    class CustomViewHolder extends RecyclerView.ViewHolder
    {
        //TextView tv;
        ImageView iv_profile;
        TextView tv_hname;
        TextView tv_wname;
        TextView tv_date;
        TextView tv_place;

        public CustomViewHolder(@NonNull View itemView)
        {
            super(itemView);
            //tv = itemView.findViewById(R.id.tv_date);
            //this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.tv_hname = itemView.findViewById(R.id.tv_hname);
            this.tv_wname = itemView.findViewById(R.id.tv_wname);
            this.tv_date = itemView.findViewById(R.id.tv_date);
            this.tv_place = itemView.findViewById(R.id.tv_place);
        }

        void onBind(CoupleInfo data) {
            tv_hname.setText(data.getHname());
            tv_wname.setText(data.getWname());
            tv_date.setText(data.getDate());
            tv_place.setText(data.getPlace());
        }
    }
}

