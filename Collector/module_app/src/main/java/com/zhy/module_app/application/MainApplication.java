package com.zhy.module_app.application;

import android.app.Application;

import com.zhy.module_app.helper.AppHelper;

/**
 * @Author ；zhy
 * @ClassName: MainApplication
 * @Date : 2021/2/7 16:14
 * @Describe : MainApplication
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new AppHelper().init(this);
    }

}
