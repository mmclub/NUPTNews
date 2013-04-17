package com.mmclub.NUPTNews;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.widget.Toast;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

/**
 * Created with IntelliJ IDEA.
 * User: linxiangyu
 * Date: 13-3-24
 * Time: 下午12:52
 * To change this template use File | Settings | File Templates.
 */
public class NewsApplication extends Application {

    public static boolean IS_DEBUG = true;

    public static final String CHECK_NEW_URL1 =  "https://trello-attachments.s3.amazonaws.com/513d607fdd5734c52f0013f1/514068b68f0f51413c001ac4/b9a14c0b0b24445b483753b95df7d32a/new.txt";
    public static final String DIR = "/sdcard/nuptnews/";
    public static final String CHECK_NEW_URL =  "http://linxiangyu.tk/1.txt";


    private static NewsApplication instance;

    public NewsApplication() {
        instance = this;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    public static Context getContext() {
        return instance;
    }

    /**
     *设置通知提示方式 - 基础属性
     */
    private void setStyleBasic(){
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this);
        builder.statusBarDrawable = R.drawable.ic_launcher;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为点击后自动消失
        builder.notificationDefaults = Notification.DEFAULT_SOUND;  //设置为铃声（ Notification.DEFAULT_SOUND）或者震动（ Notification.DEFAULT_VIBRATE）
        JPushInterface.setPushNotificationBuilder(1, builder);
    }


    /**
     *设置通知栏样式 - 定义通知栏Layout
     */
    private void setStyleCustom(){
        CustomPushNotificationBuilder builder = new CustomPushNotificationBuilder(this,R.layout.customer_notitfication_layout,R.id.icon, R.id.title, R.id.text);
        builder.layoutIconDrawable = R.drawable.ic_launcher;
        builder.developerArg0 = "developerArg2";
        JPushInterface.setPushNotificationBuilder(2, builder);
    }


}
