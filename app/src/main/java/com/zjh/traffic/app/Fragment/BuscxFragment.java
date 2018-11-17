package com.zjh.traffic.app.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.zjh.traffic.R;
import com.zjh.traffic.app.Adapter.MyBaseExpandableListAdapter;
import com.zjh.traffic.app.Bean.buscxGroupBean;
import com.zjh.traffic.app.Bean.buscxItemBean;

import java.util.ArrayList;
import java.util.List;

public class BuscxFragment extends Fragment {
    private List<buscxGroupBean> buscxGroupList;
    private List<buscxItemBean> buscxItemList;
    private List<List<buscxItemBean>> Data;
    private ExpandableListView expandableListView;
    private MyBaseExpandableListAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buscx, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        expandableListView = view.findViewById(R.id.expand_list);
        Data = new ArrayList<>();
        //组数据准备
        buscxGroupList = new ArrayList<>();
        buscxGroupList.add(new buscxGroupBean("中医院站"));
        buscxGroupList.add(new buscxGroupBean("联想大厦站"));

        //中医院站组
        buscxItemList = new ArrayList<>();
        buscxItemList.add(new buscxItemBean(R.drawable.ic_smallbus, "1号(101人)"));
        buscxItemList.add(new buscxItemBean(R.drawable.ic_smallbus, "2号(101人)"));
        Data.add(buscxItemList);
        //联想大厦站组
        buscxItemList = new ArrayList<>();
        buscxItemList.add(new buscxItemBean(R.drawable.ic_smallbus, "1号(101人)"));
        buscxItemList.add(new buscxItemBean(R.drawable.ic_smallbus, "2号(101人)"));
        Data.add(buscxItemList);

        myAdapter = new MyBaseExpandableListAdapter(buscxGroupList, Data, getContext());
        expandableListView.setAdapter(myAdapter);
    }
}