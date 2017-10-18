package ganhuo.ly.com.ganhuo.mvp.home.activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;
import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.mvp.base.BaseActivity;
import ganhuo.ly.com.ganhuo.mvp.home.adapter.MyPagerAdapter;
import ganhuo.ly.com.ganhuo.mvp.home.fragment.ReadFragment;

/**
 * Created by liuyu1 on 2017/8/15.
 */

public class ReadActivity extends BaseActivity {

    private CoordinatorTabLayout mCoordinatorTabLayout;
    private ViewPager vp;
    private int[] mImageArray, mColorArray;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"Android", "iOS", "前端", "App","瞎推荐","拓展资源"};
    private int numToSetCurrentItem = 0;

    @Override
    protected void loadViewLayout() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_read);
//        CoderfunCache.isBackFromWebOrImage = true;
        numToSetCurrentItem = getIntent().getIntExtra("numToSetCurrentItem", 0);
    }

    @Override
    protected void findViewById() {
        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);
        vp = (ViewPager) findViewById(R.id.vp);

    }

    @Override
    protected void setListener() {
        mFragments = new ArrayList<>();
        for (String title : mTitles) {
            mFragments.add(ReadFragment.getInstance(title));
        }
        vp.setOffscreenPageLimit(4);
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));

        mImageArray = new int[]{R.mipmap.bg_android,
                R.mipmap.bg_ios,
                R.mipmap.bg_js,
                R.mipmap.bg_js,
                R.mipmap.bg_ios,
                R.mipmap.bg_other};
        mColorArray = new int[]{android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_purple,
                android.R.color.holo_purple,
                android.R.color.holo_green_light};

        mCoordinatorTabLayout.setTitle("分类阅读")
                .setBackEnable(true)
                .setImageArray(mImageArray, mColorArray)
                .setupWithViewPager(vp);
        mCoordinatorTabLayout.getTabLayout().setTabMode(TabLayout.MODE_SCROLLABLE);
        vp.setCurrentItem(numToSetCurrentItem);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void processLogic() {

    }

    @Override
    protected Context getActivityContext() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }
}
