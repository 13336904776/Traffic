package com.zjh.traffic.app.Fragment.ChildFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.zjh.traffic.R;
import com.zjh.traffic.app.Chart.LineChartManager;

/**
 * 温度折线图
 */
public class TemperatureFragment extends Fragment {
    private LineChart TemperatureBarChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_temperature, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        TemperatureBarChart = view.findViewById(R.id.TemperatureLineChart);
        //设置图表的描述
        TemperatureBarChart.setDescription("过去1分钟最高气温:25℃,最低气温:16℃");
        //设置x轴的数据
        String[] numX = {"03", "06", "09", "12", "15", "18", "21", "24", "27", "30", "33", "36", "39", "42", "45", "48", "51", "54", "57", "60"};
        //设置y轴的数据
        float[] datas = {19, 19, 18, 18, 18, 18, 17, 16, 17, 19, 21, 21, 23, 23, 24, 24, 25, 25, 25, 25};//数据
        LineData lineData;
        //设置名称
        LineChartManager.setLineName("");
        lineData = LineChartManager.initSingleLineChart(getContext(), TemperatureBarChart, numX, datas);
        LineChartManager.initDataStyle2(TemperatureBarChart, lineData, getContext());

    }
}
