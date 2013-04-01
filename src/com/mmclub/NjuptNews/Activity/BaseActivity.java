package com.mmclub.NjuptNews.Activity;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import com.mmclub.NjuptNews.Update.CheckNewsService;

/**
 *  Author： Linxiangyu
 */


public class BaseActivity extends Activity {



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(1, 1, 1, "更新内容");//3,显式指定菜单项的组号，ID , 排序，标题
        menu.add(1, 2, 2, "往期列表");
        menu.add(1, 3, 3, "关于手机报");
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent1 = new Intent("com.mmclub.NjuptNews.UpdateContent");
                startService(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(BaseActivity.this, NewsListActivity.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(BaseActivity.this,  AboutActivity.class);
                startActivity(intent3);
                break;
        }
        return false;
    }
}
