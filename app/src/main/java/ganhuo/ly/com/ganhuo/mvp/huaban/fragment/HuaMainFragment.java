package ganhuo.ly.com.ganhuo.mvp.huaban.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.mvp.base.BaseFragment;
import ganhuo.ly.com.ganhuo.mvp.home.adapter.MyPagerAdapter;

/**
 * Created by liuyu1 on 2017/8/22.
 */

public class HuaMainFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager vp;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"美图", "摄影","美食", "美女"};
    private final String[] mTitlesType = {"quotes", "photography","food_drink", "beauty"};

    private Toolbar toolbar;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.activity_home, container, false);
    }

    @Override
    protected void initListener() {
        toolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        vp = (ViewPager) mRootView.findViewById(R.id.vp);
        tabLayout = (TabLayout) mRootView.findViewById(R.id.tabLayout);
    }

    @Override
    protected void loadData() {
        toolbar.setTitle("花瓣");
        vp.setOffscreenPageLimit(4);
        mFragments = new ArrayList<>();
        for (String titleType : mTitlesType) {
            mFragments.add(HuaFragment.getInstance(titleType));
        }

        vp.setAdapter(new MyPagerAdapter(getChildFragmentManager(), mFragments, mTitles));
        tabLayout.setupWithViewPager(vp);
    }


    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {

            }
            return true;
        }
    };
}
