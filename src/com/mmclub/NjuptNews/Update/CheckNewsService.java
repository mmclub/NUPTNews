package com.mmclub.NjuptNews.Update;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created with IntelliJ IDEA.
 * User: linxiangyu
 * Date: 13-3-13
 * Time: 下午3:22
 * To change this template use File | Settings | File Templates.
 */
public class CheckNewsService extends Service {
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void onDestroy() {
        super.onDestroy();    //To change body of overridden methods use File | Settings | File Templates.
    }

}
