package ganhuo.ly.com.ganhuo.mvp.huaban.model;

import ganhuo.ly.com.ganhuo.data.HttpData.HttpData;
import ganhuo.ly.com.ganhuo.mvp.entity.HuaResults;
import ganhuo.ly.com.ganhuo.mvp.home.model.OnLoadDataListListener;
import rx.Observer;

/**
 * Created by liuyu1 on 2017/8/11.
 */

public class HuaFragmentModel {

    public void loadData(final OnLoadDataListListener listener, boolean isUseCache ,String type,int max) {
        HttpData.getInstance().getHuaInfo(new Observer<HuaResults>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(HuaResults huaResults) {
                listener.onSuccess(huaResults);

            }

        }, isUseCache,type,max);
    }
}
