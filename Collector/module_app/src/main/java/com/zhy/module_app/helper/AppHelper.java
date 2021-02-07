/*
 *  Copyright (c) 2021 Oray Inc. All rights reserverd.
 *  No Part of this file may be reproduced, stored
 *  in a retrieval system, or transmitted, in any form, or by any means,
 *  electronic, mechanical, photocopying, recording, or otherwise,
 *  without the prior consent of Oray Inc.
 */

package com.zhy.module_app.helper;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.model.HttpHeaders;
import com.zhy.lib_common.BuildConfig;
import com.zhy.module_app.application.MainApplication;

import skin.support.SkinCompatManager;
import skin.support.app.SkinAppCompatViewInflater;


/**
 * @Author ；zhy
 * @ClassName: AppHelper
 * @Date : 2021/1/11 13:57
 * @Describe : AppHelper App辅助类
 */
public class AppHelper {
    private static MainApplication mContext;

    /**
     * 应用及第三方SDK初始化
     */
    public void init(MainApplication application) {
        initContext(application);
        //初始化EasyHttp网络请求架构
        initRxEasyHttp();
        //初始化换肤
//        initSkin();
        //EventBus初始化
//        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        //webview多进程处理
//        WebViewUtils.setDataSuffix(mContext);
    }

    private void initContext(MainApplication application) {
        mContext = application;
    }

    /**
     * 获取全局Context对象
     *
     * @return
     */
    public static Context getContext() {
        return mContext;
    }



    /**
     * 初始化RxEasyHttp
     */
    private void initRxEasyHttp() {
        EasyHttp.init(mContext);
        HttpHeaders headers = new HttpHeaders();
//        headers.put(AppConstant.USER_AGENT, AppUtils.getUserAgent());
        EasyHttp.getInstance()
//                .addHeaderInterceptor(new HttpResponseInterceptor())
//                .addCommonHeaders(headers)
                .debug(EasyHttp.class.getSimpleName(), !BuildConfig.isRelease)  //网络请求调试
                .setRetryCount(1)//网络不好自动重试次数
                .setRetryDelay(500)//每次延时500ms重试
                .setRetryIncreaseDelay(500)//每次延时叠加500ms
                .setReadTimeOut(20 * 1000)
                .setConnectTimeout(20 * 1000);
    }


    /**
     * 适配Android P
     *
     * @param cls
     */
    public static void startActivity(Context context, Class<? extends Activity> cls) {
        startActivity(context, cls, null);
    }

    /**
     * 适配Android P
     *
     * @param cls
     */
    public static void startActivity(Context context, Class<? extends Activity> cls, Intent intent) {
        if (null == intent) intent = new Intent();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.setClass(context, cls);
        context.startActivity(intent);
    }


    /**
     * 初始化换肤
     */
    private void initSkin() {
        SkinCompatManager.withoutActivity(mContext)
                .addInflater(new SkinAppCompatViewInflater())
                .loadSkin();
    }


    /**
     * 控制连接按钮点击的时间间隔，防止连续点击操作
     * MIN_CLICK_DELAY_TIME: 两次点击按钮之间的点击间隔不能少于1000毫秒
     */
    public static abstract class OnMultiClickListener implements View.OnClickListener {
        private long lastClickTime;

        public abstract void onMultiClick(View v);

        @Override
        public void onClick(View v) {
            long curClickTime = System.currentTimeMillis();
            if ((curClickTime - lastClickTime) >= 1000) {
                lastClickTime = curClickTime;
                onMultiClick(v);
            }
        }
    }



}
