package ganhuo.ly.com.ganhuo.mvp.home.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.common.Constant;
import ganhuo.ly.com.ganhuo.mvp.base.BaseFragment;
import ganhuo.ly.com.ganhuo.mvp.entity.DataResults;
import ganhuo.ly.com.ganhuo.mvp.entity.Results;
import ganhuo.ly.com.ganhuo.mvp.home.adapter.PartAdapter;
import ganhuo.ly.com.ganhuo.mvp.home.presenter.HomePresenter;
import ganhuo.ly.com.ganhuo.mvp.home.view.HomeFragmentView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by liuyu1 on 2017/8/1.
 */

public class DiscoveryFragment extends BaseFragment implements HomeFragmentView {

    RecyclerView recyclerview;
    SwipyRefreshLayout swipyRefreshLayout;
    //    private GirlyAdapter girlyAdapter;
//    private RealAdapter realAdapter;
    private PartAdapter partAdapter;

    private static final String ARG_TITLE = "title";
    private String mTitle;
    private static int FRESH_GANHUO_TIME = 4;
    private static int fi_num = Constant.FI_NUM;
    private static int gh_num = Constant.GH_NUM;
    private static int mz_num = Constant.MZ_NUM;
    private static int NOW_PAGE_FI = 1;
    private static int NOW_PAGE_GH = 1;
    private static int NOW_PAGE_MZ = 1;
    private HomePresenter homePresenter;
    private List<Results> part_list = new ArrayList<>();
    private List<Results> ganhuo_list;
    private List<List<Results>> ganhuo_real_list = new ArrayList<>();
    private List<Results> girly_list = new ArrayList<>();
    private boolean isTop=true;


    public static DiscoveryFragment getInstance(String title) {
        DiscoveryFragment fra = new DiscoveryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        fra.setArguments(bundle);
        return fra;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        mTitle = bundle.getString(ARG_TITLE);
        homePresenter = new HomePresenter(this);
    }


    @Override
    protected void initListener() {
        initRecyclerView();
        initSwipyRefreshLayout();
    }

    @Override
    protected void loadData() {
         getData();
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

                Observable.timer(2, TimeUnit.SECONDS)

                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {

                                swipyRefreshLayout.setRefreshing(false);
                            }
                        });
                isTop = direction == SwipyRefreshLayoutDirection.TOP ? true : false;
                getData();
            }
        });

    }

    private void initRecyclerView() {
        recyclerview = (RecyclerView) mRootView.findViewById(R.id.recyclerView);
        switch (mTitle) {
            case "首页":
                recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                partAdapter = new PartAdapter(getActivity(), null);
                recyclerview.setAdapter(partAdapter);
                break;
//            case "干货":
//                recyclerview.setLayoutManager(new LinearLayoutManager(recyclerview.getContext()));
//                realAdapter = new RealAdapter(getActivity(), null);
//                recyclerview.setAdapter(realAdapter);
//                break;
//            case "妹纸":
//                recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//                girlyAdapter = new GirlyAdapter(getActivity(), null);
//                recyclerview.setAdapter(girlyAdapter);
//                break;
        }

    }

    private void getData() {
        switch (mTitle) {
            case "首页":
                if (isTop) {
                    NOW_PAGE_FI = 1;
                }
                homePresenter.getDataResults("all", fi_num, NOW_PAGE_FI);
                break;
//            case "干货":
//                FRESH_GANHUO_TIME = 0;
//                ganhuo_real_list.clear();
//                getDataResults("Android", gh_num, NOW_PAGE_GH, isTop);
//                getDataResults("iOS", gh_num, NOW_PAGE_GH, isTop);
//                getDataResults("前端", gh_num, NOW_PAGE_GH, isTop);
//                getDataResults("拓展资源", gh_num, NOW_PAGE_GH, isTop);
//                break;
//            case "妹纸":
//                if (isTop) {
//                    NOW_PAGE_MZ = 1;
//                }
//                getDataResults("福利", mz_num, NOW_PAGE_MZ, isTop);
//                break;
        }
    }


    @Override
    public void newDatas(DataResults dataResults) {
        if (dataResults.isError()) {
            Snackbar.make(recyclerview, "服务器出问题啦", Snackbar.LENGTH_SHORT).show();
        } else {
            if (mTitle.equals("干货")) {
                ganhuo_list = new ArrayList<>();
                ganhuo_list.addAll(dataResults.getResults());
                ganhuo_real_list.add(ganhuo_list);
                FRESH_GANHUO_TIME++;
            }

            if (isTop) {
                clearAdapterResults();
            }
            dealWithDataInRecyclerView(dataResults.getResults(), ganhuo_real_list, dataResults.getResults());
        }
    }

    private void clearAdapterResults() {
        switch (mTitle) {
            case "首页":
                partAdapter.getResults().clear();
                break;
//            case "妹纸":
//                girlyAdapter.getResults().clear();
//                break;
        }
    }

    private void dealWithDataInRecyclerView(List<Results> part_list, List<List<Results>> ganhuo_real_list, List<Results> girly_list) {
        switch (mTitle) {
            case "首页":
                partAdapter.getResults().addAll(part_list);
                partAdapter.notifyDataSetChanged();
                NOW_PAGE_FI++;
                break;
//            case "干货":
//                if (FRESH_GANHUO_TIME == 4) {
//                    realAdapter.getRealResults().clear();
//                    realAdapter.getRealResults().addAll(ganhuo_real_list);
//                    realAdapter.notifyDataSetChanged();
//                    ganhuo_real_list.clear();
//                }
//                break;
//            case "妹纸":
//                girlyAdapter.getResults().addAll(girly_list);
//                girlyAdapter.notifyDataSetChanged();
//                NOW_PAGE_MZ++;
//                break;
        }
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
