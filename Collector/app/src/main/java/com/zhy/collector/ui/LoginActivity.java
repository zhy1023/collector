package com.zhy.collector.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhy.collector.R;
import com.zhy.collector.base.BaseView;
import com.zhy.collector.ui.login.LoginContract;
import com.zhy.collector.ui.login.LoginPresenter;

/**
 * Created by ZY on 2019/9/17
 * DESC: class LoginActivity
 */
public class LoginActivity extends BaseView<LoginPresenter, LoginContract.View> {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText etName;
    private EditText etPwd;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        etName = findViewById(R.id.et_name);
        etPwd = findViewById(R.id.et_pwd);
        btn = findViewById(R.id.bt_login);
    }

    public void doLoginAction(View view) {
        String name = etName.getText().toString();
        String pwd = etPwd.getText().toString();
        p.getContract().requestLogin(name, pwd);
    }


    @Override
    public LoginContract.View getContract() {
        return new LoginContract.View() {
            @Override
            public void handleResult(String result) {
                Log.i(TAG, "result = " + result);
                Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        };
    }


    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter();
    }
}
