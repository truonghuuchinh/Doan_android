package com.baitap.doan.fragmentMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.baitap.doan.R;


public class Fragment_Khoahoc extends Fragment {
    public Fragment_Khoahoc() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__khoahoc, container, false);
    }
}