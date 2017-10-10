package ganhuo.ly.com.ganhuo.mvp.zhihu.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.common.Constant;
import ganhuo.ly.com.ganhuo.mvp.base.BaseFragment;
import ganhuo.ly.com.ganhuo.mvp.zhihu.adapter.ZhiPagerAdapter;

/**
 * Created by liuyu1 on 2017/8/22.
 */

public class ZhihuMainFragment extends BaseFragment {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager vp;


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
        toolbar.setTitle("知乎日报");
//        setActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_zhuhu);
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
        vp.setOffscreenPageLimit(Constant.Num.PAGE_COUNT);
        ZhiPagerAdapter adapter = new ZhiPagerAdapter(getChildFragmentManager());
        vp.setAdapter(adapter);
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
