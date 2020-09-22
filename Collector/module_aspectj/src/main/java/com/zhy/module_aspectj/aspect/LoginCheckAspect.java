package com.zhy.module_aspectj.aspect;

import android.content.Context;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ； ZY
 * @date : 2020/9/22
 * @describe :
 */
@Aspect
public class LoginCheckAspect {
    private static final String TAG = "LoginCheckAspect";

    @Pointcut("execution(@com.zhy.module_aspectj.annotation.LoginCheck * *(..))")
    public void pointCut() {
    }

   /* @Around("pointCut()")
    public Object jointPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Context context = (Context) joinPoint.getThis();
        if (false) {
            Log.i(TAG, "已登录!");
            return joinPoint.proceed();
        } else {
            Log.i(TAG, "检测到未登录，请先登录 ！");
            return null;
        }
    }*/

    @Before("pointCut()")
    public void beforeJointPoint(JoinPoint joinPoint) throws Throwable{
        if (false) {
            Log.i(TAG, "已登录!");
        } else {
            Log.i(TAG, "检测到未登录，请先登录 ！");
        }
    }
}
