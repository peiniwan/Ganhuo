package ganhuo.ly.com.ganhuo.mvp.home.model;

import android.util.Log;

import ganhuo.ly.com.ganhuo.data.HttpData.HttpData;
import ganhuo.ly.com.ganhuo.mvp.entity.DataResults;
import rx.Observer;

/**
 * Created by liuyu1 on 2017/8/11.
 */

public class HomeFragmentModel {

    public void loadData(final OnLoadDataListListener listener,boolean isUseCache ,String type, int number, int page) {
        HttpData.getInstance().getHomeInfo(new Observer<DataResults>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
                Log.d("getData","2");

            }

            @Override
            public void onNext(DataResults homeDto) {
                listener.onSuccess(homeDto);
                Log.d("getData","3");

            }
        }, isUseCache,type,number,page);
    }
}
