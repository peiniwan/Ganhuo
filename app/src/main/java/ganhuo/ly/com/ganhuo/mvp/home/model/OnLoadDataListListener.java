package ganhuo.ly.com.ganhuo.mvp.home.model;

/**
 * Created by XY on 2016/9/17.
 */
public interface OnLoadDataListListener<T> {
    void onSuccess(T data);

    void onFailure(Throwable e);
}
