package com.mmclub.NjuptNews.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mmclub.NjuptNews.R;
/**
 * Created with IntelliJ IDEA.
 * User: acer
 * Date: 13-3-23
 * Time: 下午4:10
 * To change this template use File | Settings | File Templates.
 */
public class AboutActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(1, Menu.FIRST, 1, "关于");//3,显式指定菜单项的组号，ID , 排序，标题
        menu.add(1, Menu.FIRST+1, 2, "菜单项2");
        return true;
    }
    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case 1:setContentView(R.layout.about_activity);
                Toast.makeText(this, "1", Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(this, "2", Toast.LENGTH_LONG).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }










}
