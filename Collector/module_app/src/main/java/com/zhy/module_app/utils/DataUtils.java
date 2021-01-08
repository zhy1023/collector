package com.zhy.module_app.utils;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author ； ZY
 * @date : 2020/10/30
 * @describe :
 */
public class DataUtils {

    public static int getMaxDeep(View view) {
        if (!(view instanceof ViewGroup)) return 0;
        ViewGroup viewGroup = (ViewGroup) view;
        if (viewGroup.getChildCount() == 0) return 0;
        int max = 0;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            int deep = getMaxDeep(viewGroup.getChildAt(i)) + 1;
            if (deep > max) max = deep;
        }

        return max;
    }
}
