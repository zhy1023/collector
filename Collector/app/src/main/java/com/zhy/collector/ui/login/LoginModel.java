package com.zhy.collector.ui.login;

import android.text.TextUtils;

import com.zhy.collector.base.BaseModel;

/**
 * Created by ZY on 2019/9/17
 * DESC: class LoginModel
 */
public class LoginModel extends BaseModel<LoginPresenter, LoginContract.Model> {


    public LoginModel(LoginPresenter loginPresenter) {
        super(loginPresenter);
    }

    @Override
    public LoginContract.Model getContract() {
        return new LoginContract.Model() {
            @Override
            public void executeLogin(String name, String pwd) throws Exception {
                if (TextUtils.equals("zhy", name) && TextUtils.equals("123", pwd)) {
                    p.getContract().handleResult("login Success!!");
                } else {
                    p.getContract().handleResult("login fail!!");

                }
            }
        };
    }
}
