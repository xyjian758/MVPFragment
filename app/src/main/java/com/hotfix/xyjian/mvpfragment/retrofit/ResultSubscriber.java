package com.hotfix.xyjian.mvpfragment.retrofit;

import android.content.Context;
import android.support.annotation.IntDef;
import android.text.TextUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * 主要是针对 BaseModel 型的数据的返回统一处理
 */
public abstract class ResultSubscriber<T extends BaseBean> implements Subscriber<T> {
    public static final int ERROR_NET = 201712;//网络连接异常
    public static final int ERROR_DATA = 201713; //网络连接正常 但请求的服务器错误

    private Context mContext;
    private String mRequestTag;
    private boolean mShowDialog;
    private String mDialogMsg;

    //私有化 ，需要传参数
    private ResultSubscriber() {
    }

    public ResultSubscriber(String requestTag) {
        this.mRequestTag = requestTag;
        this.mShowDialog = false;
    }

    public ResultSubscriber(Context context, String requestTag, boolean showDialog) {
        this.mContext = context;
        this.mRequestTag = requestTag;
        this.mShowDialog = showDialog;
    }

    public ResultSubscriber(Context context, String requestTag, boolean showDialog, String dialogMsg) {
        this.mContext = context;
        this.mRequestTag = requestTag;
        this.mShowDialog = showDialog;
        this.mDialogMsg = dialogMsg;
    }

    @Override
    public void onSubscribe(Subscription s) {
        if (mShowDialog) {
            //需要加载进度框
            if (!TextUtils.isEmpty(mDialogMsg)) {
//                CustomProgressDialog.showLoading(mContext, mDialogMsg);
            } else {
//                CustomProgressDialog.showLoading(mContext);
            }
        }
        s.request(1);//网络请求一般也只有发送一次
        RetrofitApiManager.getInstance().add(mRequestTag, s);//添加的网络请求管理中，方便取消或者 页面销毁时取消
    }

    @Override
    public void onNext(T t) {
        if (t == null) {
            dataError(ERROR_DATA, "");
            return;
        }
        if (!TextUtils.equals("success", t.status)) {
            dataError(ERROR_DATA, t.msg);
            return;
        }
        dataSuccess(t);
    }

    @Override
    public void onError(Throwable t) {
        if (mShowDialog) {
            //取消对话框
//            CustomProgressDialog.stopLoading();
        }
        RetrofitApiManager.getInstance().remove(mRequestTag);
        dataError(ERROR_NET, t.getMessage());
    }

    @Override
    public void onComplete() {
        if (mShowDialog) {
            //取消对话框
//            CustomProgressDialog.stopLoading();
        }
        RetrofitApiManager.getInstance().remove(mRequestTag);
    }

    public abstract void dataSuccess(T t);

    public abstract void dataError(@DataErrorType int errorType, String msg);

    @IntDef({ERROR_NET, ERROR_DATA})
    public @interface DataErrorType {
    }
}
