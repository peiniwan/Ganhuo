package ganhuo.ly.com.ganhuo.mvp.meizi.fragment;

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
import ganhuo.ly.com.ganhuo.mvp.entity.HuaResults;
import ganhuo.ly.com.ganhuo.mvp.meizi.adapter.HuaAdapter;
import ganhuo.ly.com.ganhuo.mvp.meizi.presenter.HuaPresenter;
import ganhuo.ly.com.ganhuo.mvp.meizi.view.HuaFragmentView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by liuyu1 on 2017/8/22.
 */

public class HuaFragment extends BaseFragment implements HuaFragmentView {
    private SwipyRefreshLayout swipyRefreshLayout;
    private RecyclerView recyclerview;
    private HuaPresenter huaPresenter;


    public static HuaFragment getInstance() {
        HuaFragment fra = new HuaFragment();
        Bundle bundle = new Bundle();
        fra.setArguments(bundle);
        return fra;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle bundle = getArguments();
        }
        huaPresenter = new HuaPresenter(this);
        getData(false);
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
                getData(false);

            }
        });

    }

    private void initRecyclerView() {
        recyclerview = (RecyclerView) mRootView.findViewById(R.id.recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);
        HuaAdapter   mAdapter = new HuaAdapter(getActivity(), null);
        recyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new HuaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("position", "position:" + position);
            }
        });
    }

    private void shareQuestion(String questionUrl, String questionTitle) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        share.putExtra(Intent.EXTRA_TEXT,
                questionTitle + " " + questionUrl + Constant.Strings.SHARE_FROM_ZHIHU);
        startActivity(Intent.createChooser(share, getString(R.string.share_to)));
    }

    private void getData(boolean isUseCache) {
        huaPresenter.getDataResults();
    }


    @Override
    public void showLoadFailMsg() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void newDatas(HuaResults data) {

    }


}
