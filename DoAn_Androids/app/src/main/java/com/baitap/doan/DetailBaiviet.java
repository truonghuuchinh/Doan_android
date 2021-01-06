package com.baitap.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.baitap.doan.loadRecyclerView.Baiviet;
import com.baitap.doan.loadRecyclerView.BaivietAdapter;
import com.squareup.picasso.Picasso;

public class DetailBaiviet extends AppCompatActivity {

    TextView title,content;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_baiviet);
        title=findViewById(R.id.tieudeDetail);
        content=findViewById(R.id.noidungDetail);
        imageView=findViewById(R.id.imgDetail);
        Intent intent=getIntent();
        Picasso.with(this).load(intent.getStringExtra(BaivietAdapter.Hinanh_Baiviet)).into(imageView);
        title.setText(intent.getStringExtra(BaivietAdapter.Title_Baiviet));
        content.setText(intent.getStringExtra(BaivietAdapter.Noidung_Baiviet));
    }
}