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

public class BinhLuanAdapter extends RecyclerView.Adapter<BinhLuanAdapter.BinhLuanViewHolder> {

    final LinkedList<BinhLuan> mDataSet;
    LayoutInflater mInflater;

    public BinhLuanAdapter(Context context, LinkedList<BinhLuan> mDataSet) {
        this.mDataSet = mDataSet;
        this.mInflater =LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BinhLuanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_binh_luan_items,parent,false);
        //bước 14: trả về đối tượng Holder
        return new BinhLuanViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull BinhLuanViewHolder holder, int position) {
        BinhLuan curStr = this.mDataSet.get(position);

        // gan gia tri vao view trong ViewHolder
        holder.txtNguoiBinhLuan.setText(curStr.getNguoiBinhLuan());
        holder.txtNoiDungBinhLuan.setText(curStr.getNoiDungBinhLuan());
    }

    @Override
    public int getItemCount() {
        return this.mDataSet.size() ;
    }

    public class BinhLuanViewHolder extends RecyclerView.ViewHolder {
        //********bước 4
        public final TextView txtNguoiBinhLuan, txtNoiDungBinhLuan;
        //***********Bước 5
        final BinhLuanAdapter mAdapter;

        //******** bước 3
        public BinhLuanViewHolder(@NonNull View itemView, final BinhLuanAdapter mAdapter) { //Bước 6
            super(itemView);
            this.mAdapter = mAdapter; //bước 6

            //bước 7
            this.txtNguoiBinhLuan = itemView.findViewById(R.id.txt_nguoi_binh_luan);
            this.txtNoiDungBinhLuan = itemView.findViewById(R.id.txt_noi_dung_binh_luan);

//            //BƯỚC 20: đối tượng đc chọn
//            this.txtWord.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getLayoutPosition();
//                    mDataSet.set(position,"Click Word "+(position + 1));
//                    mAdapter.notifyItemChanged(position);
//                }
//            });
        }
    }

}
