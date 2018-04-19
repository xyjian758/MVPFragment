package com.hotfix.xyjian.mvpfragment.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hotfix.xyjian.mvpfragment.utils.TUtil;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;


/***************使用例子*********************/
//1.mvp模式
//public class SampleActivity extends BaseActivity<NewsChanelPresenter, NewsChannelModel>implements NewsChannelContract.View {
//
//    @Override
//    public void initPresenter() {
//        mPresenter.setView(this);
//    }
//}
//2.普通模式
//public class SampleActivity extends BaseActivity {
//    @Override
//    public void initPresenter() {
//    }
//}
public abstract class BaseFragment<P extends BasePresenter> extends SupportFragment {
    protected P mPresent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMVP();
        initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    private void initMVP() {
        mPresent = TUtil.getT(this, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresent != null) {
            mPresent.onDestroy();
        }
        ButterKnife.unbind(this);
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    /*********************子类实现*****************************/


    //获取布局文件
    public abstract int getLayoutId();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();
}
