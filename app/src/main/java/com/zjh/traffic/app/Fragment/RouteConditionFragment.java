package com.zjh.traffic.app.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
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
import com.zjh.traffic.app.Request.GetRoadStatusRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 路况查询
 */
public class RouteConditionFragment extends Fragment implements View.OnClickListener {
    private TextView time_tv, temperature_tv, humidity_tv, pm_tv;
    private ImageButton btn_refresh;
    private TextView road1, road2, road3, road4, road5_0, road5_1, road5_2, road6, road7;
    private TimerTask task = null;
    private Timer timer = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_routecondition, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initTask();
        if (timer == null) {
            timer = new Timer();
        } else {
            timer.cancel();
            timer = new Timer();
        }
        timer.schedule(task, 0, 3 * 1000);
    }

    private void initTask() {
        task = new TimerTask() {
            @Override
            public void run() {

                for (int i = 1; i <= 7; i++) {
                    if (i != 1) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    final int finalI = i;
                    new GetRoadStatusRequest().setParams(new Object[]{i, App.getUserName()})
                            .sendRequest(new OnResponseListener() {
                                @Override
                                public void onResponse(Object result) {
                                    try {
                                        changeColor(finalI, Integer.parseInt(result.toString()));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                }
            }
        };
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

        road1 = view.findViewById(R.id.road1);
        road2 = view.findViewById(R.id.road2);
        road3 = view.findViewById(R.id.road3);
        road4 = view.findViewById(R.id.road4);
        road5_0 = view.findViewById(R.id.road5_0);
        road5_1 = view.findViewById(R.id.road5_1);
        road5_2 = view.findViewById(R.id.road5_2);
        road6 = view.findViewById(R.id.road6);
        road7 = view.findViewById(R.id.road7);
        road1.setBackgroundColor(Color.parseColor("#6ab82e"));
        road1.setBackgroundColor(Color.parseColor("#6ab82e"));
        road2.setBackgroundColor(Color.parseColor("#6ab82e"));
        road3.setBackgroundColor(Color.parseColor("#6ab82e"));
        road4.setBackgroundColor(Color.parseColor("#6ab82e"));
        road5_0.setBackgroundColor(Color.parseColor("#6ab82e"));
        road5_1.setBackgroundColor(Color.parseColor("#6ab82e"));
        road5_2.setBackgroundColor(Color.parseColor("#6ab82e"));
        road6.setBackgroundColor(Color.parseColor("#6ab82e"));
        road7.setBackgroundColor(Color.parseColor("#6ab82e"));
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

    @SuppressLint("Range")
    private void changeColor(int roadID, int color) {
        String str_color = "";
        switch (color) {
            case 1:
                str_color = "#6ab82e";
                break;
            case 2:
                str_color = "#ece93a";
                break;
            case 3:
                str_color = "#f49b25";
                break;
            case 4:
                str_color = "#e33532";
                break;
            case 5:
                str_color = "#b01e23";
                break;
        }
        switch (roadID) {
            case 1:
                road1.setBackgroundColor(Color.parseColor(str_color));
                break;
            case 2:
                road2.setBackgroundColor(Color.parseColor(str_color));
                break;
            case 3:
                road3.setBackgroundColor(Color.parseColor(str_color));
                break;
            case 4:
                road4.setBackgroundColor(Color.parseColor(str_color));
                break;
            case 5:
                road5_0.setBackgroundColor(Color.parseColor(str_color));
                road5_1.setBackgroundColor(Color.parseColor(str_color));
                road5_2.setBackgroundColor(Color.parseColor(str_color));
                break;
            case 6:
                road6.setBackgroundColor(Color.parseColor(str_color));
                break;
            case 7:
                road7.setBackgroundColor(Color.parseColor(str_color));
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
