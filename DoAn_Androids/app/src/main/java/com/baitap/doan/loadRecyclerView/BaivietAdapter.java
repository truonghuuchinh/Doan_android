package com.baitap.doan.loadRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baitap.doan.DetailBaiviet;
import com.baitap.doan.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BaivietAdapter extends  RecyclerView.Adapter<BaivietAdapter.BaivietHolder> implements Filterable {
    LinkedList<Baiviet> mData;
    LinkedList<Baiviet> mDataOld;
    Baiviet baiviet;
     static int positionDetail=0;
    public  static  final  String Title_Baiviet="title";
    public  static  final  String Hinanh_Baiviet="hinhanh";
    public  static  final  String Noidung_Baiviet="noidung";
    LayoutInflater inflater;
    Context context;
    public BaivietAdapter(LinkedList<Baiviet> mData, Context _context) {
        this.mData = mData;
        mDataOld=mData;
        inflater=LayoutInflater.from(_context);
        context=_context;
    }
    @NonNull
    @Override
    public BaivietHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item=this.inflater.inflate(R.layout.list_baiviet,parent,false);
        return new BaivietHolder(item,this);
    }

    @Override
    public void onBindViewHolder(@NonNull BaivietHolder holder, int position) {
         baiviet=mData.get(position);
        holder.Title.setText(baiviet.title);
        Picasso.with(context).load("http://10.0.2.2:8000/img/upload/"+baiviet.image).into(holder.Image);
        holder.Description.setText(baiviet.mota);
        //sự kiện click
        holder.Title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDetail(position);
            }
        });
        holder.Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDetail(position);
            }
        });
        holder.Description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDetail(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    void startDetail(int i){
        baiviet=mData.get(i);
        Intent intent=new Intent(context,DetailBaiviet.class);
        intent.putExtra(Title_Baiviet,baiviet.title);
        intent.putExtra(Hinanh_Baiviet,baiviet.image);
        intent.putExtra(Noidung_Baiviet,baiviet.content);
       context.startActivity(intent);
    }
    class  BaivietHolder extends  RecyclerView.ViewHolder{
        TextView Title;
        ImageView Image;
        TextView Description;
        final  BaivietAdapter bookAdapter;
        public BaivietHolder(@NonNull View itemView, BaivietAdapter bookAdapter) {
            super(itemView);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                 Log.d("test",String.valueOf( itemView.get));
//                }
//            });
            this.bookAdapter = bookAdapter;
            Image=itemView.findViewById(R.id.img_baiviet);
            this.Title=itemView.findViewById(R.id.title);
            this.Description=itemView.findViewById(R.id.content);
        }
    }
    //search
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch=constraint.toString();
                if(strSearch.isEmpty()){
                    mData=mDataOld;
                }
                else{
                    LinkedList<Baiviet> list=new LinkedList<Baiviet>();
                    for (Baiviet baiviet:mDataOld){
                        if(baiviet.getTitle().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(baiviet);
                        }
                    }
                    mData=list;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=mData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mData=(LinkedList<Baiviet>)results.values;
                notifyDataSetChanged();
            }
        };
    }
}
