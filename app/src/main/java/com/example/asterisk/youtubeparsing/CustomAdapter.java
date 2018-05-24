package com.example.asterisk.youtubeparsing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asterisk.youtubeparsing.entities.Item;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    Context c;
    List<Item> list;
    public CustomAdapter(MainActivity mainActivity, List<Item> mydata) {
        c = mainActivity;
        list = mydata;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.singleitem,parent,false);
        return new MyViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.title.setText(list.get(position).getSnippet().getTitle());           //data view
        holder.date.setText(list.get(position).getSnippet().getPublishedAt());
        Glide.with(c).load(list.get(position).getSnippet().getThumbnails().getDefault().getUrl()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView title,date;
        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            date= itemView.findViewById(R.id.date);
        }
    }

}
