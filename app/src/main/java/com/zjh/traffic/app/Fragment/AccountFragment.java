package com.zjh.traffic.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import com.zjh.traffic.R;
import com.zjh.traffic.app.Adapter.MyBaseAdapter;
import com.zjh.traffic.app.Application.App;
import com.zjh.traffic.app.Bean.carListBean;
import com.zjh.traffic.app.Callback.OnResponseListener;
import com.zjh.traffic.app.Dialog.RechargeDialog;
import com.zjh.traffic.app.Request.GetCarAccountBalanceRequest;

import java.util.ArrayList;
import java.util.List;


public class AccountFragment extends Fragment {

    private ListView carList;
    private MyBaseAdapter<carListBean> carListAdapter;
    private List<carListBean> listData;
    private int[] CarId = {1, 2, 3, 4};
    private int[] im_car = {R.drawable.car_bmw, R.drawable.car_zh, R.drawable.car_bc, R.drawable.car_mazda};
    private String[] plate = {"辽A10001", "辽A10002", "辽A10003", "辽A10004"};
    private String[] name = {"张三", "李四", "王五", "赵六"};
    private String[] balance = {"(查询中)", "(查询中)", "(查询中)", "(查询中)"};
    private Boolean[] CompoundButton_isChecked = {false, false, false, false};//记录复选按钮状态

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, null);
        initView(view);
        upData();
        return view;
    }


    private void initView(View view) {
        carList = view.findViewById(R.id.carlist);
        listData = new ArrayList<>();
        for (int i = 0; i < CarId.length; i++)
            listData.add(new carListBean(CarId[i], im_car[i], plate[i], name[i], balance[i]));
        carListAdapter = new MyBaseAdapter<carListBean>(listData, R.layout.fragment_account_carlist) {

            @Override
            public void bindView(final ViewHolder holder, carListBean obj) {
                holder.setText(R.id.CarId, obj.getNumber() + "");
                holder.setImageResource(R.id.im_car, obj.getIm_car());
                holder.setText(R.id.plate, obj.getPlate());
                holder.setText(R.id.name, obj.getName());
                holder.setText(R.id.balance, obj.getBalance());
                holder.setOnCheckedChangeListener(R.id.is_recharge, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        CompoundButton_isChecked[holder.getItemPosition()] = isChecked;
                    }
                });
                holder.setOnClickListener(R.id.btn_recharge, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<Integer> rechargeCarId = new ArrayList<>();
                        List<String> rechargePlate = new ArrayList<>();
                        for (int i = 0; i < CarId.length; i++)
                            if (CompoundButton_isChecked[i]) {
                                rechargeCarId.add(CarId[i]);
                                rechargePlate.add(plate[i]);
                            }
                        new RechargeDialog(rechargeCarId, rechargePlate).show(getFragmentManager(), "Recharge");
                    }
                });
                //低于警告值更改背景颜色
                try {
                    if (Integer.parseInt(balance[holder.getItemPosition()]) < App.getAlerting())
                        holder.getItemView().setBackgroundColor(Color.parseColor("#ffcc00"));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        };
        carList.setAdapter(carListAdapter);
    }

    private void upData() {
        for (int k = 0; k < CarId.length; k++) {
            final int finalK = k;
            new GetCarAccountBalanceRequest().setParams(new Object[]{CarId[k], App.getUserName()}).sendRequest(new OnResponseListener() {
                @Override
                public void onResponse(Object result) {
                    balance[finalK] = result.toString();
                    carListAdapter.clear();
                    for (int i = 0; i < CarId.length; i++) {
                        carListAdapter.add(new carListBean(CarId[i], im_car[i], plate[i], name[i], balance[i]));
                    }
                    carListAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}
