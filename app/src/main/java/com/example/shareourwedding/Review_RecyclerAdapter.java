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
    private ArrayList<RecyclerItem> list;
    private Context context;

    public Review_RecyclerAdapter(ArrayList<RecyclerItem> list, Context context)
    {
        this.list = list;
        this.context = context;
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

        holder.onBind(list.get(position));

    }


    @Override
    public int getItemCount()
    {
        return (list !=null ? list.size():0);
    }



    class CustomViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv_title;
        TextView tv_content;

        public CustomViewHolder(@NonNull View itemView)
        {
            super(itemView);

            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_content = itemView.findViewById(R.id.tv_content);

        }

        void onBind(RecyclerItem data) {
            tv_title.setText(data.getTitle());
            tv_content.setText(data.getContent());

        }
    }
}
