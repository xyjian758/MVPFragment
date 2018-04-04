package com.hotfix.xyjian.mvpfragment.base;

/**
 * @author Xyjian
 * @Description: TODO(){BaseView}
 * @date: 2018/4/3 10:38
 */
public interface BaseView {
    /*******内嵌加载*******/
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
}
