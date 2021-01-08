package com.baitap.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Scene;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.baitap.doan.loadRecyclerView.Baiviet;
import com.baitap.doan.loadRecyclerView.BaivietAdapter;
import com.squareup.picasso.Picasso;

public class DetailBaiviet extends AppCompatActivity {

    TextView title;
    WebView webView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_baiviet);
        title=findViewById(R.id.tieudeDetail);
        webView=findViewById(R.id.contents);
        imageView=findViewById(R.id.imgDetail);
        Intent intent=getIntent();
        Picasso.with(this).load("http://10.0.2.2:8000/img/upload/"+intent.getStringExtra(BaivietAdapter.Hinanh_Baiviet).toString()).into(imageView);
        title.setText(intent.getStringExtra(BaivietAdapter.Title_Baiviet));
        webView.loadData(intent.getStringExtra(BaivietAdapter.Noidung_Baiviet), "text/html; charset=utf-8", null);
    }
}