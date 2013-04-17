package com.mmclub.NUPTNews.Activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockListActivity;
import com.mmclub.NUPTNews.NewsApplication;

import java.io.File;

/**
 * Author : Linxiangyu
 */
public class NewsListActivity extends SherlockListActivity {


    String[] file_array;


    ListView listView;

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(NewsListActivity.this, ScreenSlideActivity.class);
        intent.putExtra(ScreenSlideActivity.EXTIR_CONTENT_DIR, NewsApplication.DIR + String.valueOf(findWeekNumberByPositon(position)) + "/");
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init_file_array();
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, file_array));

    }

    @Override
    protected void onResume() {
        super.onResume();
        init_file_array();
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, file_array));
    }

    private void init_file_array() {
        File[] files = new File(NewsApplication.DIR).listFiles();
        int len = 0;
        for (File file : files){

            if (file.getName().matches("^[0-9]*$")){
                len++;
            }
        }
        file_array = new String[len];
        int now = 0;
        for (File file : files){
            if (file.getName().matches("^[0-9]*$")){
                file_array[now] = file.getName();
                now++;
            }
        }
    }

    private int findWeekNumberByPositon(int position){
        return Integer.valueOf(file_array[position]);
    }



    @Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        menu.add(0,0,0,"关于");
        menu.add(0,1,1,"最新内容");
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
                Intent intent2 = new Intent(this, ScreenSlideActivity.class);
                startActivity(intent2);
                break;
        }
        return true;
    }

}