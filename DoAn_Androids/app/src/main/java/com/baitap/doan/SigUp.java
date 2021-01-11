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
import com.baitap.doan.fragment.FragmentHome;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class SigUp extends AppCompatActivity {
    EditText choosengay, editUsername, editPassword, editEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig_up);
        editUsername = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
        editEmail = findViewById(R.id.email);
        choosengay=findViewById(R.id.date_Birthday);
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
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                choosengay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },year,month,day);
        datePickerDialog.show();
    }
    public void startSignUpSuccess(View view) {
        postDangKi("http://10.0.2.2:8000/api/dang-ki");

    }
void postDangKi(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(SigUp.this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("success")){
                    Toast.makeText(SigUp.this,"Thanh2 cong",Toast.LENGTH_LONG).show();
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
                param.put("username",editUsername.getText().toString());
                param.put("password",editPassword.getText().toString());
                param.put("email",editEmail.getText().toString());
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
}