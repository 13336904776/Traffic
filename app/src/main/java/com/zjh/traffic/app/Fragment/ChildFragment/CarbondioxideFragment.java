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
 * 二氧化碳折线图
 */
public class CarbondioxideFragment extends Fragment {
    private LineChart CarbondioxideLineChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_carbondioxide, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        CarbondioxideLineChart = view.findViewById(R.id.CarbondioxideLineChart);
        //设置图表的描述
        CarbondioxideLineChart.setDescription("过去1分钟最大相对浓度:97");
        //设置x轴的数据
        String[] numX = {"03", "06", "09", "12", "15", "18", "21", "24", "27", "30", "33", "36", "39", "42", "45", "48", "51", "54", "57", "60"};
        //设置y轴的数据
        float[] datas = {97, 95, 97, 30, 45, 40, 70, 10, 15, 30, 55, 90, 60, 5, 28, 70, 65, 90, 10, 75};//数据
        LineData lineData;
        //设置名称
        LineChartManager.setLineName("");
        lineData = LineChartManager.initSingleLineChart(getContext(), CarbondioxideLineChart, numX, datas);
        LineChartManager.initDataStyle2(CarbondioxideLineChart, lineData, getContext());
    }
}
