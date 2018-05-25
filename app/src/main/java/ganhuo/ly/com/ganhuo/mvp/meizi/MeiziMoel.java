package ganhuo.ly.com.ganhuo.mvp.meizi;

import ganhuo.ly.com.ganhuo.data.HttpData.HttpData;
import ganhuo.ly.com.ganhuo.mvp.home.model.OnLoadDataListListener;
import okhttp3.Response;
import rx.Observer;

class MeiziMoel {
    public void loadData(final OnLoadDataListListener listener, String type, String offset, String mid) {
        HttpData.getInstance().getMeizi(new Observer<MeiziResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(MeiziResult result) {
                listener.onSuccess(result);

            }

        }, type, offset, mid);
    }

    public void loadrealData(final OnLoadDataListListener listener, String url, String referer) {
        HttpData.getInstance().getRealpic(new Observer<Response>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(Response response) {
                listener.onSuccess(response);
            }

        }, url, referer);
    }
}
