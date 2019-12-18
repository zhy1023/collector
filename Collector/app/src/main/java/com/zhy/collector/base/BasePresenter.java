package com.zhy.collector.base;

import java.lang.ref.WeakReference;

/**
 * Created by ZY on 2019/9/17
 * DESC: class BasePresenter
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseModel, Contract> {

    protected M m;
    protected WeakReference<V> vWeakReference;

    public BasePresenter() {
        this.m =getModel();
    }

    public void bindView(V v) {
        vWeakReference = new WeakReference<>(v);
    }

    public V getView() {
        if (null != vWeakReference) {
            return vWeakReference.get();
        }
        return null;
    }


    public void unBindView() {
        if (vWeakReference != null) {
            vWeakReference.clear();
            vWeakReference = null;
        }
    }

    public abstract Contract getContract();

    public abstract M getModel();
}
