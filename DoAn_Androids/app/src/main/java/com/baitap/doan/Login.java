package com.baitap.doan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.baitap.doan.fragment.FragmentHome;
import com.baitap.doan.loadRecyclerView.AsyntaskLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {


    EditText editUsername, editPassword;
    LoaderManager loaderManager;
    public static int MaDangNhap = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        editUsername = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);

        loaderManager = LoaderManager.getInstance(this);
    }

    public void startSignUp(View view) {
        Intent intent=new Intent(Login.this,SigUp.class);
        startActivity(intent);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyntaskLoader(this,args.getString("api"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            int dem = 0;
            JSONObject jsonObject = new JSONObject(data);
            Log.d("test",jsonObject.toString());
            JSONArray dataArray=jsonObject.getJSONArray("data");
            for (int i=0;i<dataArray.length();i++){
                JSONObject dataObject=(JSONObject)dataArray.get(i);
                if(editUsername.getText().toString().equals(dataObject.getString("username")) && editPassword.getText().toString().equals(dataObject.getString("password"))) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    dem++;
                }
            }
            if(dem==0) {
                Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void dangNhap(View view) {
        Bundle data = new Bundle();
        data.putString("api", "http://10.0.2.2:8000//apiusers");
        if (loaderManager.getLoader(MaDangNhap) == null) {
            loaderManager.initLoader(MaDangNhap, data, Login.this);
        } else {
            loaderManager.restartLoader(MaDangNhap, data, Login.this);
        }

    }
}