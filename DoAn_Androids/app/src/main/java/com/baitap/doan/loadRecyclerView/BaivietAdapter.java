package com.baitap.doan.loadRecyclerView;

import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baitap.doan.DetailBaiviet;
import com.baitap.doan.Login;
import com.baitap.doan.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class BaivietAdapter extends  RecyclerView.Adapter<BaivietAdapter.BaivietHolder> implements Filterable {
    LinkedList<Baiviet> mData;
    LinkedList<Baiviet> mDataOld;
    int i=0;
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
        holder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baiviet=mData.get(position);
                if(Login.IdlUser==null){
                    Toast.makeText(context,"Mời bạn đăng nhập",Toast.LENGTH_SHORT).show();
                }else{
                    postLike(String.valueOf(baiviet.id),Login.IdlUser,"http://10.0.2.2:8000/api/luotlike");
                }
            }
        });
        holder.imgYeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v )  {
                baiviet=mData.get(position);
                if(Login.IdlUser==null){
                    Toast.makeText(context,"Mời bạn đăng nhập",Toast.LENGTH_SHORT).show();
                }else{
                    postLike(String.valueOf(baiviet.id),Login.IdlUser,"http://10.0.2.2:8000/api/baivietyeuthich");
                }
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
        intent.putExtra("id",String.valueOf(baiviet.id));
        intent.putExtra("idUser",String.valueOf(Login.IdlUser));
        intent.putExtra("username",String.valueOf(Login.tenUser));
        intent.putExtra(Title_Baiviet,baiviet.title);
        intent.putExtra(Hinanh_Baiviet,baiviet.image);
        intent.putExtra(Noidung_Baiviet,baiviet.content);
       context.startActivity(intent);
    }

    class  BaivietHolder extends  RecyclerView.ViewHolder{
        TextView Title;
        ImageView Image,imgLike,imgYeuthich;
        TextView Description;
        final  BaivietAdapter bookAdapter;
        public BaivietHolder(@NonNull View itemView, BaivietAdapter bookAdapter) {
            super(itemView);
            this.bookAdapter = bookAdapter;
            Image=itemView.findViewById(R.id.img_baiviet);
            this.Title=itemView.findViewById(R.id.title);
            this.imgLike=itemView.findViewById(R.id.like);
            this.imgYeuthich=itemView.findViewById(R.id.yeuthich);
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
    void postLike(String idbaiviet,String idUser,String url){
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success")){
                    Toast.makeText(context,"Đã like",Toast.LENGTH_SHORT).show();
                }
                if(response.equals("successFavorite")){
                    Toast.makeText(context,"Đã thêm",Toast.LENGTH_SHORT).show();
                }
                if(response.equals("Identical")){
                    Toast.makeText(context,"Đã có trong danh sách yêu thích",Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param=new HashMap<>();
                param.put("id_baiviet",idbaiviet);
                param.put("id_user",idUser);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
}
