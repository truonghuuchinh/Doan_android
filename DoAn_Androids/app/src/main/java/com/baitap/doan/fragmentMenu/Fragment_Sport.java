package com.baitap.doan.fragmentMenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baitap.doan.R;


public class Fragment_Sport extends Fragment {
    LoaderManager loaderManager;
    static  final  int WEATHER_LOADER_ID=1;
    public Fragment_Sport() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__sport, container, false);
    }

//    public static String Doc_noi_dung_url(String urls) {
//        StringBuilder builder = new StringBuilder();
//        String line;
//        try {
//            //create object url
//            URL url=new URL(urls);
//            //create urlconnection
//            URLConnection urlConnection=url.openConnection();
//            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            while ((line = bufferedReader.readLine()) != null) {
//                builder.append(line + "\n");
//
//            }
//            bufferedReader.close();
//            if (builder.length() == 0) {
//                return null;
//            }
//        } catch (IOException e) {
//            return null;
//        }
//        return builder.toString();
//    }
}
