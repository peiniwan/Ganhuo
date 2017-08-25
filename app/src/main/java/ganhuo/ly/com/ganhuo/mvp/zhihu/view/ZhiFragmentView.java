package ganhuo.ly.com.ganhuo.mvp.zhihu.view;

import ganhuo.ly.com.ganhuo.mvp.entity.ZhiHuDetailResults;
import ganhuo.ly.com.ganhuo.mvp.entity.ZhiHuResults;

/**
 * Created by XY on 2016/9/17.
 */
public interface ZhiFragmentView {
    //显示加载页
    void showProgress();
    //关闭加载页
    void hideProgress();
    //加载新数据
    void newDatas(ZhiHuResults data);
    //显示加载失败
    void showLoadFailMsg();

    void newDataDetail(ZhiHuDetailResults data);


}
