package com.mmclub.NUPTNews.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import com.actionbarsherlock.app.SherlockActivity;
import com.mmclub.NUPTNews.Update.CheckNewsService;

/**
 *  Author： Linxiangyu
 */


public class BaseActivity extends SherlockActivity {


    @Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        menu.add(0,1,1,"关于");
        return true;
    }


    @Override
    public boolean onMenuItemSelected(int featureId, com.actionbarsherlock.view.MenuItem item) {
        if (item.getItemId() == 1){
            NavUtils.navigateUpTo(this, new Intent(this, AboutActivity.class));
        }
        return true;
    }
}
