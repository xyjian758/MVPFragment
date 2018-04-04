package com.hotfix.xyjian.mvpfragment.base;

/**
 * author  xyj
 * createtime 2017/4/6 10:27
 * desc 基类presenter
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {
    public M mModel;
    public V mView;

    /**
     * @param v View对象
     * @param m Mode对象
     */
    public void setViewAndModel(V v, M m) {
        this.mModel = m;
        this.mView = v;
        this.onStart();
    }

    public void onStart() {
    }

    public void onDestroy() {
    }

}
