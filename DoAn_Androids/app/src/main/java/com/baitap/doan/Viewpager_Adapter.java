package com.baitap.doan;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.baitap.doan.fragment.FragmentAccount;
import com.baitap.doan.fragment.FragmentContainHome;
import com.baitap.doan.fragment.FragmentFavorite;
import com.baitap.doan.fragment.FragmentHome;
import com.baitap.doan.fragment.FragmentHotnews;
import com.baitap.doan.fragmentMenu.Fragment_Amnhac;
import com.baitap.doan.fragmentMenu.Fragment_Dulich;
import com.baitap.doan.fragmentMenu.Fragment_Giaoduc;
import com.baitap.doan.fragmentMenu.Fragment_Khoahoc;
import com.baitap.doan.fragmentMenu.Fragment_Phapluat;
import com.baitap.doan.fragmentMenu.Fragment_Sport;
import com.baitap.doan.fragmentMenu.Fragment_Thoisu;
import com.baitap.doan.fragmentMenu.Fragment_Xe;

public class Viewpager_Adapter  extends FragmentStatePagerAdapter {
    public Viewpager_Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new FragmentContainHome();
            case 1:
                return  new FragmentHotnews();
            case 2:
                return  new FragmentFavorite();
            case 3:
                if(Login.tenUser==null){
                    return  new FragmentAccount();

                }else {
                    return  new Fragment_Information_User();
                }
            default:
                return  new FragmentHome();
        }
    }
    @Override
    public int getCount() {
        return 4;
    }
}
