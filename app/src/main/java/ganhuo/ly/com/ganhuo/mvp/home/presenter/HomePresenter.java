package ganhuo.ly.com.ganhuo.mvp.home.presenter;

import android.util.Log;

import ganhuo.ly.com.ganhuo.mvp.entity.DataResults;
import ganhuo.ly.com.ganhuo.mvp.home.model.HomeFragmentModel;
import ganhuo.ly.com.ganhuo.mvp.home.model.OnLoadDataListListener;
import ganhuo.ly.com.ganhuo.mvp.home.view.HomeFragmentView;

/**
 * Created by liuyu1 on 2017/8/10.
 */

public class HomePresenter  implements OnLoadDataListListener<DataResults> {

    private HomeFragmentView mView;
    private HomeFragmentModel mModel;

    public HomePresenter(HomeFragmentView mView) {
        this.mView = mView;
        this.mModel=new HomeFragmentModel();
        mView.showProgress();
    }

    public void getDataResults(boolean isUseCache,String type, int number, int page) {
        mModel.loadData(this,isUseCache,type,number,page);
    }

    @Override
    public void onSuccess(DataResults data) {
        mView.newDatas(data);
        mView.hideProgress();
    }

    @Override
    public void onFailure(Throwable e) {
        Log.e("onFailure",e.toString());
        mView.showLoadFailMsg();
    }
}

