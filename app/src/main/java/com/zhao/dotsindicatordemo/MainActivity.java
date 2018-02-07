package com.zhao.dotsindicatordemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zhao.indicatorlibrary.view.GuideInstructionView;

import static com.zhao.dotsindicatordemo.R.id.guide_view;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private GuideInstructionView mGuideInstructionView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGuideInstructionView = (GuideInstructionView) findViewById(guide_view);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        //设置数据适配器；
        GuideFragmentAdapter adapter  = new  GuideFragmentAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mGuideInstructionView.setProgress(position, positionOffset);
    }

    @Override
    public void onPageSelected(int position) {








    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
