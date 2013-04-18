package com.mmclub.NUPTNews.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.mmclub.NUPTNews.R;

/**
 * Created with IntelliJ IDEA.
 * User: linxiangyu
 * Date: 13-4-18
 * Time: 上午8:23
 * To change this template use File | Settings | File Templates.
 */
public class PrefsActivity extends SherlockPreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.prefs);
    }


    @Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        menu.add(0,0,0,"关于");
        menu.add(0,1,1,"列表");
        menu.add(0,2,2,"最新内容");
        return true;
    }


    @Override
    public boolean onMenuItemSelected(int featureId, com.actionbarsherlock.view.MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Intent intent1 = new Intent(this, AboutActivity.class);
                startActivity(intent1);
                break;
            case 1:
                Intent intent2 = new Intent(this, NewsListActivity.class);
                startActivity(intent2);
                break;
            case 2:
                Intent intent3 = new Intent(this, ScreenSlideActivity.class);
                startActivity(intent3);
                break;
        }
        return true;
    }
}