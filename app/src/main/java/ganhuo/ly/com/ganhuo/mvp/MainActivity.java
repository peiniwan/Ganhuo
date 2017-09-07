package ganhuo.ly.com.ganhuo.mvp;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.mvp.base.BaseActivity;
import ganhuo.ly.com.ganhuo.mvp.home.activity.HomeActivity;
import ganhuo.ly.com.ganhuo.mvp.home.activity.ReadActivity;
import ganhuo.ly.com.ganhuo.mvp.home.fragment.GanHuoFragment;
import ganhuo.ly.com.ganhuo.mvp.huaban.fragment.HuaMainFragment;
import ganhuo.ly.com.ganhuo.mvp.huaban.fragment.HuaMmianActivity;
import ganhuo.ly.com.ganhuo.mvp.zhihu.fragment.ZhihuMainFragment;
import ganhuo.ly.com.ganhuo.util.PerfectClickListener;

/**
 * Created by liuyu1 on 2017/8/24.
 */

public class MainActivity extends BaseActivity {

    NavigationView mNavView;
    DrawerLayout mDrawerLayout;
    private long mExitTime = 0;
    private FrameLayout fl_content;
    private GanHuoFragment ganHuoFragment;
    private ZhihuMainFragment zhihuFragment;
    private HuaMainFragment huaMainFragment;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void findViewById() {
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        mNavView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.mainActivity);

    }


    @Override
    protected void setListener() {
        initDrawerLayout();
    }


    private void initDrawerLayout() {
        mNavView.inflateHeaderView(R.layout.layout_main_nav);
        View headerView = mNavView.getHeaderView(0);
        headerView.findViewById(R.id.ll_nav_zhihu).setOnClickListener(mListener);
        headerView.findViewById(R.id.ll_nav_daima).setOnClickListener(mListener);
        headerView.findViewById(R.id.ll_nav_exit).setOnClickListener(mListener);
        headerView.findViewById(R.id.ll_nav_sister).setOnClickListener(mListener);

    }

    private PerfectClickListener mListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(final View v) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            mDrawerLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    switch (v.getId()) {
                        case R.id.ll_nav_zhihu:
                            getSupportFragmentManager().beginTransaction().show(zhihuFragment).hide(ganHuoFragment).hide(huaMainFragment).commit();
                            break;
                        case R.id.ll_nav_daima:
                            //换到首页也无法显示
//                            getSupportFragmentManager().beginTransaction().show(ganHuoFragment).hide(zhihuFragment).hide(huaMainFragment).commit();
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.ll_nav_sister:
//                            getSupportFragmentManager().beginTransaction().show(huaMainFragment).hide(zhihuFragment).hide(ganHuoFragment).commit();
                            Intent intent1 = new Intent(MainActivity.this, HuaMmianActivity.class);
                            startActivity(intent1);
                            break;
                        case R.id.ll_nav_exit:
                            finish();
                            break;
                        default:
                            break;
                    }
                }
            }, 260);
        }
    };

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Snackbar.make(mDrawerLayout, R.string.exit_toast, Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
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

    @Override
    protected void processLogic() {
        ganHuoFragment = new GanHuoFragment();
        zhihuFragment = new ZhihuMainFragment();
        huaMainFragment = new HuaMainFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_content, zhihuFragment).
                add(R.id.fl_content, ganHuoFragment).
                add(R.id.fl_content, huaMainFragment).
                show(zhihuFragment).hide(ganHuoFragment).hide(huaMainFragment).commit();
    }

    @Override
    protected Context getActivityContext() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }
}
