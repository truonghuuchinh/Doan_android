package com.baitap.doan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.baitap.doan.fragment.FragmentHome;
import com.baitap.doan.loadRecyclerView.AsyntaskLoader;
import com.baitap.doan.loadRecyclerView.Baiviet;
import com.baitap.doan.loadRecyclerView.BaivietAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class SearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private RecyclerView recyclerView;
    BaivietAdapter bookAdapter;
    LinkedList<Baiviet> listBook;
    SearchView searchView;
    static final int WEATHER_LOADER_ID = 1000;
    LoaderManager loaderManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView=findViewById(R.id.rv_list);
        searchView=findViewById(R.id.search_baiviet);
        searchView.requestFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                bookAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                bookAdapter.getFilter().filter(newText);
                return false;
            }
        });
        loaderManager=LoaderManager.getInstance(this);
        Bundle data = new Bundle();
        data.putString("api", "http://10.0.2.2:8000/api/baiviet");
        if (loaderManager.getLoader(WEATHER_LOADER_ID) == null) {
            loaderManager.initLoader(WEATHER_LOADER_ID, data, this);
        } else {
            loaderManager.restartLoader(WEATHER_LOADER_ID, data, this);
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyntaskLoader(this,args.getString("api"));
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
            bookAdapter=new BaivietAdapter(listBook,this);
            RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(itemDecoration);
            recyclerView.setAdapter(bookAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}