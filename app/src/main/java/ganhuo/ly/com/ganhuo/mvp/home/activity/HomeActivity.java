package ganhuo.ly.com.ganhuo.mvp.home.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.mvp.base.BaseActivity;
import ganhuo.ly.com.ganhuo.mvp.home.adapter.MyPagerAdapter;
import ganhuo.ly.com.ganhuo.mvp.home.fragment.DiscoveryFragment;

public class HomeActivity extends BaseActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager vp;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"首页", "干货", "妹纸"};

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void findViewById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        vp = (ViewPager) findViewById(R.id.vp);

        setSupportActionBar(toolbar);
    }

    @Override
    protected void setListener() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(vp);
    }

    @Override
    protected void processLogic() {
        mFragments = new ArrayList<>();
        for (String title : mTitles) {
            mFragments.add(DiscoveryFragment.getInstance(title));
        }
        vp.setOffscreenPageLimit(3);
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_read:
                Intent intent_read = new Intent(this, ReadActivity.class);
                startActivity(intent_read);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
