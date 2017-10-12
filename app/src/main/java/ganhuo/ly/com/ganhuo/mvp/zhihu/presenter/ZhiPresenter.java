package ganhuo.ly.com.ganhuo.mvp.zhihu.presenter;

import android.util.Log;

import ganhuo.ly.com.ganhuo.mvp.entity.ZhiHuResults;
import ganhuo.ly.com.ganhuo.mvp.home.model.OnLoadDataListListener;
import ganhuo.ly.com.ganhuo.mvp.zhihu.model.ZhiFragmentModel;
import ganhuo.ly.com.ganhuo.mvp.zhihu.view.ZhiFragmentView;

/**
 * Created by liuyu1 on 2017/8/10.
 */

public class ZhiPresenter implements OnLoadDataListListener<ZhiHuResults> {

    private ZhiFragmentView mView;
    private ZhiFragmentModel mModel;

    public ZhiPresenter(ZhiFragmentView mView) {
        this.mView = mView;
        this.mModel=new ZhiFragmentModel();
        mView.showProgress();
    }

    public void getDataResults(boolean isUseCache,String date) {
        mModel.loadData(this,isUseCache,date);
    }

    @Override
    public void onSuccess(ZhiHuResults data) {
        mView.newDatas(data);
        mView.hideProgress();
    }

    @Override
    public void onFailure(Throwable e) {
        Log.e("onFailure",e.toString());
        mView.showLoadFailMsg();
    }
}

