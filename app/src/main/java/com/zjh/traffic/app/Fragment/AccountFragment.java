package com.zjh.traffic.app.Fragment;

import android.app.Activity;
import android.content.Intent;
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
    private String[] plate = {"鲁B10001", "鲁B10002", "鲁B10003", "鲁B10004"};
    private String[] name = {"王生安", "张顺谷", "张淮森", "王生安"};
    private String[] balance = new String[]{"0", "0", "0", "0"};
    private Boolean[] CompoundButton_isChecked;//记录复选按钮状态

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CompoundButton_isChecked = new Boolean[]{false, false, false, false};
        initView(view);
        upData(0);
    }

    private void initView(View view) {
        carList = view.findViewById(R.id.carlist);
        listData = new ArrayList<>();
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
                        if (getFragmentManager() != null) {
                            RechargeDialog rechargeDialog = new RechargeDialog(rechargeCarId, rechargePlate);
                            rechargeDialog.setTargetFragment(AccountFragment.this, 0);
                            rechargeDialog.show(getFragmentManager(), "Recharge");
                        }
                    }
                });
                //低于警告值更改背景颜色
                try {
                    if (Integer.parseInt(balance[holder.getItemPosition()]) < App.getAlerting())
                        holder.getItemView().setBackgroundColor(Color.parseColor("#ffcc00"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < CarId.length; i++)
            carListAdapter.add(new carListBean(CarId[i], im_car[i], plate[i], name[i], balance[i]));
        carList.setAdapter(carListAdapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 0:
                    upData(0);
                    break;
            }
        }
    }

    private void upData(final int i) {
        new GetCarAccountBalanceRequest().setParams(new Object[]{CarId[i], App.getUserName()}).
                sendRequest(new OnResponseListener() {
                    int finalI = i;

                    @Override
                    public void onResponse(Object result) {
                        try {
                            balance[finalI] = result.toString();
                            carListAdapter.clear();
                            for (int i = 0; i < CarId.length; i++)
                                carListAdapter.add(new carListBean(CarId[i], im_car[i], plate[i], name[i], balance[i]));
                            if (++finalI < CarId.length)
                                upData(finalI);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
