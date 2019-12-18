package com.zhy.collector.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ZY on 2019/9/17
 * DESC: class BaseView
 */
public abstract class BaseView<P extends BasePresenter, Contract> extends AppCompatActivity {
    protected P p;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p = getPresenter();
        p.bindView(this);
    }


    protected abstract P getPresenter();

    public abstract Contract getContract();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        p.unBindView();
    }
}
