package com.baitap.doan.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.baitap.doan.Login;
import com.baitap.doan.R;

public class FragmentAccount extends Fragment {
    private  View mview;
    private Button btn_dangnhap;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mview=inflater.inflate(R.layout.fragmentaccount,container,false);
        btn_dangnhap=mview.findViewById(R.id.btn_Login);
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });
        return  mview;
    }
}
