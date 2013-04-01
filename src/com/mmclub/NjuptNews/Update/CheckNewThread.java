package com.mmclub.NjuptNews.Update;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.mmclub.NjuptNews.NewsApplication;
import com.mmclub.NjuptNews.Utils.NetworkUtils;
import com.mmclub.NjuptNews.Utils.UnzipUtils;


public class CheckNewThread extends Thread {


    /**
     * 检测是否有更新。若有，则下载。
     */


    public static final String STORE_PATH = NewsApplication.DIR;
    Handler handler;

    public CheckNewThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        /**
         *
         */
        Log.d("TAG", "now in run");
        if (NetworkUtils.isNetWorkUseable(NewsApplication.getContext())) {

            Log.d("TAG", "in run");
            News news = getNews(NewsApplication.CHECK_NEW_URL);
            if (news != null) {
                String zipPackName = String.valueOf(news.week_number) + ".zip";
                File file = new File(zipPackName);
                if (!file.exists()) {
                    Log.d("TAG", zipPackName);
                    downloadFiles(news.url, zipPackName);
                    UnzipUtils.unpackZip(STORE_PATH, zipPackName);
                    Log.d("TAG", "Done");
                    Message message = new Message();
                    message.arg1 = CheckNewsService.OK;
                    message.what = news.week_number;
                    handler.sendMessage(message);
                }
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

            Log.d("TAG", "download finish");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private News getNews(String urlStr) {
        /**
         *
         * 传入一个url，读取这个url上的文本文件，第一行为最新期数，第二行为内容URL，用一个News实例保持信息
         */
        String s = null;
        String line = null;
        BufferedReader buffer = null;
        java.net.URL url = null;
        HttpURLConnection urlConn = null;
        try {
            url = new URL(urlStr);
            urlConn = (HttpURLConnection) url.openConnection();
            buffer = new BufferedReader(new InputStreamReader(
                    urlConn.getInputStream()));
            News news = new News();
            news.week_number = Integer.valueOf(buffer.readLine().toString());
            news.url = buffer.readLine().toString();
            Log.d("TAG", news.url);
            return news;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
