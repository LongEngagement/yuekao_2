package com.bwei.yuekao_2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/**
*@作者：王京京
 * @创建日期  :
 *类的用途：
*/
/**
 * Created by Long° Engagement on 2017/8/23.
 */
public class Fr1 extends Fragment{
         String address = "http://v.juhe.cn/toutiao/index?type=top&key=dbedecbcd1899c9785b95cc2d17131c5";
        ImageLoader imageLoader;
        //定义一个PullToRefreshListView
        PullToRefreshListView mPullRefreshListView;
        List<Jiexi.ResultBean.DataBean> data;
      //定义一个可以放他的集合
        //
/**
*  @作者：王京京
 * @创建日期 2017/9/26 14:08
 *类的用途：
*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = View.inflate(getActivity(),R.layout.f1,null);
        mPullRefreshListView = (PullToRefreshListView)view.findViewById(R.id.pull_refresh_list);
        mPullRefreshListView.setScrollingWhileRefreshingEnabled(true);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        new MyTasy().execute(address);
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new MyTasy().execute(address);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                address = address+"&page+1";
                new MyTasy().execute(address);

            }
        });
        mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),"我点击过了",Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }


    class MyTasy extends AsyncTask<String,Integer,String>{
        @Override
        protected String doInBackground(String... strings) {
            String all = "";
            try {
                URL url = new URL(address);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                int code = connection.getResponseCode();
                if(code==200) {
                    InputStream is = connection.getInputStream();
                    int length = 0;
                    byte [] buff = new byte[1024];
                    while ((length=is.read(buff))!=-1) {
                        String string = new String(buff,0,length);
                        all+=string;
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return all;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            Jiexi jiexi = gson.fromJson(s,Jiexi.class);
            data  = jiexi.getResult().getData();
            MyAdapter adapter2 =  new MyAdapter();
            mPullRefreshListView.setAdapter(adapter2);
            mPullRefreshListView.onRefreshComplete();
        }
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHodler hodler;
            if(convertView==null){
                convertView =View.inflate(getActivity(),R.layout.list_1,null);
                hodler = new ViewHodler();
                hodler.iv = (ImageView) convertView.findViewById(R.id.iv);
                hodler.tv  = (TextView) convertView.findViewById(R.id.tv);
                convertView.setTag(hodler);
            }else{
                hodler = (ViewHodler) convertView.getTag();
            }
            hodler.tv.setText(data.get(position).getTitle());

            ImageLoader.getInstance().displayImage(data.get(position).getThumbnail_pic_s(),hodler.iv);
            return convertView;
        }
    }
    class ViewHodler{
        ImageView iv;
        TextView tv;
    }

}



