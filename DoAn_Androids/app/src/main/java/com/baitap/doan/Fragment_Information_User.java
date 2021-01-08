package com.baitap.doan;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Fragment_Information_User extends Fragment {
    private  View itemView;
    private TextView username,dateUsername,emailUsername,Logout,goToPHND;
    ImageView imageViewLOgout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        itemView= inflater.inflate(R.layout.fragment__information__user, container, false);
        username=itemView.findViewById(R.id.tenUsername);
        dateUsername=itemView.findViewById(R.id.dateUser);
        emailUsername=itemView.findViewById(R.id.emailUser);
        goToPHND = itemView.findViewById(R.id.textView7);
        Logout=itemView.findViewById(R.id.textView5);

        goToPHND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),PhanHoiNguoiDung.class);
                startActivity(intent);
            }
        });
        

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut(v);
            }
        });
        imageViewLOgout=itemView.findViewById(R.id.imgLogout);
        imageViewLOgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut(v);
            }
        });
        username.setText(Login.tenUser.toString());
        dateUsername.setText(Login.dateUser.toString());
        emailUsername.setText(Login.emailUser.toString());
        return  itemView;
    }
    private  void logOut(View view){
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        Login.tenUser=null;
    }
}