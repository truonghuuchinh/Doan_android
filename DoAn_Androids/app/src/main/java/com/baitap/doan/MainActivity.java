package com.baitap.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.view_item);
        navigationView=findViewById(R.id.bottom_nav);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        navigationViews=(NavigationView)findViewById(R.id.navigation_view);
        listView=(ListView)findViewById(R.id.listview);
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
                }
                return true;
            }
        });
    }
    private void actionMenu() {
        arrayList=new ArrayList<ItemMenu>();
        arrayList.add(new ItemMenu("Sport",R.drawable.ic_action_menu));
        arrayList.add(new ItemMenu("Category",R.drawable.ic_action_menu));
        arrayList.add(new ItemMenu("Music",R.drawable.ic_action_menu));
        arrayList.add(new ItemMenu("transport",R.drawable.ic_action_menu));
        menuAdapter=new MenuAdapter(this,R.layout.row_item,arrayList);
        listView.setAdapter(menuAdapter);

    }
    private  void setUpViewPager(){
        Viewpager_Adapter viewpager_adapter=new Viewpager_Adapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewpager_adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

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

}