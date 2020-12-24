package com.baitap.doan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.util.Log;

import com.baitap.doan.loadRecyclerView.AsyntaskLoader;

public class inclue_sport extends AppCompatActivity implements  LoaderManager.LoaderCallbacks<String> {
    static  final  int WEATHER_LOADER_ID=1;
    LoaderManager loaderManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loaderManager = LoaderManager.getInstance(this);
        Bundle data=new Bundle();
        data.putString("book","Ho Chi Minh");
        loaderManager.initLoader(WEATHER_LOADER_ID, data, this);
        setContentView(R.layout.activity_inclue_sport);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyntaskLoader(this,args.getString("book"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Log.d("TEST_LOG", data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}