package com.zhy.collector.ui.login;

import com.zhy.collector.base.BasePresenter;
import com.zhy.collector.ui.LoginActivity;

/**
 * Created by ZY on 2019/9/17
 * DESC: class LoginPresenter
 */
public class LoginPresenter extends BasePresenter<LoginActivity, LoginModel, LoginContract.Presenter> {


    @Override
    public LoginContract.Presenter getContract() {
        return new LoginContract.Presenter() {
            @Override
            public void requestLogin(String name, String pwd) {
                try {
                    m.getContract().executeLogin(name, pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void handleResult(String response) {
                getView().getContract().handleResult(response);
            }
        };
    }

    @Override
    public LoginModel getModel() {
        return new LoginModel(this);
    }
}
