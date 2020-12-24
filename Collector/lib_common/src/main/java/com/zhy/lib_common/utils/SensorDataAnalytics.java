/*
 *  Copyright (c) 2019 Oray Inc. All rights reserverd.
 *  No Part of this file may be reproduced, stored
 *  in a retrieval system, or transmitted, in any form, or by any means,
 *  electronic, mechanical, photocopying, recording, or otherwise,
 *  without the prior consent of Oray Inc.
 *
 *  Author: ZY
 *  Create on:  19-9-23 上午10:36
 *  FileName:SensorDataAnalytics.java
 */

package com.zhy.lib_common.utils;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebView;

import com.sensorsdata.analytics.android.sdk.SAConfigOptions;
import com.sensorsdata.analytics.android.sdk.SensorsAnalyticsAutoTrackEventType;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.umeng.analytics.AnalyticsConfig;
import com.yanzhenjie.permission.AndPermission;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by qianwei on 2019/9/18.
 * 神策埋点
 */
public class SensorDataAnalytics {

    private static final String TAG = SensorDataAnalytics.class.getSimpleName();

    private static final String SENSOR_ANALYTICS_URL = "http://pgyapp-tk.oray.com/pgyapp-track";

    /**
     * 初始化 神策统计
     *
     * @param context
     */
    public static void initAnalytics(Context context) {
        SAConfigOptions saConfigOptions = new SAConfigOptions(SENSOR_ANALYTICS_URL);
        saConfigOptions.setAutoTrackEventType(SensorsAnalyticsAutoTrackEventType.APP_START |    // App 启动事件(超过30s)
                SensorsAnalyticsAutoTrackEventType.APP_END)                                     // App 退出事件(超过30s)
                .enableLog(false)
                .enableTrackAppCrash();
        SensorsDataAPI.startWithConfigOptions(context, saConfigOptions);
    }

    /**
     * H5 使用js SDK 采集到数据发送到 App端
     *
     * @param view
     */
    public static void showSupportWebView(WebView view) {
        SensorsDataAPI.sharedInstance().showUpWebView(view, false, true);
    }

    /**
     * 发送点击事件信息
     *
     * @param elementName
     * @param screenName
     */
    public static void sendSensorEvent(String screenName,String elementName) {
        sendSensorEvent(screenName,elementName, null);
    }

    /**
     * 发送点击事件信息
     */
    public static void sendSensorEvent(String screenName,String eleName, String eleContent) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("$screen_name",screenName);
            jsonObject.put("$element_name", eleName);
            if (!TextUtils.isEmpty(eleContent))
                jsonObject.put("$element_content", eleContent);
            SensorsDataAPI.sharedInstance().track("$AppClick", jsonObject);
        } catch (JSONException e) {
            LogUtils.i(TAG, "sendSensorEvent Error :" + e.getMessage());
        }
    }

    /**
     * 注册事件信息
     */
    public static void registerSensor() {
        SensorsDataAPI.sharedInstance().track("SignUp");
    }

    /**
     * 登录事件信息
     */
    public static void loginSensor() {
        SensorsDataAPI.sharedInstance().track("Login");
    }


    /**
     * 配置公共属性 UserId信息
     * 之后发送的事件会携带用户信息
     *
     * @param userId 用户id
     */
    public static void sendRegisterCommonProperty(String userId) {
        try {
            JSONObject properties = new JSONObject();
            properties.put("userid", userId);
            SensorsDataAPI.sharedInstance().registerSuperProperties(properties);
        } catch (Exception e) {
            LogUtils.i(TAG, "sendRegisterCommonProperty Error :" + e.getMessage());
        }
    }

    /**
     * 取消公共属性配置信息
     * Logout时候调用
     */
    public static void unRegisterCommonProperty() {
        SensorsDataAPI.sharedInstance().clearSuperProperties();
    }

    /**
     * 统计Apk安装量
     *
     *
     * @param context
     */
    public static void trackInstallation(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || AndPermission.hasPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
            try {
                String channel_name = AnalyticsConfig.getChannel(context);
                JSONObject channelInfo = new JSONObject();
                channelInfo.put("channel", channel_name);
                SensorsDataAPI.sharedInstance().trackInstallation("AppInstall", channelInfo);
            } catch (Exception e) {
                LogUtils.i(TAG, "trackInstallation :" + e.getMessage());
            }
        }
    }
}
