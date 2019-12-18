package com.zhy.collector.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;

/**
 * Created by zou on 2016/6/2.
 */
public class UIUtils {
    private static final String SCREE_PARAMETER_720 = "720x1280";
    private static final String SCREE_PARAMETER_1080 = "1080x1920";
    private static final String SCREE_PARAMETER_1440 = "1440x2560";
    private static final String SCREE_PARAMETER_1600 = "2560x1600";


    /**
     * 返回当前手机屏幕尺寸
     *
     * @return
     */
    public static String makeSize(Context context) {
        int screenWidth = getScreenWidth(context);
        if (screenWidth == 720) {
            return SCREE_PARAMETER_720;
        } else if (screenWidth == 1080) {
            return SCREE_PARAMETER_1080;
        } else if (screenWidth == 1440) {
            return SCREE_PARAMETER_1440;
        } else if (screenWidth == 1600) {
            return SCREE_PARAMETER_1600;
        }
        return SCREE_PARAMETER_1080;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm != null ? wm.getDefaultDisplay().getHeight() : 0;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm != null ? wm.getDefaultDisplay().getWidth() : 0;
    }

    /**
     * 获取版本名称
     *
     * @param context
     */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = null;
        try {
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null != packInfo ? packInfo.versionName : null;
    }


    /**
     * dp转px
     *
     * @param dp
     * @param context
     * @return
     */
    public static int dp2px(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    /**
     * px to dp
     *
     * @param pxValue
     * @param context
     * @return
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale);
    }


    public static float sp2px( Context context,float spValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spValue, context.getResources().getDisplayMetrics());
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * （DisplayMetrics类中属性scaledDensity）
     *
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 判断EditText是否为空
     *
     * @param editText
     * @return
     */
    public static boolean isEmptyEdit(EditText editText) {
        if (TextUtils.isEmpty(editText.getText())) {
            return true;
        }
        return false;
    }


    //获得coolie 的鍵值隊
    public HashMap<String, String> cookieParse(String cookies) {
        String[] cookieArray = cookies.split("; ");     //得到分割的cookie名值对
        HashMap<String, String> cookieMap = new HashMap<String, String>();
        for (int i = 0; i < cookieArray.length; i++) {
            String[] arr = cookieArray[i].split("=");   //得到鍵值對，key/value
            if (arr.length > 1)
                cookieMap.put(arr[0], arr[1]);
        }
        return cookieMap;
    }


    public static String getDeviceModel() {
        Build build = new Build();
        return build.MODEL;
    }


    /**
     * 使用外部浏览器打开URl地址
     *
     * @param url
     * @param context
     * @return
     */
    public static boolean redirect(String url, Context context) {
        if (TextUtils.isEmpty(url)) {
            return false;
        } else {
            try {
                Uri uri = Uri.parse(url);
                Intent intentUri = new Intent(Intent.ACTION_VIEW);
                intentUri.setData(uri);
                intentUri.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentUri);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    /**
     * 判断应用是否已安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isInstalled(Context context, String packageName) {
        boolean hasInstalled = false;
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> list = pm
                .getInstalledPackages(PackageManager.PERMISSION_GRANTED);
        for (PackageInfo p : list) {
            if (packageName != null && packageName.equals(p.packageName)) {
                hasInstalled = true;
                break;
            }
        }
        return hasInstalled;
    }


    /**
     * 跳转到应用市场下载蒲公英管理
     *
     * @param context
     */
    public static void goToMarket(Context context) throws Exception {
        Uri uri = Uri.parse("market://details?id=com.oray.pgymanager");
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(goToMarket);
    }

    /**
     * 获取手机Android系统版本
     *
     * @return
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }


    /**
     * 绘制背景图（左--》右）
     *
     * @param background
     * @return
     */
    public static GradientDrawable getBgDrawable(List<String> background) {
        int[] colors = new int[2];
        for (int i = 0; i <= 1; i++) {
            String color = background.get(i);
            color = color.replace("0x", "#");
            colors[i] = Color.parseColor(color);
        }
        return new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
    }

    /**
     * 绘制背景图（左--》右）
     *
     * @param startColor
     * @param endColor
     * @return
     */
    public static GradientDrawable getBgDrawable(int startColor, int endColor, int cornerRadius) {
        int colors[] = new int[]{startColor, endColor};
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
        gradientDrawable.setCornerRadius(cornerRadius);
        return gradientDrawable;
    }

    /**
     * 绘制背景图（上--》下）
     *
     * @param startColor
     * @param endColor
     * @return
     */
    public static GradientDrawable getBgLinearDrawable(int startColor, int endColor) {
        int colors[] = new int[]{startColor, endColor};
        return new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
    }


    /**
     * 设置Table layout 底部导航器左右间距
     *
     * @param context
     * @param tabs
     * @param leftDip
     * @param rightDip
     */
    public static void setIndicator(Context context, TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        try {
//            Field tabStrip = tabLayout.getDeclaredField("mTabStrip");
            Field tabStrip = tabLayout.getDeclaredField("slidingTabIndicator");  //API :28
            tabStrip.setAccessible(true);
            LinearLayout ll_tab = (LinearLayout) tabStrip.get(tabs);
            int left = (int) (getDisplayMetrics(context).density * leftDip);
            int right = (int) (getDisplayMetrics(context).density * rightDip);


            for (int i = 0; i < ll_tab.getChildCount(); i++) {
                View child = ll_tab.getChildAt(i);

                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                params.leftMargin = left;
                params.rightMargin = right;
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric;
    }



    /**
     * 动态设置view布局
     *
     * @param view
     * @param x
     * @param y
     */
    public static void setLayout(View view, int x, int y) {
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x, y, x + margin.width, y + margin.height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }

    /**
     * 动态设置view的大小及移动
     */
    public static void setImageViewLayout(View view, int left, int top, int right, int bottom, int height, int width) {
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(left, top, right, bottom);
        margin.width = width;
        margin.height = height;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }


    public static void setViewMargin(View v, int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(v.getLayoutParams());
        marginParams.setMargins(left, top, right, bottom);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(marginParams);
        v.setLayoutParams(layoutParams);
    }

    public static void setRelativeViewMargin(View v, int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(v.getLayoutParams());
        marginParams.setMargins(left, top, right, bottom);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginParams);
        v.setLayoutParams(layoutParams);
    }

    /**
     * @param timer
     * @param timerTask
     */
    public static void handleTimerCancel(Timer timer, TimerTask timerTask) {
        if (null != timer) {
            timer.cancel();
            timer.purge();
        }
        if (null != timerTask) {
            timerTask.cancel();
        }
    }

    /**
     * 适配Android P
     *
     * @param context
     * @param cls
     */
    public static void startActivity(Context context, Class cls) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(context, cls);
        context.startActivity(intent);
    }

    /**
     * copy日志到系统剪切版
     */
    public static void copyMessage2Clipboard(Context context, CharSequence stringBuilder) throws Exception {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (null != clipboard) {
            clipboard.setPrimaryClip(ClipData.newPlainText("Label", stringBuilder.toString()));//参数一：标签，可为空，参数二：要复制到剪贴板的文本
            if (clipboard.hasPrimaryClip()) {
                ClipData clipData = clipboard.getPrimaryClip();
                if (null != clipData)
                    clipData.getItemAt(0).getText();
            }
        }
    }

    /**
     * dismiss popwindow
     *
     * @param popupWindows
     */
    public static void dismissPopWindow(PopupWindow... popupWindows) {
        if (null == popupWindows || popupWindows.length == 0) return;
        for (PopupWindow popupWindow : popupWindows) {
            if (null != popupWindow && popupWindow.isShowing())
                popupWindow.dismiss();
        }
    }

    /**
     * dismiss dialog
     *
     * @param dialogs
     */
    public static void dismissDialog(Dialog... dialogs) {
        if (null == dialogs || dialogs.length == 0) return;
        for (Dialog dialog : dialogs) {
            if (null != dialog && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    /**
     * init popwindow common parameters
     *
     * @param rootView
     * @param popupWindow
     */
    public static void initPopWindowParams(View rootView, PopupWindow popupWindow) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)
            popupWindow.setClippingEnabled(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);//add for Android5.1 与屏幕底部标题栏显示冲突问题
        popupWindow.showAtLocation(rootView, Gravity.FILL, 0, 0);
    }

    /**
     * 获取顶部状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return resourceId > 0 ? context.getResources().getDimensionPixelSize(resourceId) : 0;
    }

    public static void cancelAnimatorSet(AnimatorSet... animatorSets) {
        if (null == animatorSets || animatorSets.length == 0) return;
        for (AnimatorSet animatorSet : animatorSets) {
            if (null != animatorSet && animatorSet.isRunning()) animatorSet.cancel();
        }
    }

    public static void cancelObjectAnimator(ObjectAnimator... objectAnimators) {
        if (null == objectAnimators || objectAnimators.length == 0) return;
        for (ObjectAnimator objectAnimator : objectAnimators) {
            if (null != objectAnimator && objectAnimator.isRunning()) objectAnimator.cancel();
        }
    }


    public static void clearCollection(Collection... collections) {
        for (Collection collection : collections) {
            if (null != collection)
                collection.clear();
        }
    }

    /**
     * 强制停止recyleview滚动
     *
     * @param recyclerView
     */
    public static void forceStopRecycleViewScroll(RecyclerView recyclerView) {
        if (null != recyclerView)
            recyclerView.dispatchTouchEvent((MotionEvent.obtain(SystemClock.uptimeMillis(),
                    SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0, 0, 0)));

    }

    /**
     * 关闭流资源
     *
     * @param resources
     */
    public static void closeResource(Closeable... resources) {
        for (Closeable resource : resources) {
            if (null != resource) {
                try {
                    resource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 关闭线程池
     *
     * @param services:线程池
     */
    public static void shutDownExecutorService(ExecutorService... services) {
        if (null != services && services.length > 0) {
            for (ExecutorService service : services) {
                if (null != service) service.shutdownNow();
            }
        }
    }

}
