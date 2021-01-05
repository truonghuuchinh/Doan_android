package com.baitap.doan.loadRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.LinkedList;

public class AsyntaskLoader extends AsyncTaskLoader<String> {
    private String api;

    public AsyntaskLoader(@NonNull Context context, String api) {
        super(context);
        this.api = api;
    }
    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getWeather(this.api);
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
