package com.zjh.traffic.app.Dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
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
import com.zjh.traffic.app.Fragment.AccountFragment;
import com.zjh.traffic.app.Request.SetCarAccountRechargeRequest;

import java.util.List;

public class RechargeDialog extends DialogFragment implements View.OnClickListener {
    private List<Integer> rechargeCarId;//充值小车ID
    private List<String> rechargePlate;//充值小车车牌号

    private TextView rechargePlate_tv;
    private EditText rechargeMoney_ed;
    private Button btn_recharge, btn_cancel;

    private Boolean[] isRequest;//存储请求返回结果

    public RechargeDialog() {
    }

    @SuppressLint("ValidFragment")
    public RechargeDialog(List<Integer> rechargeCarId, List<String> rechargePlate) {
        this.rechargeCarId = rechargeCarId;
        this.rechargePlate = rechargePlate;
        this.isRequest = new Boolean[rechargeCarId.size()];
    }

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_recharge, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
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
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle("充值");
                progressDialog.setMessage("充值中");
                progressDialog.setCancelable(false);
                progressDialog.show();
                for (int i = 0; i < rechargeCarId.size(); i++) {
                    final int finalI = i;
                    new SetCarAccountRechargeRequest().setParams(new Object[]{rechargeCarId.get(i),
                            rechargeMoney_ed.getText(), App.getUserName()}).sendRequest(new OnResponseListener() {
                        @Override
                        public void onResponse(Object result) {
                            isRequest[finalI] = ((Boolean) result);
                            Boolean isRequestAll = true;//判断是否已全部请求完成
                            for (int i = 0; i < isRequest.length; i++)
                                if (isRequest == null)
                                    isRequestAll = false;
                            if (isRequestAll) {
                                try {
                                    String out = "";
                                    for (int i = 0; i < isRequest.length; i++)
                                        if (isRequest[i])
                                            out += rechargePlate.get(i) + "充值" + rechargeMoney_ed.getText() + "元成功\n";
                                        else
                                            out += rechargePlate.get(i) + "充值" + rechargeMoney_ed.getText() + "元失败\n";
                                    App.showAlertDialog(getContext(), "充值结果", out);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                progressDialog.dismiss();
                                RechargeDialog.this.dismiss();
                                //通知AccountFragment更新数据
                                getTargetFragment().onActivityResult(getTargetRequestCode(),
                                        Activity.RESULT_OK, new Intent());

                            }
                        }
                    });
                }
                break;
            case R.id.btn_cancel:
                RechargeDialog.this.dismiss();
                break;
        }
    }
}
