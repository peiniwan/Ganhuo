package ganhuo.ly.com.ganhuo.mvp.meizi;

import android.util.Log;

import ganhuo.ly.com.ganhuo.mvp.home.model.OnLoadDataListListener;
import okhttp3.Response;

public class RealMeiziPresenter implements OnLoadDataListListener<Response> {

    private MeiziView mView;
    private MeiziMoel mModel;

    public RealMeiziPresenter(MeiziView mView) {
        this.mView = mView;
        this.mModel = new MeiziMoel();
        mView.showProgress();
    }

    public void getRealData(String url,String referer) {
        mModel.loadrealData(this, url,referer);
    }


    @Override
    public void onSuccess(Response data) {
        mView.realPic(data);
        mView.hideProgress();
    }


    @Override
    public void onFailure(Throwable e) {
        Log.e("onFailure", e.toString());
        mView.showLoadFailMsg();
    }
}