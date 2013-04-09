package com.mmclub.NjuptNews.Update;



import android.util.Log;
import com.mmclub.NjuptNews.Activity.NewsActivity;
import com.mmclub.NjuptNews.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;






public class CheckNewsService extends Service {

    public static final String NO_NEWS = "no_news";
    public static final int NO_ANY_NEWS = -1;
    public static final int OK = 100000;

    Handler handler;

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG", "Create Service");
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what != NO_ANY_NEWS && msg.arg1 == OK){
                    Log.d("TAG", String.valueOf(msg.what));
                    createNotification(msg.what);
                }
            }
        };


    }

    public void onStart(Intent intent, int startId) {

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new CheckNewThread(handler).start();
        return super.onStartCommand(intent, flags, startId);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void createNotification(int week_number) {
        // 定义NotificationManager
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
        // 定义通知栏展现的内容信息
        int icon = R.drawable.ic_launcher;
        CharSequence tickerText = "南邮手机报更新";
        long when = System.currentTimeMillis();
        Notification notification = new Notification(icon, tickerText, when);
        // 定义下拉通知栏时要展现的内容信息
        Context context = getApplicationContext();
        CharSequence contentTitle = "最新的一期已下载";
        CharSequence contentText = "点击查看";
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent notificationIntent = new Intent(Intent.ACTION_MAIN);
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        notificationIntent.setClass(this,
                com.mmclub.NjuptNews.Activity.NewsActivity.class);

        notificationIntent.putExtra(NewsActivity.WEEK_NUMBER, week_number); //传送期数

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, 0);
        notification.setLatestEventInfo(context, contentTitle, contentText,
                contentIntent);
        // 用mNotificationManager的notify方法通知用户生成标题栏消息通知
        mNotificationManager.notify(1, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



}
