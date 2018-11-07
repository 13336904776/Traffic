package com.zjh.traffic.app.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjh.traffic.R;
import com.zjh.traffic.app.Adapter.ViewPagerAdapter_fragment;
import com.zjh.traffic.app.Fragment.ChildFragment.GetUserCardusedFragment;
import com.zjh.traffic.app.Fragment.ChildFragment.PersonalDataFragment;
import com.zjh.traffic.app.Fragment.ChildFragment.ThresholdSettingFragment;

import java.util.ArrayList;
import java.util.List;

public class PersonalcenterFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter_fragment myViewPagerAdapter;
    private List<Fragment> fragment;
    private List<String> title;

    private PersonalDataFragment personalDataFragment;
    private GetUserCardusedFragment getUserCardusedFragment;
    private ThresholdSettingFragment thresholdSettingFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personalcenter, null);
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
        personalDataFragment = new PersonalDataFragment();
        getUserCardusedFragment = new GetUserCardusedFragment();
        thresholdSettingFragment = new ThresholdSettingFragment();
        fragment.add(personalDataFragment);
        fragment.add(getUserCardusedFragment);
        fragment.add(thresholdSettingFragment);
        title.add("个人信息");
        title.add("充值记录");
        title.add("阈值设置");
        for (int i = 0; i < title.size(); i++)
            tabLayout.addTab(tabLayout.newTab().setText(title.get(i)));
        myViewPagerAdapter = new ViewPagerAdapter_fragment(getChildFragmentManager(), fragment, title);
        viewPager.setAdapter(myViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}