package com.zjh.traffic.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zjh.traffic.R;
import com.zjh.traffic.app.Application.App;
import com.zjh.traffic.app.Callback.OnResponseListener;
import com.zjh.traffic.app.Request.GetAllSenseRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 路况查询
 */
public class RouteConditionFragment extends Fragment implements View.OnClickListener {
    private TextView time_tv, temperature_tv, humidity_tv, pm_tv;
    private ImageButton btn_refresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_routecondition, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        time_tv = view.findViewById(R.id.time_tv);
        temperature_tv = view.findViewById(R.id.temperature_tv);
        humidity_tv = view.findViewById(R.id.humidity_tv);
        pm_tv = view.findViewById(R.id.pm_tv);
        btn_refresh = view.findViewById(R.id.btn_refresh);

        time_tv.setText(getTime());
        upData();
        btn_refresh.setOnClickListener(this);
    }

    private String getTime() {
        Calendar calendar = Calendar.getInstance();
        String time = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" +
                calendar.get(Calendar.DAY_OF_MONTH) + "    ";
        Date date = new Date();
        if (date.getDay() == 0)
            time += "星期天";
        else if (date.getDay() == 1)
            time += "星期一";
        else if (date.getDay() == 2)
            time += "星期二";
        else if (date.getDay() == 3)
            time += "星期三";
        else if (date.getDay() == 4)
            time += "星期四";
        else if (date.getDay() == 5)
            time += "星期五";
        else if (date.getDay() == 6)
            time += "星期六";
        return time;
    }

    @Override
    public void onClick(View v) {
        time_tv.setText(getTime());
        upData();
    }

    private void upData() {
        new GetAllSenseRequest().setParams(new Object[]{App.getUserName()})
                .sendRequest(new OnResponseListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Object result) {
                        try {
                            List<Integer> list = (List<Integer>) result;
                            temperature_tv.setText("温度：" + list.get(0) + "℃");
                            humidity_tv.setText("相对湿度：" + list.get(1) + "％");
                            pm_tv.setText("PM2.5：" + list.get(2) + "μg/m3");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
