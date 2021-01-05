package com.baitap.doan.loadRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baitap.doan.R;

import java.util.LinkedList;

public class BaivietAdapter extends  RecyclerView.Adapter<BaivietAdapter.BaivietHolder> implements Filterable {
    LinkedList<Baiviet> mData;
    LinkedList<Baiviet> mDataOld;
    LayoutInflater inflater;
    public BaivietAdapter(LinkedList<Baiviet> mData, Context context) {
        this.mData = mData;
        this.mDataOld = mData;
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()) {
                    mData = mDataOld;
                } else {
                    LinkedList<Baiviet> list = new LinkedList<>();
                    for (Baiviet baiviet : mDataOld) {
                        if(baiviet.getTitle().toLowerCase().contains(strSearch.toLowerCase()) || baiviet.getMota().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(baiviet);
                        }
                    }
                    mData = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mData = (LinkedList<Baiviet>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
