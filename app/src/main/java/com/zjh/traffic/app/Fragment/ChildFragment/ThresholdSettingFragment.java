package com.zjh.traffic.app.Fragment.ChildFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zjh.traffic.R;
import com.zjh.traffic.app.Application.App;

public class ThresholdSettingFragment extends Fragment {
    private TextView threshold_tv;
    private EditText threshold_ed;
    private Button btn_thresholdsetting;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thresholdsetting, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @SuppressLint("SetTextI18n")
    private void initView(View view) {
        threshold_tv = view.findViewById(R.id.threshold_tv);
        threshold_ed = view.findViewById(R.id.threshold_ed);
        btn_thresholdsetting = view.findViewById(R.id.btn_thresholdsetting);
        threshold_tv.setText(App.getAlerting() + "");
        btn_thresholdsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.setAlerting(Integer.parseInt(threshold_ed.getText().toString()));
                App.showAlertDialog(getContext(), "提醒", "设置成功", null);
                threshold_tv.setText(App.getAlerting() + "");
            }
        });
    }
}