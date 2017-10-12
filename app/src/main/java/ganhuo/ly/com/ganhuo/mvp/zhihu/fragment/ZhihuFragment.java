package ganhuo.ly.com.ganhuo.mvp.zhihu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.concurrent.TimeUnit;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.common.Constant;
import ganhuo.ly.com.ganhuo.mvp.base.BaseFragment;
import ganhuo.ly.com.ganhuo.mvp.entity.ZhiHuDetailResults;
import ganhuo.ly.com.ganhuo.mvp.entity.ZhiHuResults;
import ganhuo.ly.com.ganhuo.mvp.home.activity.WebAcitivity;
import ganhuo.ly.com.ganhuo.mvp.zhihu.adapter.ZhiFragmentAdapter;
import ganhuo.ly.com.ganhuo.mvp.zhihu.presenter.ZhiDetailPresenter;
import ganhuo.ly.com.ganhuo.mvp.zhihu.presenter.ZhiPresenter;
import ganhuo.ly.com.ganhuo.mvp.zhihu.view.ZhiFragmentView;
import ganhuo.ly.com.ganhuo.util.SPUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by liuyu1 on 2017/8/22.
 */

public class ZhihuFragment extends BaseFragment implements ZhiFragmentView {
    private String date;
    private ZhiFragmentAdapter mAdapter;
    private SwipyRefreshLayout swipyRefreshLayout;
    private RecyclerView recyclerview;
    private boolean isToday;
    private ZhiPresenter zhiPresenter;
    private ZhiHuResults zhiHuResults;
    private ZhiDetailPresenter zhiDetailPresenter;
    private boolean isShareClick;


    public static ZhihuFragment getInstance(String date, int i) {
        ZhihuFragment fra = new ZhihuFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.BundleKeys.DATE, date);
        bundle.putBoolean(Constant.BundleKeys.IS_FIRST_PAGE, i == 0);
        bundle.putBoolean(Constant.BundleKeys.IS_SINGLE, false);
        fra.setArguments(bundle);
        return fra;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle bundle = getArguments();
            date = bundle.getString(Constant.BundleKeys.DATE);
            isToday = bundle.getBoolean(Constant.BundleKeys.IS_FIRST_PAGE);
            setRetainInstance(true);
        }
        zhiPresenter = new ZhiPresenter(this);
        zhiDetailPresenter = new ZhiDetailPresenter(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_zhihu, container, false);
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
        swipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.TOP);

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
                getData(false, date);

            }
        });

    }

    private void initRecyclerView() {
        recyclerview = (RecyclerView) mRootView.findViewById(R.id.recyclerView);
        recyclerview.setHasFixedSize(!isToday);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);
        mAdapter = new ZhiFragmentAdapter(getActivity(), null);
        recyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ZhiFragmentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("position", "position:" + position);
                isShareClick = false;
                int id = zhiHuResults.getStories().get(position).getId();
                zhiDetailPresenter.getDataResults(String.valueOf(id));

            }
        });
        mAdapter.setOnShareClickListener(new ZhiFragmentAdapter.OnShareClickListener() {
            @Override
            public void onShareClick(View view, int position) {
                isShareClick = true;
                int id = zhiHuResults.getStories().get(position).getId();
                zhiDetailPresenter.getDataResults(String.valueOf(id));
            }
        });
    }

    private void shareQuestion(String questionUrl, String questionTitle) {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        share.putExtra(Intent.EXTRA_TEXT,
                questionTitle + " " + questionUrl + Constant.Strings.SHARE_FROM_ZHIHU);
        startActivity(Intent.createChooser(share, getString(R.string.share_to)));
    }

    private void getData(boolean isUseCache, String date) {
        zhiPresenter.getDataResults(isUseCache,date);
    }


    @Override
    public void newDatas(ZhiHuResults data) {
        if (data != null) {
            this.zhiHuResults = data;
            mAdapter.setZhiHuResults(data);
        }

    }

    @Override
    public void newDataDetail(ZhiHuDetailResults data) {
        if (data != null) {
            Log.d("position", "data:" + data.toString());
            if (isShareClick) {
                shareQuestion(data.getShare_url(), data.getTitle());
            } else {
                WebAcitivity.newIntent(getActivity(), data.getShare_url(), data.getTitle());
            }
        }
    }


    @Override
    public void showLoadFailMsg() {

    }

    @Override
    protected void loadData() {
        boolean isFirst = (boolean) SPUtils.get(getActivity(), "isFirst", false);
        if(!isFirst){
            SPUtils.put(getActivity(), "isFirst", true);
        }
        getData(isFirst, date);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


}
