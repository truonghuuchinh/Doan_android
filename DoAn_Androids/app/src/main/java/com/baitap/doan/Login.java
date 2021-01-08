package com.baitap.doan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.baitap.doan.fragment.FragmentHome;
import com.baitap.doan.loadRecyclerView.AsyntaskLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    public  static String  tenUser=null;
    public  static String  dateUser=null;
    public  static String  emailUser=null;
    public  static String  IdlUser=null;
    public  static  String password=null;
    //Tạo shareFreference
    SharedPreferences sharedPreferences;
    EditText editUsername, editPassword;
    CheckBox checkBox1;
    LoaderManager loaderManager;
    public static int MaDangNhap = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editUsername = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
        sharedPreferences=getSharedPreferences("dataLogin",MODE_PRIVATE);
        checkBox1=findViewById(R.id.checkBox);
        loaderManager = LoaderManager.getInstance(this);
        editUsername.setText(sharedPreferences.getString("taikhoan",""));
        editPassword.setText(sharedPreferences.getString("password",""));
        checkBox1.setChecked(sharedPreferences.getBoolean("checked",false));
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
            SharedPreferences.Editor editor=sharedPreferences.edit();
//            JSONObject jsonObject1=new JSONObject(data);
//            JSONArray dataArray=jsonObject1.getJSONArray("data");
            JSONArray dataArray=new JSONArray(data);
            for (int i=0;i<dataArray.length();i++){
                JSONObject dataObject=(JSONObject)dataArray.get(i);
                if(editUsername.getText().toString().equals(dataObject.getString("username")) && editPassword.getText().toString().equals(dataObject.getString("password"))) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    tenUser=dataObject.getString("username");
                    dateUser=dataObject.getString("ngaysinh");
                    emailUser=dataObject.getString("email");
                    IdlUser=dataObject.getString("id");
                    password=dataObject.getString("password");
                    dem++;
                    if(checkBox1.isChecked()){
                        editor.putString("taikhoan",tenUser);
                        editor.putString("password",password);
                        editor.putBoolean("checked",true);
                        editor.commit();
                    }else{
                        editor.remove("taikhoan");
                        editor.remove("password");
                        editor.remove("checked");
                        editor.commit();
                    }
                    break;
                    //Đưa dau74 liệu vào reference
                }
            }
            if(dem==0) {
                Toast.makeText(this, "Đăng nhập thất bại,tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
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
        data.putString("api", "http://10.0.2.2:8000/api/users");
        if (loaderManager.getLoader(MaDangNhap) == null) {
            loaderManager.initLoader(MaDangNhap, data, Login.this);
        } else {
            loaderManager.restartLoader(MaDangNhap, data, Login.this);
        }

    }
}