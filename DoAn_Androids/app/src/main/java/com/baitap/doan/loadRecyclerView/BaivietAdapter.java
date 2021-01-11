package com.baitap.doan.loadRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baitap.doan.DetailBaiviet;
import com.baitap.doan.R;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

public class BaivietAdapter extends  RecyclerView.Adapter<BaivietAdapter.BaivietHolder>  {
    LinkedList<Baiviet> mData;
    Baiviet baiviet;
    public  static  final  String Title_Baiviet="title";
    public  static  final  String Hinanh_Baiviet="hinhanh";
    public  static  final  String Noidung_Baiviet="noidung";
    LayoutInflater inflater;
    Context context;
    public BaivietAdapter(LinkedList<Baiviet> mData, Context _context) {
        this.mData = mData;
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
        Picasso.with(context).load(baiviet.image).into(holder.Image);
        holder.Content.setText(baiviet.content);
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

<<<<<<< Updated upstream
=======
    void startDetail(int i){
        baiviet=mData.get(i);
        Intent intent=new Intent(context,DetailBaiviet.class);
        intent.putExtra("id",String.valueOf(baiviet.id));
        intent.putExtra("idUser",String.valueOf(Login.IdlUser));
        intent.putExtra("username",String.valueOf(Login.tenUser));
        intent.putExtra(Title_Baiviet,baiviet.title);
        intent.putExtra(Hinanh_Baiviet,baiviet.image);
        intent.putExtra(Noidung_Baiviet,baiviet.content);
       context.startActivity(intent);
    }

>>>>>>> Stashed changes
    class  BaivietHolder extends  RecyclerView.ViewHolder{
        TextView Title;
        ImageView Image;
        TextView Content;
        final  BaivietAdapter bookAdapter;
        public BaivietHolder(@NonNull View itemView, BaivietAdapter bookAdapter) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,DetailBaiviet.class);
                    intent.putExtra(Title_Baiviet,Title.getText().toString());
                    intent.putExtra(Hinanh_Baiviet,baiviet.image);
                    intent.putExtra(Noidung_Baiviet,Content.getText().toString());
                    context.startActivity(intent);
                }
            });
            this.bookAdapter = bookAdapter;
            Image=itemView.findViewById(R.id.img_baiviet);
            this.Title=itemView.findViewById(R.id.title);
            this.Content=itemView.findViewById(R.id.content);
        }
    }
}
