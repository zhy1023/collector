package com.zhy.collector.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
import com.zhy.collector.utils.DataUtils;
import com.zhy.collector.utils.LogUtils;

/**
 * Created by ZY on 2019/9/17
 * DESC: class LoginActivity
 */
public class LoginActivity extends BaseView<LoginPresenter, LoginContract.View> {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private View mRootView;
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
        mRootView = findViewById(R.id.rootView);
        etName = findViewById(R.id.et_name);
        etPwd = findViewById(R.id.et_pwd);
        btn = findViewById(R.id.bt_login);
        int deep = DataUtils.getMaxDeep(mRootView);
        LogUtils.i(TAG, "deep :" + deep);
    }

    public void doLoginAction(View view) {
       /* String name = etName.getText().toString();
        String pwd = etPwd.getText().toString();
        p.getContract().requestLogin(name, pwd);*/
//        run();
        startActivity(new Intent(getApplication(),MainActivity.class));
    }


    private void run() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                Looper looper = Looper.myLooper();
                Handler handler = new Handler(looper) {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if (msg.what == 0) {
                            LogUtils.i(TAG, "123");
                        }
                    }
                };
                handler.sendEmptyMessage(0);
                Looper.loop();
            }
        }.start();
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
