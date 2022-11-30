package com.example.shareourwedding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Review_RecyclerAdapter extends RecyclerView.Adapter<Review_RecyclerAdapter.CustomViewHolder>
{
    private ArrayList<Review_RecyclerItem> list;

    public Review_RecyclerAdapter(ArrayList<Review_RecyclerItem> list, Context context)
    {
        this.list = list;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position)
    {
        //Review_RecyclerItem list = item.get(position);

        holder.tv_userID.setText(String.valueOf(list.get(position).getUserId()));
        holder.tv_title.setText(String.valueOf(list.get(position).getTitle()));
        holder.tv_content.setText(String.valueOf(list.get(position).getContent()));
    //    holder.onBind(list.get(position));

    }


    @Override
    public int getItemCount()
    {
        return (list !=null ? list.size():0);
    }

//    void addItem(Review_RecyclerItem data) {
//        // 외부에서 item을 추가시킬 함수입니다.
//        list.add(data);
//    }


    class CustomViewHolder extends RecyclerView.ViewHolder
    {
        //TextView tv;
        TextView tv_userID;
        TextView tv_title;
        TextView tv_content;

        public CustomViewHolder(@NonNull View itemView)
        {
            super(itemView);
            //tv = itemView.findViewById(R.id.tv_date);
            //this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.tv_userID = itemView.findViewById(R.id.tv_userID);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_content = itemView.findViewById(R.id.tv_content);

        }

//        void onBind(Review_RecyclerItem data) {
//           //userID.setText(data.getId());
//            tv_title.setText(data.getTitle());
//            tv_content.setText(data.getContent());
//
//        }
    }
}
