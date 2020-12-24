package com.baitap.doan;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.baitap.doan.fragment.FragmentAccount;
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
                return  new FragmentHome();
            case 1:
                return  new FragmentHotnews();
            case 2:
                return  new FragmentFavorite();
            case 3:
                return  new FragmentAccount();
            case 4:
                return  new Fragment_Sport();
            case 5:
                return  new Fragment_Thoisu();
            case 6:
                return  new Fragment_Amnhac();
            case 7:
                return  new Fragment_Phapluat();
            case 8:
                return  new Fragment_Giaoduc();
            case 9:
                return  new Fragment_Dulich();
            case 10:
                return  new Fragment_Khoahoc();
            case 11:
                return  new Fragment_Xe();
            default:
                return  new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return 12;
    }
}
