package com.hotfix.xyjian.mvpfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hotfix.xyjian.mvpfragment.activity.ChangeFragmentActivity;
import com.hotfix.xyjian.mvpfragment.base.BaseActivity;
import com.hotfix.xyjian.mvpfragment.kotlin.Customer;

import butterknife.OnClick;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {

    }

    @OnClick({R.id.btn_jump})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_jump:
                Intent intent = new Intent(this, ChangeFragmentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(ChangeFragmentActivity.TYPE, 0);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;
        }
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
