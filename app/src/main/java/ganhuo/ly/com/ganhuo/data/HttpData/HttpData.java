package ganhuo.ly.com.ganhuo.data.HttpData;


import java.io.File;

import ganhuo.ly.com.ganhuo.data.api.CacheProviders;
import ganhuo.ly.com.ganhuo.data.api.MovieService;
import ganhuo.ly.com.ganhuo.data.retrofit.RetrofitUtils;
import ganhuo.ly.com.ganhuo.util.FileUtil;
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
    protected static final MovieService service = getRetrofit().create(MovieService.class);

    private static class SingletonHolder {
        private static final HttpData INSTANCE = new HttpData();
    }

    public static HttpData getInstance() {
        return SingletonHolder.INSTANCE;
    }

//    //获取app书本类别
//    public void getBookTypes(Observer<List<BookTypeDto>> observer){
//        Observable observable=service.getBookTypes();
//        Observable observableCahce=providers.getBookTypes(observable,new DynamicKey("书本类别"),new EvictDynamicKey(false)).map(new HttpResultFuncCcche<List<BookTypeDto>>());
//        setSubscribe(observableCahce,observer);
//    }

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
