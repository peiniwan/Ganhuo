package ganhuo.ly.com.ganhuo.mvp.meizi;

import android.util.Log;

import ganhuo.ly.com.ganhuo.mvp.home.model.OnLoadDataListListener;

public class MeiziPresenter implements OnLoadDataListListener<MeiziResult> {

    private MeiziView mView;
    private MeiziMoel mModel;

    public MeiziPresenter(MeiziView mView) {
        this.mView = mView;
        this.mModel = new MeiziMoel();
        mView.showProgress();
    }

    public void getDataResults(String type,String offset,String mid) {
        mModel.loadData(this,type, offset,mid);
    }

    @Override
    public void onSuccess(MeiziResult data) {
        mView.newDatas(data);
        mView.hideProgress();
    }


    @Override
    public void onFailure(Throwable e) {
        Log.e("onFailure", e.toString());
        mView.showLoadFailMsg();
    }
}