package com.zjh.traffic.app.Dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zjh.traffic.R;
import com.zjh.traffic.app.Application.App;
import com.zjh.traffic.app.Callback.OnResponseListener;
import com.zjh.traffic.app.Request.SetCarAccountRechargeRequest;
import com.zjh.traffic.app.SQLite.DBManager;
import com.zjh.traffic.app.Bean.RechargeRecordBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RechargeDialog extends DialogFragment implements View.OnClickListener {
    private List<Integer> rechargeCarId;//充值小车ID
    private List<String> rechargePlate;//充值小车车牌号

    private TextView rechargePlate_tv;
    private EditText rechargeMoney_ed;
    private Button btn_recharge, btn_cancel;

    private Boolean[] isRequest;//存储请求返回结果

    private ProgressDialog progressDialog;//充值过程框

    private String Tag;

    private DBManager dbManager;

    public RechargeDialog() {
    }

    @SuppressLint("ValidFragment")
    public RechargeDialog(Context context, List<Integer> rechargeCarId, List<String> rechargePlate) {
        if (rechargeCarId != null && rechargePlate != null) {
            this.rechargeCarId = rechargeCarId;
            this.rechargePlate = rechargePlate;
            this.isRequest = new Boolean[rechargeCarId.size()];
        } else {
            this.rechargeCarId = new ArrayList<>();
            this.rechargePlate = new ArrayList<>();
            this.isRequest = new Boolean[0];
        }
        dbManager = new DBManager(context);
    }

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Tag = getTag();
        return inflater.inflate(R.layout.dialog_recharge, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onStop() {
        super.onStop();
        dbManager.closeDB();
    }

    private void initView(View view) {
        rechargePlate_tv = view.findViewById(R.id.rechargePlate_tv);
        String out = "";
        for (int i = 0; i < rechargePlate.size(); i++)
            out += rechargePlate.get(i) + " ";
        rechargePlate_tv.setText(out);
        rechargeMoney_ed = view.findViewById(R.id.rechargeMoney_ed);
        btn_recharge = view.findViewById(R.id.btn_recharge);
        btn_cancel = view.findViewById(R.id.btn_cancel);
        btn_recharge.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_recharge:
                if (rechargeCarId.size() == 0)
                    App.showAlertDialog(getContext(), "提醒", "请选择充值车辆", null);
                else if (!rechargeMoney_ed.getText().toString().equals("")) {
                    progressDialog = new ProgressDialog(getContext());
                    progressDialog.setTitle("充值");
                    progressDialog.setMessage("充值中");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    upData(0);
                } else
                    App.showAlertDialog(getContext(), "提醒", "请输入充值金额", null);
                break;
            case R.id.btn_cancel:
                RechargeDialog.this.dismiss();
                break;
        }
    }

    private void upData(final int i) {
        new SetCarAccountRechargeRequest().setParams(new Object[]{rechargeCarId.get(i),
                rechargeMoney_ed.getText(), App.getUserName()}).
                sendRequest(new OnResponseListener() {
                    int finalI = i;

                    @SuppressLint("SimpleDateFormat")
                    @Override
                    public void onResponse(Object result) {
                        try {
                            isRequest[finalI] = (Boolean) result;
                            if (finalI < rechargeCarId.size() - 1 && isRequest[++finalI] == null)
                                upData(finalI);
                            else {
                                ArrayList<RechargeRecordBean> list = new ArrayList<>();
                                for (int i = 0; i < rechargePlate.size(); i++) {
                                    list.add(new RechargeRecordBean(App.getUserName(), rechargePlate.get(i),
                                            Integer.parseInt(rechargeMoney_ed.getText().toString()),
                                            new SimpleDateFormat("yyyy.MM.dd HH:mm")
                                                    .format(new Date())));
                                }
                                dbManager.add(list);
                                progressDialog.dismiss();
                                RechargeDialog.this.dismiss();
                                String out = "";
                                for (int i = 0; i < isRequest.length; i++)
                                    if (isRequest[i])
                                        out += rechargePlate.get(i) + "充值" + rechargeMoney_ed.getText() + "元成功\n";
                                    else
                                        out += rechargePlate.get(i) + "充值" + rechargeMoney_ed.getText() + "元失败\n";
                                App.showAlertDialog(getContext(), "充值结果", out, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //通知AccountFragment更新数据
                                        if (Tag.equals("Recharge"))
                                            getTargetFragment().onActivityResult(getTargetRequestCode(),
                                                    Activity.RESULT_OK, new Intent());
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
