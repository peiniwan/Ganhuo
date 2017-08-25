package ganhuo.ly.com.ganhuo.mvp.zhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import ganhuo.ly.com.ganhuo.MyApplication;
import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.common.Constant;
import ganhuo.ly.com.ganhuo.mvp.zhihu.fragment.ZhihuFragment;


/**
 * Created by liuyu1 on 2017/8/22.
 */

public class ZhiPagerAdapter extends FragmentStatePagerAdapter {


    public ZhiPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Calendar dateToGetUrl = Calendar.getInstance();
        dateToGetUrl.add(Calendar.DAY_OF_YEAR, 1 - i);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);
        String date = simpleDateFormat.format(dateToGetUrl.getTime());
        ZhihuFragment newFragment = ZhihuFragment.getInstance(date, i);
        return newFragment;
    }

    @Override
    public int getCount() {
        return Constant.Num.PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Calendar displayDate = Calendar.getInstance();
        displayDate.add(Calendar.DAY_OF_YEAR, -position);

        return (position == 0 ? MyApplication.getInstance().getString(R.string.zhihu_daily_today) + " " : "")
                + DateFormat.getDateInstance().format(displayDate.getTime());
    }
}
