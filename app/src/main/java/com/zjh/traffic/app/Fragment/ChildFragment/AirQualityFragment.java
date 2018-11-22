package com.zjh.traffic.app.Fragment.ChildFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.zjh.traffic.R;
import com.zjh.traffic.app.Chart.BarChartManager;

/**
 * 空气质量柱形图
 */
public class AirQualityFragment extends Fragment {
    private BarChart AirQualityBarChart;
    private BarData barData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_airquality, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        AirQualityBarChart = view.findViewById(R.id.AirQualityBarChart);
        //设置图表的描述
        String Description = "过去1分钟空气质量最差值";
        //设置x轴的数据
        String[] numX = {"03", "06", "09", "12", "15", "18", "21", "24", "27", "30", "33", "36", "39", "42", "45", "48", "51", "54", "57", "60"};
        //设置y轴的数据
        float[] datas = {82, 92, 95, 103, 103, 100, 108, 100, 90, 85, 89, 95, 93, 80, 102, 83, 101, 102, 79, 80};//数据
        //设置名称
        BarChartManager.setName("");
        //创建两条折线的图表
        barData = BarChartManager.initBarChartManager(getContext(), AirQualityBarChart, numX, datas);
        BarChartManager.initDataStyle(AirQualityBarChart, barData, Description, getContext());
    }
}
