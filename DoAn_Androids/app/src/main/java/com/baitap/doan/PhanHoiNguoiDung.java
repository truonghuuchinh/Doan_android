package com.baitap.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PhanHoiNguoiDung extends AppCompatActivity {
    EditText txtContent;
    Button btnGui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_hoi_nguoi_dung);
        txtContent = findViewById(R.id.txtContent);
        btnGui = findViewById(R.id.btnGui);

        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postLike(txtContent.getText().toString(),Login.IdlUser,"http://10.0.2.2:8000/api/phanhoinguoidung");
                finish();
            }
        });
    }
    void postLike(String noidung,String idUser,String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success")){
                    Toast.makeText(PhanHoiNguoiDung.this,"Phản hồi thành công",Toast.LENGTH_SHORT).show();
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
                param.put("noidung",noidung);
                param.put("id_user",idUser);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
}