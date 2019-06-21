package com.haolin.screen.adapter.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * 作者：haoLin_Lee on 2019/06/21 10:54
 * 邮箱：Lhaolin0304@sina.com
 * class:
 */
public class ThreeActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
    }
    public void toClickFour(View view) {
        startActivity(new Intent(ThreeActivity.this, DisplayCutoutActivity.class));
    }
}
