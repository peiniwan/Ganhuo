package ganhuo.ly.com.ganhuo.mvp.huaban.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;
import java.util.concurrent.TimeUnit;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.mvp.base.BaseFragment;
import ganhuo.ly.com.ganhuo.mvp.entity.HuaResults;
import ganhuo.ly.com.ganhuo.mvp.home.adapter.GirlyAdapter;
import ganhuo.ly.com.ganhuo.mvp.huaban.presenter.HuaPresenter;
import ganhuo.ly.com.ganhuo.mvp.huaban.view.HuaFragmentView;
import ganhuo.ly.com.ganhuo.util.ListUtils;
import ganhuo.ly.com.ganhuo.util.SPUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static ganhuo.ly.com.ganhuo.util.SPUtils.get;

/**
 * Created by liuyu1 on 2017/8/22.
 */

public class HuaFragment extends BaseFragment implements HuaFragmentView {
    private SwipyRefreshLayout swipyRefreshLayout;
    private RecyclerView recyclerview;
    private HuaPresenter huaPresenter;
    private String type;
    private GirlyAdapter girlyAdapter;
    private List<HuaResults.PinsBean> pins;
    private boolean isTop;
    private int maxId;


    public static HuaFragment getInstance(String type) {
        HuaFragment fra = new HuaFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fra.setArguments(bundle);
        return fra;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle bundle = getArguments();
            type = bundle.getString("type");
            Log.d("huatype", type);
        }
        huaPresenter = new HuaPresenter(this);
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
        swipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        swipyRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);

        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                isTop = direction == SwipyRefreshLayoutDirection.TOP ? true : false;

                if (ListUtils.isEmpty(pins)) {
                    swipyRefreshLayout.setRefreshing(false);
                } else {
                    Observable.timer(2, TimeUnit.SECONDS)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<Long>() {
                                @Override
                                public void call(Long aLong) {
                                    swipyRefreshLayout.setRefreshing(false);
                                }
                            });
                    int maxId = (int) SPUtils.get(getActivity(), "maxId", 0);
                    getData(false, type, maxId);
                    SPUtils.put(getActivity(), "maxId", getMaxId(pins));
                }

            }
        });

    }

    /**
     * 从返回联网结果中保存max值 用于下次联网的关键
     *
     * @param
     * @return
     */
    private int getMaxId(List<HuaResults.PinsBean> pins) {

        return pins.get(pins.size() - 1).getPin_id();
    }


    private void initRecyclerView() {
        recyclerview = (RecyclerView) mRootView.findViewById(R.id.recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        girlyAdapter = new GirlyAdapter(getActivity(), 2);
        recyclerview.setAdapter(girlyAdapter);
    }


    private void getData(boolean isUseCache, String type, int max) {
        if (isTop) {
            max = 0;

        }
        huaPresenter.getDataResults(isUseCache, type, max);
    }


    @Override
    public void showLoadFailMsg() {

    }

    @Override
    protected void loadData() {
        boolean isFirst = (boolean) get(getActivity(), "isFirst", false);
        if (!isFirst) {
            get(getActivity(), "isFirst", true);
        }
        getData(false, type, 0);

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void newDatas(HuaResults data) {
        if (isTop) {
            girlyAdapter.getHuaResults().clear();
        }
        pins = data.getPins();
        girlyAdapter.getHuaResults().addAll(pins);
        girlyAdapter.notifyDataSetChanged();
    }


}
