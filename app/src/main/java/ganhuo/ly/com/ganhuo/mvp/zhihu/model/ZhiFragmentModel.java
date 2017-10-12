package ganhuo.ly.com.ganhuo.mvp.zhihu.model;

import ganhuo.ly.com.ganhuo.data.HttpData.HttpData;
import ganhuo.ly.com.ganhuo.mvp.entity.ZhiHuDetailResults;
import ganhuo.ly.com.ganhuo.mvp.entity.ZhiHuResults;
import ganhuo.ly.com.ganhuo.mvp.home.model.OnLoadDataListListener;
import rx.Observer;

/**
 * Created by liuyu1 on 2017/8/11.
 */

public class ZhiFragmentModel {

    public void loadData(final OnLoadDataListListener listener, boolean isUseCache, String date) {
        HttpData.getInstance().getZhihuInfo(new Observer<ZhiHuResults>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(ZhiHuResults zhiHuResults) {
                listener.onSuccess(zhiHuResults);

            }
        }, isUseCache, date);
    }

    public void loadDataDetail(final OnLoadDataListListener listener, String id) {
        HttpData.getInstance().getZhihuDetail(new Observer<ZhiHuDetailResults>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(ZhiHuDetailResults zhiHuDetailResults) {
                listener.onSuccess(zhiHuDetailResults);

            }
        }, id);
    }
}
