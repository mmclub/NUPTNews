package com.mmclub.NUPTNews.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import com.actionbarsherlock.app.SherlockActivity;

/**
 *  Author： Linxiangyu
 */


public class BaseActivity extends SherlockActivity {


    @Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        menu.add(0,0,0,"关于");
        menu.add(0,1,1,"列表");
        menu.add(0,2,2,"设置");
        return true;
    }


    @Override
    public boolean onMenuItemSelected(int featureId, com.actionbarsherlock.view.MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Intent intent1 = new Intent(BaseActivity.this, AboutActivity.class);
                startActivity(intent1);
                break;
            case 1:
                Intent intent2 = new Intent(BaseActivity.this, NewsListActivity.class);
                startActivity(intent2);
                break;
            case 2:
                Intent intent3 = new Intent(BaseActivity.this, PrefsActivity.class);
                startActivity(intent3);
                break;
        }
        return true;
    }
}
