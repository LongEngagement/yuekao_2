package com.bwei.yuekao_2;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Dabin on 2017/8/23.
 */

public class MyAPP extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration aDefault = ImageLoaderConfiguration.createDefault(getApplicationContext());
        ImageLoader.getInstance().init(aDefault);
    }
}
