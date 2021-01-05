package com.baitap.doan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.baitap.doan.fragment.FragmentHome;
import com.baitap.doan.fragmentMenu.Fragment_Amnhac;
import com.baitap.doan.fragmentMenu.Fragment_Dulich;
import com.baitap.doan.fragmentMenu.Fragment_Giaoduc;
import com.baitap.doan.fragmentMenu.Fragment_Khoahoc;
import com.baitap.doan.fragmentMenu.Fragment_Phapluat;
import com.baitap.doan.fragmentMenu.Fragment_Sport;
import com.baitap.doan.fragmentMenu.Fragment_Thoisu;
import com.baitap.doan.fragmentMenu.Fragment_Xe;

public class Viewpager_Category extends FragmentStatePagerAdapter {
    public Viewpager_Category(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case  0:
                return  new FragmentHome();
            case 1:
                return  new Fragment_Sport();
            case 2:
                return  new Fragment_Thoisu();
            case 3:
                return  new Fragment_Amnhac();
            case 4:
                return  new Fragment_Phapluat();
            case 5:
                return  new Fragment_Giaoduc();
            case 6:
                return  new Fragment_Dulich();
            case 7:
                return  new Fragment_Khoahoc();
            case 8:
                return  new Fragment_Xe();
            default:
                return  new Fragment_Sport();
        }
    }

    @Override
    public int getCount() {
        return 9;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title="Home";
                break;
            case 1:
                title="Thể thao";
                break;
            case 2:
                title="Thời sự";
                break;
            case 3:
                title="Âm nhạc";
                break;
            case 4:
                title="Pháp luật";
                break;
            case 5:
                title="Giáo dục";
                break;
            case 6:
                title="Du lịch";
                break;
            case 7:
                title="Khoa học";
                break;
            case 8:
                title="Xe";
                break;
        }
        return title;
    }
}
