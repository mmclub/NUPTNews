package com.mmclub.NUPTNews.Activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.mmclub.NUPTNews.NewsApplication;
import com.mmclub.NUPTNews.Update.CheckNewsService;

import java.io.File;

/**
 * Author : Linxiangyu
 */
public class NewsListActivity extends ListActivity {


    String[] file_array;


    ListView listView;

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(NewsListActivity.this, NewsActivity.class);
        intent.putExtra(NewsActivity.WEEK_NUMBER, findWeekNumberByPositon(position));
        startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init_file_array();
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, file_array));



    }

    private void init_file_array() {
        File[] files = new File(NewsApplication.DIR).listFiles();
        int len = 0;
        for (File file : files){
            if (file.getName().matches("^[0-9]*$"))
                len++;
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
                Intent intent1 = new Intent(NewsListActivity.this, CheckNewsService.class);
                startService(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(NewsListActivity.this, NewsListActivity.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(NewsListActivity.this,  AboutActivity.class);
                startActivity(intent3);
                break;
        }
        return false;
    }

}