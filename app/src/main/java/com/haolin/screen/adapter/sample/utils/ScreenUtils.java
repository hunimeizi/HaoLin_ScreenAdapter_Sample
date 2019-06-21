package com.haolin.screen.adapter.sample.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * 作者：haoLin_Lee on 2019/06/17 17:23
 * 邮箱：Lhaolin0304@sina.com
 * class: 屏幕适配工具类 真实手机屏幕长宽 与 切图UI长宽比例
 */
public class ScreenUtils {

    private static ScreenUtils screenUtils;

    private int mDisplayWidth;
    private int mDisplayHeight;

    // UI设计师切图的模型 大小尺寸
    private static final float STANDARAD_WIDTH = 720;
    private static final float STANDARAD_HEIGTH = 1280;

    private ScreenUtils(Context context) {
        if (mDisplayWidth == 0 || mDisplayHeight == 0) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                    mDisplayWidth = displayMetrics.heightPixels;
                    mDisplayHeight = displayMetrics.widthPixels;
                } else {
                    mDisplayWidth = displayMetrics.widthPixels;
                    mDisplayHeight = displayMetrics.heightPixels - getStatusBarHeight(context);
                }
            }
        }
    }

    private int getStatusBarHeight(Context context) {
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return resId > 0 ? context.getResources().getDimensionPixelOffset(resId) : 0;
    }

    public static ScreenUtils getInstance(Context context) {

        if (screenUtils == null) {
            synchronized (ScreenUtils.class) {
                if (screenUtils == null) {
                    screenUtils = new ScreenUtils(context.getApplicationContext());
                }
            }
        }
        return screenUtils;
    }

    /**
     * 水平方向的缩放比例
     *
     * @return float
     */
    public float getHorizontalScale() {
        return mDisplayWidth / STANDARAD_WIDTH;
    }

    /**
     * 竖屏方向的缩放比例
     *
     * @return float
     */
    public float getVerticalScale() {
        return mDisplayHeight / STANDARAD_HEIGTH;
    }
}
