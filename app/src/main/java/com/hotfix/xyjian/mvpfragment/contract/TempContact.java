package com.hotfix.xyjian.mvpfragment.contract;

import android.content.Context;

import com.hotfix.xyjian.mvpfragment.base.BasePresenter;
import com.hotfix.xyjian.mvpfragment.base.BaseView;
import com.hotfix.xyjian.mvpfragment.retrofit.BaseBean;
import com.hotfix.xyjian.mvpfragment.retrofit.ResultSubscriber;
import com.hotfix.xyjian.mvpfragment.model.BrokerRequest;

import java.util.HashMap;


/**
 * @author Xyjian
 * @Description: TODO(){临时Fragment层的契约类}
 * @date: 2018/4/19 11:57
 *
 * {@link com.hotfix.xyjian.mvpfragment.fragment.TempFragment}
 */
public class TempContact {
    public interface TempView extends BaseView {
        abstract void showInfo();

    }

    public static class TempPresenter extends BasePresenter<TempView> {
        public void getInfo(Context context, String tag) {
            BrokerRequest.getLocationCityInfo(new HashMap<String, String>(), new ResultSubscriber<BaseBean>(context, tag, true) {
                @Override
                public void dataSuccess(BaseBean baseBean) {
                    mView.showErrorTip(baseBean.status);
                }

                @Override
                public void dataError(@DataErrorType int errorType, String msg) {
                    if (isViewAttrach()) {
                        mView.showErrorTip(msg);
                    }

                }
            });
        }

        @Override
        public void onDestroy() {
            super.onDestroy();

        }
    }
}
