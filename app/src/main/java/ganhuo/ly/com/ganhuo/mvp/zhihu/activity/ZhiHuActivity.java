package ganhuo.ly.com.ganhuo.mvp.zhihu.activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.common.Constant;
import ganhuo.ly.com.ganhuo.mvp.base.BaseActivity;
import ganhuo.ly.com.ganhuo.mvp.zhihu.adapter.ZhiPagerAdapter;

/**
 * Created by liuyu1 on 2017/8/22.
 */

public class ZhiHuActivity extends BaseActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager vp;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_zhihu);
    }

    @Override
    protected void findViewById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        vp = (ViewPager) findViewById(R.id.vp);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }

    @Override
    protected void setListener() {
        toolbar.setTitle("知乎日报");
//        setActionBar(toolbar);
        vp.setOffscreenPageLimit(Constant.Num.PAGE_COUNT);
        ZhiPagerAdapter adapter = new ZhiPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        tabLayout.setupWithViewPager(vp);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_zhuhu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_go_to_search:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
