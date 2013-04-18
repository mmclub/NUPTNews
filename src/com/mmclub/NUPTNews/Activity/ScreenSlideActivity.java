/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mmclub.NUPTNews.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.mmclub.NUPTNews.NewsApplication;
import com.mmclub.NUPTNews.R;
import com.mmclub.NUPTNews.Utils.MediaUtils;

import java.io.File;


public class ScreenSlideActivity extends SherlockFragmentActivity {

    public static final String EXTIR_CONTENT_DIR = "DIR";

    private static final int NUM_PAGES = 9;

    private ViewPager mPager;

    private String mContentDir;

    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);


        mContentDir = initContentDir();
        Log.d("TAG", "ContentDir " + mContentDir);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

                invalidateOptionsMenu();
            }
        });

        if (NewsApplication.isPlayMusic()){

            if (new File(mContentDir + "back.mp3").exists()){

                 MediaUtils.getInstance().play(mContentDir + "back.mp3");
            }
        }
    }



    private String initContentDir(){
        if ((getIntent().getStringExtra(EXTIR_CONTENT_DIR)) == null){
            Log.d("TAG", "null" + getIntent().getStringExtra(EXTIR_CONTENT_DIR));
            return NewsApplication.getNewsetDir();
        }else{
            return getIntent().getStringExtra(EXTIR_CONTENT_DIR);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        getSupportMenuInflater().inflate(R.menu.activity_screen_slide, menu);

        menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() > 0);

        // Add either a "next" or "finish" button to the action bar, depending on which page
        // is currently selected.
        MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
                (mPager.getCurrentItem() == mPagerAdapter.getCount() - 1)
                        ? R.string.action_finish
                        : R.string.action_next);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        menu.add(Menu.NONE, 5, 5, "关于");
        menu.add(Menu.NONE, 6, 6, "列表");
        menu.add(Menu.NONE, 10, 10, "设置");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, AboutActivity.class));
                return true;

            case R.id.action_previous:
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                return true;

            case R.id.action_next:
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                return true;

            case 5:
                startActivity( new Intent(this, AboutActivity.class));
                return true;

            case 6:
                startActivity(new Intent(this, NewsListActivity.class));
                return true;

            case 10:
                startActivity( new Intent(this, PrefsActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.create(position, mContentDir);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}
