package ganhuo.ly.com.ganhuo.mvp.huaban.presenter;

import android.util.Log;

import ganhuo.ly.com.ganhuo.mvp.entity.HuaResults;
import ganhuo.ly.com.ganhuo.mvp.home.model.OnLoadDataListListener;
import ganhuo.ly.com.ganhuo.mvp.huaban.model.HuaFragmentModel;
import ganhuo.ly.com.ganhuo.mvp.huaban.view.HuaFragmentView;

/**
 * Created by liuyu1 on 2017/8/10.
 */

public class HuaPresenter implements OnLoadDataListListener<HuaResults> {

    private HuaFragmentView mView;
    private HuaFragmentModel mModel;

    public HuaPresenter(HuaFragmentView mView) {
        this.mView = mView;
        this.mModel=new HuaFragmentModel();
        mView.showProgress();
    }

    public void getDataResults(boolean isUseCache,String type,int max) {
        mModel.loadData(this,isUseCache,type,max);
    }


    @Override
    public void onSuccess(HuaResults data) {
        mView.newDatas(data);
        mView.hideProgress();
    }

    @Override
    public void onFailure(Throwable e) {
        Log.e("onFailure",e.toString());
        mView.showLoadFailMsg();
    }
}

