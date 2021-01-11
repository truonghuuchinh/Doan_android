package com.baitap.doan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
<<<<<<< Updated upstream

import android.content.Intent;
import android.os.Bundle;
=======
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Scene;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
>>>>>>> Stashed changes
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baitap.doan.fragment.FragmentHome;
import com.baitap.doan.loadRecyclerView.AsyntaskLoader;
import com.baitap.doan.loadRecyclerView.Baiviet;
import com.baitap.doan.loadRecyclerView.BaivietAdapter;
import com.baitap.doan.loadRecyclerView.BinhLuan;
import com.baitap.doan.loadRecyclerView.BinhLuanAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

<<<<<<< Updated upstream
    TextView title,content;
=======
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DetailBaiviet extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    TextView title, txtComment;
    WebView webView;
>>>>>>> Stashed changes
    ImageView imageView;
    Button btnGui;

    String idUser, idBaiViet, username;

    LinkedList<BinhLuan> dsBinhLuan;
    RecyclerView recyclerView;
    BinhLuanAdapter binhLuanAdapter;

    static final int BINHLUAN_LOADER_ID = 3;
    LoaderManager loaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_baiviet);
        title=findViewById(R.id.tieudeDetail);
        content=findViewById(R.id.noidungDetail);
        imageView=findViewById(R.id.imgDetail);

        txtComment = findViewById(R.id.comment);
        btnGui = findViewById(R.id.btn_send);


        loaderManager = LoaderManager.getInstance(this);
        Intent intent=getIntent();
        Picasso.with(this).load(intent.getStringExtra(BaivietAdapter.Hinanh_Baiviet)).into(imageView);
        title.setText(intent.getStringExtra(BaivietAdapter.Title_Baiviet));
<<<<<<< Updated upstream
        content.setText(intent.getStringExtra(BaivietAdapter.Noidung_Baiviet));
=======
        webView.loadData(intent.getStringExtra(BaivietAdapter.Noidung_Baiviet), "text/html; charset=utf-8", null);
        recyclerView = findViewById(R.id.recyclerview_binh_luan);
        idUser= intent.getStringExtra("idUser");
        idBaiViet=intent.getStringExtra("id");
        username =intent.getStringExtra("username");
        Log.d("testid",idUser);
        Log.d("testid",username);

        Bundle data = new Bundle();
        data.putString("api", "http://10.0.2.2:8000/api/get-binh-luan?id="+idBaiViet);
        if (loaderManager.getLoader(BINHLUAN_LOADER_ID) == null) {
            loaderManager.initLoader(BINHLUAN_LOADER_ID, data, this);
        } else {
            loaderManager.restartLoader(BINHLUAN_LOADER_ID, data, this);
        }
    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyntaskLoader(this,args.getString("api"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            String username=null;
            String noidung=null;

            dsBinhLuan =new LinkedList<>();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray dataArray=jsonObject.getJSONArray("data");
            for (int i=0;i<dataArray.length();i++){
                JSONObject dataObject=(JSONObject)dataArray.get(i);

                username=String.valueOf(dataObject.get("username"));
                noidung = String.valueOf(dataObject.get("noidungbinhluan"));
                dsBinhLuan.add(new BinhLuan(username,noidung));
            }

            binhLuanAdapter = new BinhLuanAdapter(this,dsBinhLuan);
            recyclerView.setAdapter(binhLuanAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void onGuiComment(View view) {
        if(Login.IdlUser==null){
            Toast.makeText(DetailBaiviet.this,"Mời bạn đăng nhập để bình luận!",Toast.LENGTH_SHORT).show();
        }else{
            RequestQueue requestQueue= Volley.newRequestQueue(DetailBaiviet.this);
            StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://10.0.2.2:8000/api/post-binh-luan", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equals("success")){
                        int count = dsBinhLuan.size()+1;
                        BinhLuan binhLuan = new BinhLuan(username,txtComment.getText().toString());
                        dsBinhLuan.addLast(binhLuan);
                        binhLuanAdapter.notifyItemInserted(count);
                        txtComment.setText("");
                        Toast.makeText(DetailBaiviet.this,"Hoàn tất",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(DetailBaiviet.this, "Bình luận thất bại", Toast.LENGTH_SHORT).show();
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
                    param.put("noidungbinhluan",txtComment.getText().toString());
                    param.put("id_user",idUser);
                    param.put("id_baiviet",idBaiViet);
                    return param;
                }
            };
            requestQueue.add(stringRequest);

        }

>>>>>>> Stashed changes
    }
}