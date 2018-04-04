package com.hotfix.xyjian.mvpfragment.fragment;


import android.os.Bundle;

import com.hotfix.xyjian.mvpfragment.R;
import com.hotfix.xyjian.mvpfragment.base.BaseFragment;
import com.hotfix.xyjian.mvpfragment.contract.TempContact;

public class TempFragment extends BaseFragment<TempContact.TempPresenter,TempContact.TempMode> implements TempContact.TempView {


    public TempFragment() {
    }
    public static TempFragment newInstance() {
        TempFragment fragment = new TempFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_temp;
    }

    @Override
    public void initPresenter() {
        mPresent.setViewAndModel(this,mMode);
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public void showInfo() {

    }
}
