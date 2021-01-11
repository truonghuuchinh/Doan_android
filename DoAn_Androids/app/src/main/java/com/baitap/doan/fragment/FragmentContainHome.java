package com.baitap.doan.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.baitap.doan.R;
import com.baitap.doan.Viewpager_Category;
import com.google.android.material.tabs.TabLayout;

public class FragmentContainHome extends Fragment {
    private TabLayout tableLayout;
    public static ViewPager viewPager;
    private  View itemView;

    public FragmentContainHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        itemView= inflater.inflate(R.layout.fragment_contain_home, container, false);
        tableLayout=itemView.findViewById(R.id.tablayout);
        viewPager=itemView.findViewById(R.id.viewPagers);
        Viewpager_Category viewpager_category=new Viewpager_Category(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewpager_category);
        tableLayout.setupWithViewPager(viewPager);
        return  itemView;
    }
}