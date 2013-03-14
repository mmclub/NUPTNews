package com.mmclub.NjuptNews.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created with IntelliJ IDEA.
 * User: linxiangyu
 * Date: 13-3-13
 * Time: 下午2:53
 * Email: lxyweb@gmail.com
 */
public class NetworkUtils {

    public static boolean isNetWorkUseable(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info == null || !info.isAvailable())
                    return false;
                else
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
