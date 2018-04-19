package com.hotfix.xyjian.mvpfragment.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hotfix.xyjian.mvpfragment.R;
import com.hotfix.xyjian.mvpfragment.base.BaseFragment;
import com.hotfix.xyjian.mvpfragment.contract.TempContact;
import com.hotfix.xyjian.mvpfragment.retrofit.RetrofitApiManager;

import butterknife.Bind;
import butterknife.OnClick;

public class TempFragment extends BaseFragment<TempContact.TempPresenter> implements TempContact.TempView {
    @Bind(R.id.textView)
    TextView textView;

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
        mPresent.setView(this);
    }

    @OnClick({R.id.button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                mPresent.getInfo(_mActivity, "ldkgdjg");
                break;
        }
    }


    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        Log.d("tag", "<showErrorTip>" + System.currentTimeMillis());
        Toast.makeText(_mActivity, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showInfo() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RetrofitApiManager.getInstance().cancel("ldkgdjg");
    }
}
