package com.zhy.collector.ui.login;

/**
 * Created by ZY on 2019/9/17
 * DESC: class LoginContract
 */
public interface LoginContract {

    interface Model {
        void executeLogin(String name, String pwd) throws Exception;
    }

    interface View {
        void handleResult(String result);
    }

    interface Presenter {
        void requestLogin(String name, String pwd);

        void handleResult(String response);
    }
}
