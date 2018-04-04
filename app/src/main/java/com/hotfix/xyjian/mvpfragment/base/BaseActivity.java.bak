package com.hotfix.xyjian.mvpfragment.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.hotfix.xyjian.mvpfragment.R;
import com.hotfix.xyjian.mvpfragment.utils.TUtil;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;


/**
 * 基类
 */

/***************使用例子*********************/
//1.mvp模式
//public class SampleActivity extends BaseActivity<NewsChanelPresenter, NewsChannelModel>implements NewsChannelContract.View {
//
//    @Override
//    public void initPresenter() {
//        mPresenter.setViewAndModel(this, mModel);
//    }
//}
//2.普通模式
//public class SampleActivity extends BaseActivity {
//    @Override
//    public void initPresenter() {
//    }
//}
public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends SupportActivity {
    private P mPresent;
    private M mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//通过程序改变屏幕显示的方向
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initMVP();
        initPresenter();

        init();
    }

    private void initMVP() {
        mPresent = TUtil.getT(this, 0);
        mMode = TUtil.getT(this, 1);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresent != null) {
            mPresent.onDestroy();
        }
        ButterKnife.unbind(this);
    }


    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
    }

    /*********************子类实现*****************************/
    //初始化需要的东东
    public abstract void init();

    //获取布局文件
    public abstract int getLayoutId();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();
}
