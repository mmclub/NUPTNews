package com.mmclub.NUPTNews.Update;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.mmclub.NUPTNews.Activity.ScreenSlideActivity;
import com.mmclub.NUPTNews.NewsApplication;
import com.mmclub.NUPTNews.R;
import com.mmclub.NUPTNews.Utils.NetworkUtils;
import com.mmclub.NUPTNews.Utils.UnzipUtils;


public class DownloadThread extends Thread {

    public static final String STORE_PATH = NewsApplication.DIR;

    String url;
    String content_dir;
    String full_content_dir;


    public DownloadThread(String url, String name) {
        this.url = url;
        this.content_dir = name;
    }


    @Override
    public void run() {
        if (NetworkUtils.isNetWorkUseable(NewsApplication.getContext())) {
            String zipPackName =  content_dir + ".zip";
            Log.d("TAG", "zip Name:" + zipPackName);
            File file = new File(STORE_PATH + zipPackName);
            if (!file.exists()) {
                downloadFiles(url, zipPackName);
                UnzipUtils.unpackZip(STORE_PATH, zipPackName);
            }
        }
    }




    private String downloadFiles(String download_url, String file_name) {
        /**
         * 传入下载URL，文件名，进行下载
         */
        try {
            URL url = new URL(download_url);
            File dir = new File(STORE_PATH);
            dir.mkdir();
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.connect();
            InputStream input = new BufferedInputStream(url.openStream());
            OutputStream output = new FileOutputStream(STORE_PATH + file_name);
            byte data[] = new byte[1024];
            int count;
            while ((count = input.read(data)) != -1) {
                output.write(data, 0, count);
            }
            output.flush();
            output.close();

            if (NewsApplication.IS_DEBUG)
                Log.d("NUPTNews", "download finish");
            createNotification();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void createNotification() {
        // 定义NotificationManager
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) ((NewsApplication.getContext()).getSystemService(ns));
        // 定义通知栏展现的内容信息
        int icon = R.drawable.ic_launcher;
        CharSequence tickerText = "南邮手机报更新";
        long when = System.currentTimeMillis();
        Notification notification = new Notification(icon, tickerText, when);
        // 定义下拉通知栏时要展现的内容信息
        Context context = NewsApplication.getContext();
        CharSequence contentTitle = "最新的一期已下载";
        CharSequence contentText = "点击查看";
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent notificationIntent = new Intent(Intent.ACTION_MAIN);
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        notificationIntent.setClass(context,
                ScreenSlideActivity.class);

        notificationIntent.putExtra(ScreenSlideActivity.EXTIR_CONTENT_DIR,  content_dir);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, 0);
        notification.setLatestEventInfo(context, contentTitle, contentText,
                contentIntent);
        // 用mNotificationManager的notify方法通知用户生成标题栏消息通知
        mNotificationManager.notify(1, notification);
    }



}
