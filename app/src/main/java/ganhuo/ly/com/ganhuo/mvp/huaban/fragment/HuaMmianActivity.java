package ganhuo.ly.com.ganhuo.mvp.huaban.fragment;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.mvp.base.BaseActivity;
import ganhuo.ly.com.ganhuo.mvp.home.adapter.MyPagerAdapter;

/**
 * Created by liuyu1 on 2017/9/6.
 */

public class HuaMmianActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager vp;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"美图", "摄影","美食", "美女"};
    private final String[] mTitlesType = {"quotes", "photography","food_drink", "beauty"};

    private Toolbar toolbar;
    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void findViewById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        vp = (ViewPager) findViewById(R.id.vp);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic() {
        toolbar.setTitle("花瓣");
//        setActionBar(toolbar);
        vp.setOffscreenPageLimit(2);
        mFragments = new ArrayList<>();
        for (String titleType : mTitlesType) {
            mFragments.add(HuaFragment.getInstance(titleType));
        }

        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
        tabLayout.setupWithViewPager(vp);
    }

    @Override
    protected Context getActivityContext() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }
}
