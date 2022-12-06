package com.example.shareourwedding;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>
{
    private ArrayList<CoupleInfo2> list = new ArrayList<>();
    private Context context;
    private AdapterView.OnItemClickListener itemClickListener;



    public CustomAdapter(ArrayList<CoupleInfo2> list, Context context)
    {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position)
    {
        Glide.with(holder.itemView)
                .load(list.get(position).getImageUrl())
                .into(holder.iv_couple);
        holder.tv_hname.setText(list.get(position).getHname());
        holder.tv_wname.setText(list.get(position).getWname());
        holder.tv_date.setText(list.get(position).getDate());
        holder.tv_place.setText(list.get(position).getPlace());

        //holder.onBind(list.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String hname = holder.tv_hname.getText().toString();
                String wname = holder.tv_wname.getText().toString();
                String date = holder.tv_date.getText().toString();
                String place = holder.tv_place.getText().toString();
                String id = holder.id;

                Intent intent = new Intent(context, ChoiceActivity2.class);

                intent.putExtra("hname", hname);
                intent.putExtra("wname", wname);
                intent.putExtra("date", date);
                intent.putExtra("place", place);
                intent.putExtra("id", id);

                context.startActivity(intent);


            }
        });

    }


    @Override
    public int getItemCount()
    {
        return list.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_couple;
        TextView tv_hname;
        TextView tv_wname;
        TextView tv_date;
        TextView tv_place;
        CardView card_view;
        String id;

        public CustomViewHolder(@NonNull View itemView)
        {
            super(itemView);

            this.tv_hname = itemView.findViewById(R.id.tv_hname);
            this.tv_wname = itemView.findViewById(R.id.tv_wname);
            this.tv_date = itemView.findViewById(R.id.tv_date);
            this.tv_place = itemView.findViewById(R.id.tv_place);
            this.card_view = itemView.findViewById(R.id.layout_container);
            this.iv_couple = itemView.findViewById(R.id.iv_couple);

        }

        void onBind(CoupleInfo2 data) {
            /*tv_hname.setText(data.getHname());
            tv_wname.setText(data.getWname());
            tv_date.setText(data.getDate());
            tv_place.setText(data.getPlace());*/
            id = data.getIdToken();
        }
    }
}

