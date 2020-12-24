package com.zhy.module_aspectj;

import android.os.Bundle;
import android.view.View;

import com.zhy.module_aspectj.annotation.LoginCheck;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //登录按钮
    @LoginCheck
    public void login(View view) {
    }

    @LoginCheck
    public void regist(View view) {
    }

    @LoginCheck
    public void personal(View view) {
    }
}
