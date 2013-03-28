package com.mmclub.NjuptNews.Activity;




import com.mmclub.NjuptNews.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.view.Window;
import android.view.WindowManager;

/**
 * Created with IntelliJ IDEA.
 * User: linxiangyu
 * Date: 13-3-13
 * Time: 涓嬪崍2:53
 * To change this template use File | Settings | File Templates.
 */
public class StartActivity extends Activity {



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏
        setContentView(R.layout.start_activity);

        //闪屏的核心代码

        new Handler().postDelayed(new Runnable() {

            public void run() {
                // TODO Auto-generated method stub
                Intent intent = new Intent(StartActivity.this,NewsActivity.class);
                startActivity(intent);
                StartActivity.this.finish();

            }
        }, 1000);//闪屏时间为1秒

    };

}
