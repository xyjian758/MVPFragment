package com.hotfix.xyjian.mvpfragment.contract;

import com.hotfix.xyjian.mvpfragment.base.BaseModel;
import com.hotfix.xyjian.mvpfragment.base.BasePresenter;
import com.hotfix.xyjian.mvpfragment.base.BaseView;

/**
 * Created by Xyjian on 2018/4/3.
 */

public class TempContact {
    public interface TempView extends BaseView {
        abstract void showInfo();

    }

    public static class TempMode implements BaseModel {
    }

    public static class TempPresenter extends BasePresenter<TempView, TempMode> {
    }
}
