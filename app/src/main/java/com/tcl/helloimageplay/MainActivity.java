package com.tcl.helloimageplay;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    private ViewPager imageViewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private int[] datas = new int[Images.images.length+2];


    private ScheduledExecutorService scheduledExecutorService;

    private int currentIndex;

    private int index;
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    imageViewPager.setCurrentItem(index);
                    break;
            }
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        imageViewPager = findViewById(R.id.image_viewPager);
        //viewPagerAdapter = new ViewPagerAdapter(this,Images.images);
        viewPagerAdapter = new ViewPagerAdapter(this,datas);
        //绑定适配器
        imageViewPager.setAdapter(viewPagerAdapter);
        //选择一个较大的条目选中
        //imageViewPager.setCurrentItem(Images.images.length*1000,true);
        imageViewPager.setCurrentItem(1,true);

        imageViewPager.addOnPageChangeListener(new ViewPagerListener());

    }


    @Override
    protected void onStart() {
        super.onStart();

        //初始化自动轮播定时器
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                index++;
                handler.sendEmptyMessage(1);

            }
        },3,3,TimeUnit.SECONDS);

    }

    //当界面不可见的时候
    @Override
    protected void onStop() {
        super.onStop();
        if (scheduledExecutorService != null){
            scheduledExecutorService.shutdown();
        }
    }

    private void initData(){
        datas[0] = Images.images[Images.images.length-1];


        for (int i = 0 ;i<Images.images.length;i++){
            datas[i+1]=Images.images[i];
        }

        datas[datas.length-1] = Images.images[0];
    }


    class ViewPagerListener implements ViewPager.OnPageChangeListener{


        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            index = i;

            if (index == 0){
                index = datas.length - 2;
            }else if (index == datas.length-1){
                index = 1;
            }


            if (index!=i){
                imageViewPager.setCurrentItem(index,true);
            }



        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }
}
