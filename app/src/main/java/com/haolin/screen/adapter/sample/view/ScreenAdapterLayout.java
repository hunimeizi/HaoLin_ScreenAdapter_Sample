package com.haolin.screen.adapter.sample.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.haolin.screen.adapter.sample.utils.ScreenUtils;

/**
 * 作者：haoLin_Lee on 2019/06/17 18:48
 * 邮箱：Lhaolin0304@sina.com
 * class: 自定义布局
 */
public class ScreenAdapterLayout extends RelativeLayout {

    private boolean flage;

    public ScreenAdapterLayout(Context context) {
        super(context);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!flage) {
            float scalX = ScreenUtils.getInstance(getContext()).getHorizontalScale();
            float scalY = ScreenUtils.getInstance(getContext()).getVerticalScale();
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                layoutParams.width *= scalX;
                layoutParams.height *= scalY;

                layoutParams.leftMargin *= scalX;
                layoutParams.rightMargin *= scalX;

                layoutParams.topMargin *= scalY;
                layoutParams.bottomMargin *= scalY;

            }
        }
        flage = true;
    }
}
