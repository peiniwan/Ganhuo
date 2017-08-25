package ganhuo.ly.com.ganhuo.mvp.home.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;
import java.util.concurrent.TimeUnit;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.common.Constant;
import ganhuo.ly.com.ganhuo.mvp.base.BaseFragment;
import ganhuo.ly.com.ganhuo.mvp.entity.DataResults;
import ganhuo.ly.com.ganhuo.mvp.entity.Results;
import ganhuo.ly.com.ganhuo.mvp.home.adapter.ReadAdapter;
import ganhuo.ly.com.ganhuo.mvp.home.presenter.HomePresenter;
import ganhuo.ly.com.ganhuo.mvp.home.view.HomeFragmentView;
import ganhuo.ly.com.ganhuo.util.SPUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by liuyu1 on 2017/8/15.
 */

public class ReadFragment extends BaseFragment implements HomeFragmentView {
    private SwipyRefreshLayout swipyRefreshLayout;
    private RecyclerView recyclerview;
    private ReadAdapter readAdapter;

    private static final String ARG_TITLE = "title";
    private String mTitle;
    private static int read_num = Constant.Num.READ_NUM;
    private static int NOW_PAGE_READ = 1;
    private boolean isTop = true;
    private HomePresenter homePresenter;

    public static ReadFragment getInstance(String title) {
        ReadFragment fra = new ReadFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        fra.setArguments(bundle);
        return fra;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        mTitle = bundle.getString(ARG_TITLE);
        homePresenter = new HomePresenter(this);

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    protected void initListener() {
        initRecyclerView();
        initSwipyRefreshLayout();

    }

    private void initSwipyRefreshLayout() {
        swipyRefreshLayout = (SwipyRefreshLayout) mRootView.findViewById(R.id.swipyrefreshlayout);
        swipyRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);

        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                Log.d("HomeActivity", "Refresh triggered at "
                        + (direction == SwipyRefreshLayoutDirection.TOP ? "top" : "bottom"));
                Observable.timer(2, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                swipyRefreshLayout.setRefreshing(false);
                            }
                        });
                isTop = direction == SwipyRefreshLayoutDirection.TOP ? true : false;
                getData(false);

            }
        });

    }

    private void initRecyclerView() {
        recyclerview = (RecyclerView) mRootView.findViewById(R.id.recyclerView);

        recyclerview.setLayoutManager(new LinearLayoutManager(recyclerview.getContext()));
        readAdapter = new ReadAdapter(getActivity(), null);
        recyclerview.setAdapter(readAdapter);
    }


    @Override
    protected void loadData() {
        boolean isFirst = (boolean) SPUtils.get(getActivity(), "isFirst", false);
        if (!isFirst) {
            SPUtils.get(getActivity(), "isFirst", true);
        }
        getData(isFirst);
    }

    private void getData(boolean isUseCache) {
        if (isTop) {
            NOW_PAGE_READ = 1;
        }
        homePresenter.getDataResults(isUseCache, mTitle, read_num, NOW_PAGE_READ);

    }

    @Override
    public void newDatas(DataResults dataResults) {
        if (dataResults.isError()) {
            Snackbar.make(recyclerview, "啊擦，服务器出问题啦", Snackbar.LENGTH_SHORT).show();
        } else {

            if (isTop) {
                clearAdapterResults();
            }
            dealWithDataInRecyclerView(dataResults.getResults());
        }
    }

    private void clearAdapterResults() {
        readAdapter.getResults().clear();
    }

    private void dealWithDataInRecyclerView(List<Results> read_list) {
        readAdapter.getResults().addAll(read_list);
        readAdapter.notifyDataSetChanged();
        NOW_PAGE_READ++;
    }

    @Override
    public void showLoadFailMsg() {
        Snackbar.make(recyclerview, "网络不顺畅嘞,更新不了数据啦", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


}
