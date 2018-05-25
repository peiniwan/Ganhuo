package ganhuo.ly.com.ganhuo.mvp.meizi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.mvp.base.BaseActivity;
import ganhuo.ly.com.ganhuo.util.FileUtil;
import ganhuo.ly.com.ganhuo.util.ListUtils;
import okhttp3.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MeiziListActivity extends BaseActivity implements MeiziView {

    private SwipyRefreshLayout swipyRefreshLayout;
    private boolean isTop;
    private RecyclerView recyclerview;
    private List<MeiziResult.ResultsBean> beans;
    private int offset = 20;
    private MeiziPresenter meiziPresenter;
    private RealMeiziPresenter realMeiziPresenter;
    private String mid;
    private List<byte[]> realPicList;
    private MeiziListAdapter adapter;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.fragment_zhihu);
    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void setListener() {
        initRecyclerView();
        initSwipyRefreshLayout();
    }

    private void initSwipyRefreshLayout() {
        swipyRefreshLayout = (SwipyRefreshLayout) findViewById(R.id.swipyrefreshlayout);
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

                if (ListUtils.isEmpty(realPicList)) {
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
        recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new MeiziListAdapter(this);
        recyclerview.setAdapter(adapter);
    }

    @Override
    protected void processLogic() {
        mid = getIntent().getStringExtra("mid");
        realPicList = new ArrayList<>();
        meiziPresenter = new MeiziPresenter(this);
        realMeiziPresenter = new RealMeiziPresenter(this);
        getData(offset);
    }


    private void getData(int offset) {
        if (isTop) {
            offset = 20;
        }
        String s = String.valueOf(offset);
        meiziPresenter.getDataResults("getlistpic", s, mid);
    }


    @Override
    public void newDatas(MeiziResult data) {
        if (isTop) {
            adapter.getRealResults().clear();
        }
        if (data != null && !ListUtils.isEmpty(data.getResults())) {
            beans = data.getResults();
            realPicList.clear();
            for (MeiziResult.ResultsBean result : beans) {
                String img_url = result.getImg_url();
                String page_url = result.getPage_url();
                //由于网站做了反爬操作，所以需要再请求一次
                realMeiziPresenter.getRealData(img_url, page_url);
            }
        }


    }

    @Override
    public void realPic(final Response response) {

        if (response != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    InputStream is = response.body().byteStream();
                    try {
                        byte[] b = FileUtil.readInputStream(is);
                        realPicList.add(b);
                        if (realPicList.size() == beans.size()) {
                            adapter.getRealResults().addAll(realPicList);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    protected Context getActivityContext() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


    @Override
    public void showLoadFailMsg() {

    }


}
