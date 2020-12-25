package com.baitap.doan.loadRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baitap.doan.R;

import java.util.LinkedList;

public class BaivietAdapter extends  RecyclerView.Adapter<BaivietAdapter.BaivietHolder>  {
    LinkedList<Baiviet> mData;
    LayoutInflater inflater;
    public BaivietAdapter(LinkedList<Baiviet> mData, Context context) {
        this.mData = mData;
        inflater=LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public BaivietHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item=this.inflater.inflate(R.layout.list_baiviet,parent,false);
        return new BaivietHolder(item,this);
    }

    @Override
    public void onBindViewHolder(@NonNull BaivietHolder holder, int position) {
        Baiviet baiviet=mData.get(position);
        holder.Title.setText(baiviet.title);
        holder.Content.setText(baiviet.content);
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    class  BaivietHolder extends  RecyclerView.ViewHolder{
        TextView Title;
        TextView Content;
        final  BaivietAdapter bookAdapter;
        public BaivietHolder(@NonNull View itemView, BaivietAdapter bookAdapter) {
            super(itemView);
            this.bookAdapter = bookAdapter;
            this.Title=itemView.findViewById(R.id.title);
            this.Content=itemView.findViewById(R.id.content);
        }
    }
}
