package com.zhy.collector.base;

/**
 * Created by ZY on 2019/9/17
 * DESC: class BaseModel
 */
public abstract class BaseModel<P extends BasePresenter, Contract> {

    protected P p;


    public BaseModel(P p) {
        this.p = p;
    }

    public abstract Contract getContract();
}
