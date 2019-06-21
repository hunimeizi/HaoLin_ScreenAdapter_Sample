package com.haolin.screen.adapter.sample.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.haolin.screen.adapter.sample.R;

/**
 * 作者：haoLin_Lee on 2019/06/18 16:05
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public class PercentLayout extends RelativeLayout {

    public PercentLayout(Context context) {
        super(context);
    }

    public PercentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int withSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams params = child.getLayoutParams();
            if (checkLayoutParams(params)) {
                LayoutParams layoutParams = (LayoutParams) params;
                float withPercent = layoutParams.withPercent;
                float heightPercent = layoutParams.heightPercent;
                float marginLeftPercent = layoutParams.marginLeftPercent;
                float marginRightPercent = layoutParams.marginRightPercent;
                float marginTopPercent = layoutParams.marginTopPercent;
                float marginBottomPercent = layoutParams.marginBottomPercent;
                if (withPercent > 0) {
                    params.width = (int) (withSize * withPercent);
                }
                if (heightPercent>0){
                    params.height = (int) (heightSize * heightPercent);
                }
                if (marginLeftPercent>0){
                    ((LayoutParams) params).marginLeftPercent = (int) (withSize * marginLeftPercent);
                }
                if (marginRightPercent>0){
                    ((LayoutParams) params).marginRightPercent = (int) (withSize * marginRightPercent);
                }
                if (marginTopPercent>0){
                    ((LayoutParams) params).marginTopPercent = (int) (heightSize * marginTopPercent);
                }
                if (marginBottomPercent>0){
                    ((LayoutParams) params).marginBottomPercent = (int) (heightSize * marginBottomPercent);
                }
            }
        }
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    private static class LayoutParams extends RelativeLayout.LayoutParams {

        float withPercent;
        float heightPercent;
        float marginLeftPercent;
        float marginRightPercent;
        float marginTopPercent;
        float marginBottomPercent;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            @SuppressLint("CustomViewStyleable") TypedArray typedArray = c.obtainStyledAttributes(attrs, R.styleable.PercentLayout);
            withPercent = typedArray.getFloat(R.styleable.PercentLayout_withPercent, 0);
            heightPercent = typedArray.getFloat(R.styleable.PercentLayout_heightPercent, 0);
            marginLeftPercent = typedArray.getFloat(R.styleable.PercentLayout_marginLeftPercent, 0);
            marginRightPercent = typedArray.getFloat(R.styleable.PercentLayout_marginRightPercent, 0);
            marginTopPercent = typedArray.getFloat(R.styleable.PercentLayout_marginRightPercent, 0);
            marginBottomPercent = typedArray.getFloat(R.styleable.PercentLayout_marginBottomPercent, 0);
            typedArray.recycle();
        }
    }
}
