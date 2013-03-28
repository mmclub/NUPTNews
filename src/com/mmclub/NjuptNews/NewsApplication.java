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

    private static NewsApplication instance;

    public NewsApplication() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }


}
