package com.baitap.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NotificationDetail extends AppCompatActivity {
    TextView textView;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);
        textView=findViewById(R.id.showData);
        back=findViewById(R.id.back_main);
        Intent intent=getIntent();
        String data=intent.getExtras().getString(Login.NOTIFICATION);
        textView.setText(data);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}