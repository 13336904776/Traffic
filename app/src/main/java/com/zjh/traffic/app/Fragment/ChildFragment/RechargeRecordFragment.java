package com.zjh.traffic.app.Fragment.ChildFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zjh.traffic.R;
import com.zjh.traffic.app.Adapter.MyBaseAdapter;
import com.zjh.traffic.app.Application.App;
import com.zjh.traffic.app.SQLite.DBManager;
import com.zjh.traffic.app.Bean.RechargeRecordBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 充值记录
 */
public class RechargeRecordFragment extends Fragment {
    private TextView userName, total;
    private MyBaseAdapter<RechargeRecordBean> myBaseAdapter;
    private ListView listView;
    private List<RechargeRecordBean> Data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_getusercardused, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        upData();
    }

    private void initView(View view) {
        userName = view.findViewById(R.id.userName);
        userName.setText(App.getUserName());
        total = view.findViewById(R.id.total);
        listView = view.findViewById(R.id.listView);
        Data = new ArrayList<>();
        myBaseAdapter = new MyBaseAdapter<RechargeRecordBean>(Data, R.layout.item_rechargerecord) {
            @Override
            public void bindView(ViewHolder holder, RechargeRecordBean obj) {
                holder.setText(R.id.rechargeUserName, "充值人:" + obj.getUserName());
                holder.setText(R.id.rechargePlate, "车牌号:" + obj.getRechargePlate());
                holder.setText(R.id.rechargeMoney, obj.getRechargeMoney() + "");
                holder.setText(R.id.rechargeTime, obj.getStringrechargeTime());
            }
        };
        listView.setAdapter(myBaseAdapter);
    }

    @SuppressLint("SetTextI18n")
    private void upData() {
        List<RechargeRecordBean> list = new DBManager(getContext()).query();
        int totalMoney = 0;
        for (int i = 0; i < list.size(); i++) {
            myBaseAdapter.add(list.get(i));
            totalMoney += list.get(i).getRechargeMoney();
        }
        total.setText("总支出" + totalMoney);
    }
}