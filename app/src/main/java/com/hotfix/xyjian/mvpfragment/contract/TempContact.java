package com.hotfix.xyjian.mvpfragment.contract;

import android.content.Context;

import com.hotfix.xyjian.mvpfragment.base.BaseModel;
import com.hotfix.xyjian.mvpfragment.base.BasePresenter;
import com.hotfix.xyjian.mvpfragment.base.BaseView;
import com.hotfix.xyjian.mvpfragment.retrofit.BaseBean;
import com.hotfix.xyjian.mvpfragment.retrofit.ResultSubscriber;
import com.hotfix.xyjian.mvpfragment.retrofit.request.BrokerRequest;

import java.util.HashMap;

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
        public void getInfo(Context context,String tag){
            BrokerRequest.getLocationCityInfo(new HashMap<String, String>(), new ResultSubscriber<BaseBean>(context,tag,true) {
                @Override
                public void dataSuccess(BaseBean baseBean) {
                    mView.showErrorTip(baseBean.status);
                }

                @Override
                public void dataError(@DataErrorType int errorType, String msg) {
                    mView.showErrorTip(msg);
                }
            });
        }
    }
}
