package com.zjh.traffic.app.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjh.traffic.R;
import com.zjh.traffic.app.Adapter.ViewPagerAdapter_fragment;
import com.zjh.traffic.app.Fragment.ChildFragment.AirQualityFragment;
import com.zjh.traffic.app.Fragment.ChildFragment.DataAnalysisItemFragment1;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据分析
 */
public class DataAnalysisFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter_fragment myViewPagerAdapter;
    private List<Fragment> fragment;
    private List<String> title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dataanalysis, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        fragment = new ArrayList<>();
        title = new ArrayList<>();
        fragment.add(new DataAnalysisItemFragment1());
        fragment.add(new DataAnalysisItemFragment1());
        fragment.add(new DataAnalysisItemFragment1());
        fragment.add(new DataAnalysisItemFragment1());
        fragment.add(new DataAnalysisItemFragment1());
        fragment.add(new DataAnalysisItemFragment1());
        fragment.add(new DataAnalysisItemFragment1());
        title.add("");
        title.add("");
        title.add("");
        title.add("");
        title.add("");
        title.add("");
        title.add("");
        for (int i = 0; i < title.size(); i++)
            tabLayout.addTab(tabLayout.newTab().setText(title.get(i)));
        myViewPagerAdapter = new ViewPagerAdapter_fragment(getChildFragmentManager(), fragment, title);
        viewPager.setAdapter(myViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
