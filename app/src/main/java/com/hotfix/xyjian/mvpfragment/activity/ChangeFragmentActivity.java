package com.hotfix.xyjian.mvpfragment.activity;

import android.os.Bundle;

import com.hotfix.xyjian.mvpfragment.R;
import com.hotfix.xyjian.mvpfragment.base.BaseActivity;
import com.hotfix.xyjian.mvpfragment.fragment.TempFragment;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class ChangeFragmentActivity extends BaseActivity {
    public static final String TYPE = "type";

    @Override
    public void init() {
        Bundle extras = getIntent().getExtras();
        int anInt = extras.getInt(TYPE, -1);
        switch (anInt) {
            case 0:
                loadRootFragment(R.id.fl_content, TempFragment.newInstance());
                break;
            default:
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_fragment;
    }

    @Override
    public void initPresenter() {

    }


    @Override
    public FragmentAnimator getFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
