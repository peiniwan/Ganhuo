package ganhuo.ly.com.ganhuo.mvp.zhihu.presenter;

import android.util.Log;

import ganhuo.ly.com.ganhuo.mvp.entity.ZhiHuDetailResults;
import ganhuo.ly.com.ganhuo.mvp.home.model.OnLoadDataListListener;
import ganhuo.ly.com.ganhuo.mvp.zhihu.model.ZhiFragmentModel;
import ganhuo.ly.com.ganhuo.mvp.zhihu.view.ZhiFragmentView;

/**
 * Created by liuyu1 on 2017/8/10.
 */

public class ZhiDetailPresenter implements OnLoadDataListListener<ZhiHuDetailResults> {

    private ZhiFragmentView mView;
    private ZhiFragmentModel mModel;

    public ZhiDetailPresenter(ZhiFragmentView mView) {
        this.mView = mView;
        this.mModel=new ZhiFragmentModel();
        mView.showProgress();
    }

    public void getDataResults(String id) {
        mModel.loadDataDetail(this,id);
    }

    @Override
    public void onSuccess(ZhiHuDetailResults data) {
        mView.newDataDetail(data);
        mView.hideProgress();
    }

    @Override
    public void onFailure(Throwable e) {
        Log.e("onFailure",e.toString());
        mView.showLoadFailMsg();
    }
}

