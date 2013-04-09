package com.mmclub.NjuptNews;

import android.app.Application;
import android.content.Context;

import java.net.ContentHandlerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: linxiangyu
 * Date: 13-3-24
 * Time: 下午12:52
 * To change this template use File | Settings | File Templates.
 */
public class NewsApplication extends Application {


    public static final String CHECK_NEW_URL1 =  "https://trello-attachments.s3.amazonaws.com/513d607fdd5734c52f0013f1/514068b68f0f51413c001ac4/b9a14c0b0b24445b483753b95df7d32a/new.txt";
    public static final String DIR = "/sdcard/nuptnews/";
    public static final String CHECK_NEW_URL =  "http://linxiangyu.tk/1.txt";


    private static NewsApplication instance;

    public NewsApplication() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }


}
