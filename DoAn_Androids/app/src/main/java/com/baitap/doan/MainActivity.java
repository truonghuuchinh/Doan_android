package com.baitap.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.FragmentTransitionSupport;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baitap.doan.fragment.FragmentContainHome;
import com.baitap.doan.fragment.FragmentHome;
import com.baitap.doan.fragmentMenu.Fragment_Sport;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private  static   int Flag=0;
    SearchView searchView;
    //Phần menu
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationViews;
    ListView listView;
    //Item menu
    ArrayList<ItemMenu> arrayList;
    MenuAdapter menuAdapter;
    //Phần nội dung ứng dụng
    BottomNavigationView navigationView;
    ViewPager viewPager;
    //Tablayout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        actionMenu();
        setUpViewPager();
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case  R.id.action_home:
                       viewPager.setCurrentItem(0);
                        break;
                    case  R.id.action_hotNews:
                        viewPager.setCurrentItem(1);
                        break;
                    case  R.id.action_favorite:
                        viewPager.setCurrentItem(2);
                        break;
                    case  R.id.action_Account:
                        viewPager.setCurrentItem(3);
                        break;
                    default:
                        viewPager.setCurrentItem(0);
                        break;
                }
                return true;
            }
        });
    }
    void Anhxa(){
        viewPager=findViewById(R.id.view_item);
        navigationView=findViewById(R.id.bottom_nav);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        navigationViews=(NavigationView)findViewById(R.id.navigation_view);
        searchView=(SearchView)findViewById(R.id.search_bar);
        listView=(ListView)findViewById(R.id.listview);
    }
    private void actionMenu() {
        arrayList=new ArrayList<ItemMenu>();
        arrayList.add(new ItemMenu("Thể Thao",R.drawable.football));
        arrayList.add(new ItemMenu("Thời Sự",R.drawable.newspaper));
        arrayList.add(new ItemMenu("Âm Nhạc",R.drawable.music));
        arrayList.add(new ItemMenu("Pháp Luật",R.drawable.auction));
        arrayList.add(new ItemMenu("Giáo Dục",R.drawable.presentation));
        arrayList.add(new ItemMenu("Du Lịch",R.drawable.dulich));
        arrayList.add(new ItemMenu("Khoa Học",R.drawable.laboratory));
        arrayList.add(new ItemMenu("Xe",R.drawable.car));
        menuAdapter=new MenuAdapter(this,R.layout.row_item,arrayList);
        listView.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();

    }
    private  void setUpViewPager(){
        Viewpager_Adapter viewpager_adapter=new Viewpager_Adapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewpager_adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(Flag==1){
                }else {
                    if (position == 3) {
                        viewPager.setCurrentItem(3);
                    }
                }

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.action_hotNews).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.action_favorite).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.action_Account).setChecked(true);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void actionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void startMenu(View view) {
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void clickMenuItem(View view) {
        int n=1,position=0;
        LinearLayout layout = (LinearLayout)view;
        TextView txt = (TextView)layout.findViewById(R.id.txt_name);
        if(txt.getText().toString().contains("Thể Thao")) {
            position=1;
        }
        if(txt.getText().toString().contains("Thời Sự")){
            position=2;
        }
        if(txt.getText().toString().contains("Âm Nhạc")){
           position=3;

        }
        if(txt.getText().toString().contains("Pháp Luật")){
            position=4;
        }
        if(txt.getText().toString().contains("Giáo Dục")){
            position=5;
        }
        if(txt.getText().toString().contains("Du Lịch")){
            position=6;
        }
        if(txt.getText().toString().contains("Khoa Học")){
            position=7;
        }
        if(txt.getText().toString().contains("Xe")){
            position=8;
        }
        FragmentContainHome.viewPager.setCurrentItem(position);
        Flag=n;
            Toast.makeText(MainActivity.this, txt.getText().toString(), Toast.LENGTH_SHORT).show();
            drawerLayout.closeDrawer(GravityCompat.START);
            navigationView.getMenu().findItem(R.id.action_home).setChecked(true);
    }


}