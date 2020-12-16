package com.baitap.doan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

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
            default:
                return  new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
