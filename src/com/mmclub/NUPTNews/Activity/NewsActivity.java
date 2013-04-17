package com.mmclub.NUPTNews.Activity;

import java.io.File;
import java.util.ArrayList;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.*;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.mmclub.NUPTNews.NewsApplication;
import com.mmclub.NUPTNews.R;
import com.mmclub.NUPTNews.Utils.FileUtils;

/**
 * Author : Linxiangyu
 */
public class NewsActivity extends BaseActivity {
    /*
     * Reference: http://blog.csdn.net/wangkuifeng0118/article/details/7388166
     */

    public static final String WEEK_NUMBER = "week_number";

    ViewPager viewPager;
    ArrayList<View> list;
    ViewGroup main, group;
    ImageView imageView;
    ImageView[] imageViews;
    int mWeekNumber;
    ImageView mBackGroud;
    ImageView img1, img2, img3, img4, img5;
    TextView text1, text2, text3, text4, text5;

    private String getContent(String dir, int number) {
        try {

            String filePath = dir + String.valueOf(number) + "/content.txt";
            Log.d("TAG", filePath);
            File file = new File(filePath);
            if (!file.exists()) {
                return "not content";
            }
            String content = FileUtils.readFile(file);
            Log.d("TAG-Conten", content);
            return content;
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return " ";
        }
    }

    private Bitmap getBitmap(String dir, int number) {
        File file1 = new File(dir + String.valueOf(number) + "/img.png");
        File file2 = new File(dir + String.valueOf(number) + "/img.jpg");
        if (file1.exists()) {
            return BitmapFactory.decodeFile(file1.getAbsolutePath());
        } else if (file2.exists()) {
            return BitmapFactory.decodeFile(file2.getAbsolutePath());
        } else {
            return null;
        }
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            // TODO Auto-generated method stub
            ((ViewPager) arg0).removeView(list.get(arg1));
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            // TODO Auto-generated method stub
            ((ViewPager) arg0).addView(list.get(arg1));
            return list.get(arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub

        }
    }

    class MyListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int arg0) {
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[arg0]
                        .setBackgroundResource(R.drawable.dot_full);
                if (arg0 != i) {
                    imageViews[i]
                            .setBackgroundResource(R.drawable.dot_empty);
                }
            }

        }

    }


}