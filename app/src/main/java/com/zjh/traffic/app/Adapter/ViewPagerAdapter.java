package com.zjh.traffic.app.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private List<View> views;   // 我们引导页的list
    private Context context;    // 上下文

    public ViewPagerAdapter(List<View> views, Context context) {
        this.views = views;
        this.context = context;
    }

    // 移除一个view
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView(views.get(position));
    }

    // 加载一个view
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public int getCount() { // 必写的方法 返回当前views的数量
        return this.views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) { //必写的方法 判断当前的view是否是我们需要的对象
        return (view == object);
    }
}