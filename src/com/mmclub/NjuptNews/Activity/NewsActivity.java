package com.mmclub.NjuptNews.Activity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.mmclub.NjuptNews.R;
import com.mmclub.NjuptNews.Utils.FileUtils;

import com.mmclub.NjuptNews.R;

public class NewsActivity extends Activity {
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
            String filePath =  dir + String.valueOf(number) + "/content.txt";
            Log.d("TAG", filePath);
            File file = new File(filePath);
            if (!file.exists()){
                return "not content";
            }
            String content =  FileUtils.readFile(file);
            Log.d("TAG-Conten", content);
            return content;
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return " ";
        }
    }

    private Bitmap getBitmap(String dir, int number) {
        File file = new File(dir + String.valueOf(number) + "/img.png");
        if (file.exists()) {
            return BitmapFactory.decodeFile(file.getAbsolutePath());
        } else {
            return null;
        }
    }


    private void initWidget() {
        img1 = (ImageView) list.get(0).findViewById(R.id.img1);
        img2 = (ImageView) list.get(1).findViewById(R.id.img2);
        img3 = (ImageView) list.get(2).findViewById(R.id.img3);
        img4 = (ImageView) list.get(3).findViewById(R.id.img4);
        img5 = (ImageView) list.get(4).findViewById(R.id.img5);


        text1 = (TextView) list.get(0).findViewById(R.id.text1);
        text2 = (TextView) list.get(1).findViewById(R.id.text2);
        text3 = (TextView) list.get(2).findViewById(R.id.text3);
        text4 = (TextView) list.get(3).findViewById(R.id.text4);
        text5 = (TextView) list.get(4).findViewById(R.id.text5);

        String dirName =  Environment.getExternalStorageDirectory().getAbsolutePath() + "/nuptnews/1/";

        Log.d("TAG", dirName);


        Log.d("TAG-testNull", String.valueOf(text1 == null));
        text1.setText(getContent(dirName, 1));

        Log.d("TAG", "after 1");

        text2.setText(getContent(dirName, 2));
        text3.setText(getContent(dirName, 3));
        text4.setText(getContent(dirName, 4));
        text5.setText(getContent(dirName, 5));

        img1.setImageBitmap(getBitmap(dirName,1));
        img2.setImageBitmap(getBitmap(dirName,2));
        img3.setImageBitmap(getBitmap(dirName,3));
        img4.setImageBitmap(getBitmap(dirName,4));
        img5.setImageBitmap(getBitmap(dirName,5));


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = getLayoutInflater();



        list = new ArrayList<View>();
        list.add(inflater.inflate(R.layout.item1, null));
        list.add(inflater.inflate(R.layout.item2, null));
        list.add(inflater.inflate(R.layout.item3, null));
        list.add(inflater.inflate(R.layout.item4, null));
        list.add(inflater.inflate(R.layout.item5, null));


        imageViews = new ImageView[list.size()];
        ViewGroup main = (ViewGroup) inflater.inflate(R.layout.news_activity, null);
        // group是R.layou.main中的负责包裹小圆点的LinearLayout.
        ViewGroup group = (ViewGroup) main.findViewById(R.id.viewGroup);

        viewPager = (ViewPager) main.findViewById(R.id.viewPager);

        mWeekNumber = getIntent().getIntExtra(WEEK_NUMBER, 1);

        for (int i = 0; i < list.size(); i++) {
            imageView = new ImageView(NewsActivity.this);
            imageView.setLayoutParams(new LayoutParams(10, 10));
            imageView.setPadding(10, 0, 10, 0);
            imageViews[i] = imageView;
            if (i == 0) {
                // 默认进入程序后第一张图片被选中;
                imageViews[i].setBackgroundResource(R.drawable.dot_full);
            } else {
                imageViews[i].setBackgroundResource(R.drawable.dot_empty);
            }
            group.addView(imageView);
        }

        setContentView(main);




        viewPager.setAdapter(new MyAdapter());
        viewPager.setOnPageChangeListener(new MyListener());

        initWidget();
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