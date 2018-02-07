package com.zhao.dotsindicatordemo;

/**
 * Created by 狼 on 2017/9/28.
 * QQ 281788747 ;;
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * ┃　　　┃   神兽保佑
 * ┃　　　┃   代码无BUG！
 * ┃　　　┗━━━┓
 * ┃　　　　　　　┣┓
 * ┃　　　　　　　┏┛
 * ┗┓┓┏━┳┓┏┛
 * ┃┫┫　┃┫┫
 * ┗┻┛　┗┻┛
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * Home页面的适配器adapter
 * Created by alic on 16-4-30.
 */
public class GuideFragmentAdapter extends FragmentPagerAdapter {
    private  FragmentActivity mActivity;
    private List<Fragment> fragmentList = new ArrayList<>();

    //构造方法一 推荐
    public GuideFragmentAdapter(FragmentActivity mActivity, FragmentManager fm) {
        super(fm);
        this.mActivity = mActivity;

        this.fragmentList.add(new GuideFragment1());
        this.fragmentList.add(new GuideFragment2());
        this.fragmentList.add(new GuideFragment3());
    }

    //构造方法二
    public GuideFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}

