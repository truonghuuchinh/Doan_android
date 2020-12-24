package com.baitap.doan.loadRecyclerView;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.baitap.doan.NetworkUtils;
import com.baitap.doan.fragmentMenu.Fragment_Sport;

public class AsyntaskLoader extends AsyncTaskLoader<String> {
    private String book;
    public AsyntaskLoader(@NonNull Context context,String books) {
        super(context);
        book=books;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getWeather(book);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
