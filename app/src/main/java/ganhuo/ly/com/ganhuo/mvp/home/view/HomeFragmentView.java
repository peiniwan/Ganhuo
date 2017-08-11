package ganhuo.ly.com.ganhuo.mvp.home.view;

import ganhuo.ly.com.ganhuo.mvp.entity.DataResults;

/**
 * Created by XY on 2016/9/17.
 */
public interface HomeFragmentView {
    //显示加载页
    void showProgress();
    //关闭加载页
    void hideProgress();
    //加载新数据
    void newDatas(DataResults data);
    //显示加载失败
    void showLoadFailMsg();

}
