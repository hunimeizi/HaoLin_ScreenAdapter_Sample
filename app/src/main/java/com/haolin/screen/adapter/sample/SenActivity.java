package com.haolin.screen.adapter.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * 作者：haoLin_Lee on 2019/06/18 16:28
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public class SenActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sen);
    }
    public void toClickThree(View view) {
        startActivity(new Intent(SenActivity.this, ThreeActivity.class));
    }
}
