package ganhuo.ly.com.ganhuo.mvp.home.model;

import ganhuo.ly.com.ganhuo.data.HttpData.HttpData;
import ganhuo.ly.com.ganhuo.mvp.entity.DataResults;
import rx.Observer;

/**
 * Created by liuyu1 on 2017/8/11.
 */

public class HomeFragmentModel {

    public void loadData(final OnLoadDataListListener listener, String type, int number, int page) {
        HttpData.getInstance().getHomeInfo(new Observer<DataResults>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(DataResults homeDto) {
                listener.onSuccess(homeDto);
            }
        },type,number,page);
    }
}
