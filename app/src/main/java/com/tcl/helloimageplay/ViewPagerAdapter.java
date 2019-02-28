package com.tcl.helloimageplay;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private int[] datas;


    public ViewPagerAdapter(Context context,int[] datas){
        this.context =context;
        this.datas =datas;
        layoutInflater =LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        //return Integer.MAX_VALUE;
        return datas.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    //用于渲染每一页的数据
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        //int index = position % datas.length;
        View view = layoutInflater.inflate(R.layout.viewpage_item,null);
        ImageView imageView = view.findViewById(R.id.image_item);
        //设置显示的图片
        //imageView.setImageResource(datas[index]);
        imageView.setImageResource(datas[position]);
        //添加到ViewPager里面
        container.addView(view);

        return view;
    }
}
