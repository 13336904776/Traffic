package com.zjh.traffic.app.Dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import com.zjh.traffic.R;
import com.zjh.traffic.app.Application.App;
import com.zjh.traffic.app.Callback.OnResponseListener;
import com.zjh.traffic.app.Request.SetTrafficLightConfigRequest;

import java.util.List;

public class LightSettingsDialog extends DialogFragment implements View.OnClickListener {
    private List<Integer> TrafficLightId;
    private EditText red_ed, yellow_ed, green_ed;
    private Button btn_OK, btn_NO;

    private String Tag;

    public LightSettingsDialog() {
    }

    @SuppressLint("ValidFragment")
    public LightSettingsDialog(List<Integer> trafficLightId) {
        TrafficLightId = trafficLightId;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Tag = getTag();
        return inflater.inflate(R.layout.dialog_lightsettings, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        red_ed = view.findViewById(R.id.red_ed);
        yellow_ed = view.findViewById(R.id.yellow_ed);
        green_ed = view.findViewById(R.id.green_ed);
        btn_OK = view.findViewById(R.id.btn_OK);
        btn_NO = view.findViewById(R.id.btn_NO);
        btn_OK.setOnClickListener(this);
        btn_NO.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_OK) {
            try {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < TrafficLightId.size(); i++) {
                            if (i != 0) {
                                try {
                                    Thread.sleep(400);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            final int finalI = i;
                            new SetTrafficLightConfigRequest().setParams(new Object[]{TrafficLightId.get(i),
                                    red_ed.getText(), green_ed.getText(), yellow_ed.getText(), App.getUserName()})
                                    .sendRequest(new OnResponseListener() {
                                        @Override
                                        public void onResponse(Object result) {
                                            if (finalI == TrafficLightId.size() - 1) {
                                                dismiss();
                                                //通知AccountFragment更新数据
                                                if (Tag.equals("LightSettings"))
                                                    getTargetFragment().onActivityResult(getTargetRequestCode(),
                                                            Activity.RESULT_OK, new Intent());
                                            }
                                        }
                                    });
                        }
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else
            dismiss();

    }
}
