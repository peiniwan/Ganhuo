package ganhuo.ly.com.ganhuo.mvp.meizi;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;
import java.util.concurrent.TimeUnit;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.mvp.base.BaseFragment;
import ganhuo.ly.com.ganhuo.util.ListUtils;
import okhttp3.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MeiMainFragment extends BaseFragment implements MeiziView {

    private RecyclerView recyclerview;
    private MeiziAdapter adapter;
    private MeiziPresenter meiziPresenter;
    private MeiziResult.ResultsBean resultsBean;
    private RealMeiziPresenter realMeiziPresenter;
    private SwipyRefreshLayout swipyRefreshLayout;
    private boolean isTop;
    private List<MeiziResult.ResultsBean> beans;
    private int offset = 20;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_meizi, container, false);
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

                if (ListUtils.isEmpty(beans)) {
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

                    getData(offset += 20);
                }

            }
        });
    }

    private void initRecyclerView() {
        recyclerview = (RecyclerView) mRootView.findViewById(R.id.recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);
        adapter = new MeiziAdapter(getActivity());
        recyclerview.setAdapter(adapter);
    }

    @Override
    protected void loadData() {
        meiziPresenter = new MeiziPresenter(this);
//        ImageActivity.newIntent(getActivity(), url, des);
        getData(offset);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void getData(int offset) {
        if (isTop) {
            offset = 20;
        }
        String s = String.valueOf(offset);
        meiziPresenter.getDataResults("getlist", s, "");
    }


    @Override
    public void newDatas(MeiziResult data) {
        if (isTop) {
            adapter.getResults().clear();
        }
        beans = data.getResults();
        adapter.getResults().addAll(beans);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showLoadFailMsg() {

    }

    @Override
    public void realPic(final Response response) {
    }
}
