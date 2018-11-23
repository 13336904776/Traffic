package com.zjh.traffic.app.Fragment.ChildFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.zjh.traffic.R;
import com.zjh.traffic.app.Chart.PieChartManager;

import java.util.ArrayList;
import java.util.List;

public class DataAnalysisItemFragment2 extends Fragment {
    private PieChart DataAnalysisPieChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dataanalysisitem2, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        DataAnalysisPieChart = view.findViewById(R.id.DataAnalysisPieChart);
        showhodlePieChart();
    }

    private void showhodlePieChart() {
        DataAnalysisPieChart.setDescription("有无\"违章记录车辆\"的占比统计");
        List<String> xVals = new ArrayList<>();
        xVals.add("有重复违章");
        xVals.add("无重复违章");
        // 设置每份所占数量
        List<Entry> yvals = new ArrayList<>();
        yvals.add(new Entry(13.8F, 2));
        yvals.add(new Entry(86.1F, 2));
        //设置每份的颜色
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#FFA84543"));
        colors.add(Color.parseColor("#FF4472A5"));

        PieChartManager pieChartManagger = new PieChartManager(DataAnalysisPieChart);
        pieChartManagger.showSolidPieChart(xVals, yvals, colors);
    }
}