package ganhuo.ly.com.ganhuo.data.HttpData;


import java.io.File;
import java.util.List;

import ganhuo.ly.com.ganhuo.common.Constant;
import ganhuo.ly.com.ganhuo.data.api.CacheProviders;
import ganhuo.ly.com.ganhuo.data.api.GanHuoService;
import ganhuo.ly.com.ganhuo.data.api.HuaBangService;
import ganhuo.ly.com.ganhuo.data.api.ZhihuService;
import ganhuo.ly.com.ganhuo.data.retrofit.RetrofitUtils;
import ganhuo.ly.com.ganhuo.mvp.entity.DataResults;
import ganhuo.ly.com.ganhuo.mvp.entity.HuaResults;
import ganhuo.ly.com.ganhuo.mvp.entity.ZhiHuDetailResults;
import ganhuo.ly.com.ganhuo.mvp.entity.ZhiHuResults;
import ganhuo.ly.com.ganhuo.util.FileUtil;
import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import io.rx_cache.internal.RxCache;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/*
 * 所有的请求数据的方法集中地
 * 根据MovieService的定义编写合适的方法
 * 其中observable是获取API数据
 * observableCahce获取缓存数据
 * new EvictDynamicKey(false) false使用缓存  true 加载数据不使用缓存
 */
public class HttpData extends RetrofitUtils {

    private static File cacheDirectory = FileUtil.getcacheDirectory();
    private static final CacheProviders providers = new RxCache.Builder()
            .persistence(cacheDirectory)
            .using(CacheProviders.class);
    protected GanHuoService ganService = getRetrofit(Constant.Urls.API_SERVER).create(GanHuoService.class);
    protected ZhihuService zhiService = getRetrofit(Constant.Urls.ZHIHU_DAILY_BEFORE).create(ZhihuService.class);
    protected ZhihuService zhiDetailService = getRetrofit(Constant.Urls.ZHIHU_DAILY_OFFLINE_NEWS).create(ZhihuService.class);
    protected HuaBangService huaBangService = getRetrofit(Constant.Urls.HUA_BANG_URL).create(HuaBangService.class);

    private static class SingletonHolder {
        private static final HttpData INSTANCE = new HttpData();
    }

    public static HttpData getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public void getHomeInfo(Observer<DataResults> observer, boolean isUseCache, String type, int number, int page) {
        Observable observable = ganService.getDataResults(type, number, page);
        Observable observableCahce = providers.getHomeTypes(observable, new DynamicKey("首页"), new EvictDynamicKey(!isUseCache)).map(new HttpResultFuncCcche<List<DataResults>>());
        setSubscribe(observableCahce, observer);
    }

    public void getZhihuInfo(Observer<ZhiHuResults> observer, String date) {
        Observable observable = zhiService.getDataResults(date);
//        Observable observableCahce = providers.getHomeTypes(observable, new DynamicKey("首页"), new EvictDynamicKey(!isUseCache)).map(new HttpResultFuncCcche<List<DataResults>>());
        setSubscribe(observable, observer);
    }

    public void getZhihuDetail(Observer<ZhiHuDetailResults> observer, String id) {
        Observable observable = zhiDetailService.getDataResultsDetail(id);
//        Observable observableCahce = providers.getHomeTypes(observable, new DynamicKey("首页"), new EvictDynamicKey(!isUseCache)).map(new HttpResultFuncCcche<List<DataResults>>());
        setSubscribe(observable, observer);
    }


    public void getHuaInfo(Observer<HuaResults> observer) {
        Observable observable = huaBangService.getDataResults("45128","0ebee127eb894f5c99ed8a7d8452e4c7","35","20","1");
//        Observable observableCahce = providers.getHomeTypes(observable, new DynamicKey("首页"), new EvictDynamicKey(!isUseCache)).map(new HttpResultFuncCcche<List<DataResults>>());
        setSubscribe(observable, observer);
    }


    /**
     * 插入观察者
     *
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

    /**
     * 用来统一处理RxCacha的结果
     */
    private class HttpResultFuncCcche<T> implements Func1<Reply<T>, T> {

        @Override
        public T call(Reply<T> httpResult) {
            return httpResult.getData();
        }
    }

}
