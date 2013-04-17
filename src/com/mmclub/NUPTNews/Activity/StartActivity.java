package com.mmclub.NUPTNews.Activity;




import com.mmclub.NUPTNews.NewsApplication;
import com.mmclub.NUPTNews.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.view.Window;
import android.view.WindowManager;

import java.io.File;

/**
 * Author: PanLei
 */
public class StartActivity extends Activity{

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏
        setContentView(R.layout.start_activity);

        File dir = new File(NewsApplication.DIR);
        if (!dir.exists()){
            dir.mkdir();
        }

        //闪屏的核心代码

        new Handler().postDelayed(new Runnable() {

            public void run() {
                // TODO Auto-generated method stub
                Intent intent = new Intent(StartActivity.this, NewsActivity.class);
                startActivity(intent);
                StartActivity.this.finish();

            }
        }, 1000);//闪屏时间为1秒

    };


}
