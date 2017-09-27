package com.bwei.yuekao_2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    ViewPager VP;
    List<Fragment> list;

    ListView lv;
    DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inintView();
        initData();
    }

    private void initData() {
        lv = findViewById(R.id.lv);
        drawerLayout = findViewById(R.id.activity_main);
        ininData();
    }

    private void ininData() {
        List<String> lists = new ArrayList<String>();
        lists.add("网易");
        lists.add("腾讯");
        lists.add("新浪");
        lists.add("搜狐");
        ArrayAdapter<String> adaptter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lists);
        lv.setAdapter(adaptter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showcehua();
            }


        });
    }

    private void showcehua() {
        if(!drawerLayout.isDrawerOpen(Gravity.LEFT)){
            drawerLayout.openDrawer(Gravity.LEFT);
        }
        else {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
    }


    private void inintView() {

        VP = (ViewPager) findViewById(R.id.VP);
        list = new ArrayList<>();
        list.add(new Fr1());
        list.add(new Fr2());
        list.add(new Fr3());
        list.add(new Fr4());
        list.add(new Fr5());
        list.add(new Fr6());
        list.add(new Fr7());
        list.add(new Fr8());
        list.add(new Fr9());
        list.add(new Fr10());
        MypageAdapter adapter1 = new MypageAdapter(getSupportFragmentManager());
        VP.setAdapter(adapter1);



    }
    class MypageAdapter extends  FragmentPagerAdapter{

        public MypageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

}
