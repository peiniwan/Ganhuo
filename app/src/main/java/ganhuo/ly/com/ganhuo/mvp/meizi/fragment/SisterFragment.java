package ganhuo.ly.com.ganhuo.mvp.meizi.fragment;

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
import ganhuo.ly.com.ganhuo.mvp.home.fragment.DiscoveryFragment;

/**
 * Created by liuyu1 on 2017/8/22.
 */

public class SisterFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager vp;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"妹纸", "花瓣"};
    private Toolbar toolbar;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.activity_zhihu, container, false);
    }

    @Override
    protected void initListener() {
        toolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        vp = (ViewPager) mRootView.findViewById(R.id.vp);
        tabLayout = (TabLayout) mRootView.findViewById(R.id.tabLayout);
    }

    @Override
    protected void loadData() {
        toolbar.setTitle("妹子");
//        setActionBar(toolbar);
        vp.setOffscreenPageLimit(2);
        mFragments = new ArrayList<>();

        mFragments.add(DiscoveryFragment.getInstance(mTitles[0]));
        mFragments.add(HuaFragment.getInstance());

        vp.setAdapter(new MyPagerAdapter(getFragmentManager(), mFragments, mTitles));
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
