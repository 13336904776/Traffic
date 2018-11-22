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
 * 相对湿度折线图
 */
public class HumidityFragment extends Fragment {
    private LineChart HumidityLineChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_humidity, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        HumidityLineChart = view.findViewById(R.id.HumidityLineChart);
        //设置图表的描述
        HumidityLineChart.setDescription("过去1分钟最大相对湿度:67％");
        //设置x轴的数据
        String[] numX = {"03", "06", "09", "12", "15", "18", "21", "24", "27", "30", "33", "36", "39", "42", "45", "48", "51", "54", "57", "60"};
        //设置y轴的数据
        float[] datas = {59, 58, 59, 60, 58, 56, 48, 64, 67, 60, 55, 48, 42, 40, 36, 36, 31, 32, 31, 36};//数据
        LineData lineData;
        //设置名称
        LineChartManager.setLineName("");
        lineData = LineChartManager.initSingleLineChart(getContext(), HumidityLineChart, numX, datas);
        LineChartManager.initDataStyle2(HumidityLineChart, lineData, getContext());
    }
}
