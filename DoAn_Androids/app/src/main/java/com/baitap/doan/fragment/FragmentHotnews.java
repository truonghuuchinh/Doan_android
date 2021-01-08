package com.baitap.doan.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baitap.doan.R;
import com.baitap.doan.loadRecyclerView.AsyntaskLoader;
import com.baitap.doan.loadRecyclerView.Baiviet;
import com.baitap.doan.loadRecyclerView.BaivietAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class FragmentHotnews extends Fragment implements LoaderManager.LoaderCallbacks<String> {
    private RecyclerView recyclerView;
    BaivietAdapter bookAdapter;
    LinkedList<Baiviet> listBook;
    static final int WEATHER_LOADER_ID = 1000;
    LoaderManager loaderManager;
    View itemView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        itemView=inflater.inflate(R.layout.fragmenthome, container, false);
        recyclerView=itemView.findViewById(R.id.rv_list);
        loaderManager=LoaderManager.getInstance(this);
        Bundle data = new Bundle();
        data.putString("api", "http://10.0.2.2:8000/api/tin-hot");
        if (loaderManager.getLoader(WEATHER_LOADER_ID) == null) {
            loaderManager.initLoader(WEATHER_LOADER_ID, data, FragmentHotnews.this);
        } else {
            loaderManager.restartLoader(WEATHER_LOADER_ID, data, FragmentHotnews.this);
        }
        return  itemView;
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyntaskLoader(getContext(),args.getString("api"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            String Title=null;
            String Content=null;
            String Image=null;
            String Description=null;
            listBook=new LinkedList<Baiviet>();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray dataArray=jsonObject.getJSONArray("data");
            for (int i=0;i<dataArray.length();i++){
                JSONObject dataObject=(JSONObject)dataArray.get(i);
                Title=String.valueOf(dataObject.get("tieude"));
                Image = String.valueOf(dataObject.get("hinhanh"));
                Content=String.valueOf(dataObject.get("noidung"));
                Description=String.valueOf(dataObject.get("mota"));
                listBook.add(new Baiviet(0,Title,Description,Content,Image,null,null));
            }
            bookAdapter=new BaivietAdapter(listBook,getContext());
            RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(itemDecoration);
            recyclerView.setAdapter(bookAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
