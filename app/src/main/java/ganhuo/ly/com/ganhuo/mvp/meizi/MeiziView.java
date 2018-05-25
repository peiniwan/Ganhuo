package ganhuo.ly.com.ganhuo.mvp.meizi;

import okhttp3.Response;

public interface MeiziView {
    //显示加载页
    void showProgress();

    //关闭加载页
    void hideProgress();

    //加载新数据
    void newDatas(MeiziResult data);

    //显示加载失败
    void showLoadFailMsg();

    void realPic(Response response);

}
