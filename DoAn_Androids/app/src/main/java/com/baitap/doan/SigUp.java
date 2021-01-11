package com.baitap.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class SigUp extends AppCompatActivity {
    EditText choosengay,edit_User,edit_password,edit_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig_up);
        choosengay=findViewById(R.id.date_Birthday);
        edit_User=findViewById(R.id.username);
        edit_password=findViewById(R.id.password);
        edit_email=findViewById(R.id.email);
        choosengay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choosenggay();
            }
        });
    }
    private void Choosenggay(){
        Calendar calendar=Calendar.getInstance();
        int day=calendar.get(Calendar.DATE);
        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
                choosengay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },year,month,day);
        datePickerDialog.show();
    }
    public void startSignUpSuccess(View view) {
        postLike("http://10.0.2.2:8000/api/danngky");
    }
    void postLike(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success")){
                    Toast.makeText(SigUp.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SigUp.this,MainActivity.class);
                    startActivity(intent);
                }
                if(response.equals("IdenticalUser")){
                    Toast.makeText(SigUp.this,"Tên username đã tồn tại",Toast.LENGTH_SHORT).show();
                }
                if(response.equals("IdenticalEmail")){
                    Toast.makeText(SigUp.this,"Tên email đã tồn tại",Toast.LENGTH_SHORT).show();
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
                param.put("username",edit_User.getText().toString());
                param.put("password",edit_password.getText().toString());
                param.put("email",edit_email.getText().toString());
                String ngay=choosengay.getText().toString();
                param.put("ngaysinh",ngay);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
}