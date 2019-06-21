package com.haolin.screen.adapter.sample;

import android.app.Activity;
import android.os.Bundle;

import com.haolin.screen.adapter.sample.utils.Density;

/**
 * 作者：haoLin_Lee on 2019/06/21 10:54
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Density.setDensity(getApplication(), this);
    }
}
