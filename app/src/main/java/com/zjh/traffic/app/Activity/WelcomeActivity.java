package com.zjh.traffic.app.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjh.traffic.R;
import com.zjh.traffic.app.Adapter.ViewPagerAdapter;
import com.zjh.traffic.app.Util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends BaseActivity {
    private ViewPager viewPager;
    private ViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private List<View> layouts;
    private Button btnSkip, btnNext;
    private SharedPreferencesUtil spUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //在setContentView()前检查是否第一次运行
        spUtil = SharedPreferencesUtil.getInstance(this);
        if (!(Boolean) spUtil.getSP("IsFirstTimeLaunch", true)) {
            launchHomeScreen();
            finish();
        }
        //取消标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //让状态栏透明
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_welcome);

        viewPager = findViewById(R.id.view_pager);
        dotsLayout = findViewById(R.id.layoutDots);
        btnNext = findViewById(R.id.btn_next);
        btnSkip = findViewById(R.id.btn_skip);
        layouts = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(this);
        //添加欢迎页面
        layouts.add(inflater.inflate(R.layout.welcome_slide1, null));
        layouts.add(inflater.inflate(R.layout.welcome_slide2, null));
        layouts.add(inflater.inflate(R.layout.welcome_slide3, null));
        //添加点
        addBottomDots(0);

        //让状态栏透明
        changeStatusBarColor();

        myViewPagerAdapter = new ViewPagerAdapter(layouts, this);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if (current < layouts.size()) {
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.size()];


        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));//圆点
            dots[i].setTextSize(30);
            dots[i].setTextColor(Color.WHITE);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[currentPage].setTextColor(Color.BLACK);
        }
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        spUtil.putSP("IsFirstTimeLaunch", false);
        startActivity(new Intent(this, LoginActivity.class));
        WelcomeActivity.this.finish();
    }

    /**
     * 让状态栏变透明
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            //改变下一步按钮text  “NEXT”或“GOT IT”
            if (position == layouts.size() - 1) {
                btnNext.setText("进入");
                btnSkip.setVisibility(View.GONE);
            } else {
                btnNext.setText("下一页");
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
