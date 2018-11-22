package com.zjh.traffic.app.Chart;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class BarChartManager {

    private static String name = null;

    public static BarData initBarChartManager(Context context, BarChart barChart, String[] count, float[] datas) {
        List<String> xValues = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues.add(count[i]);
        }

        // y轴的数据
        List<BarEntry> yValues = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            yValues.add(new BarEntry(datas[i], i));
        }
        //设置样式
        BarDataSet dataSet = new BarDataSet(yValues, name);
        //用y轴的集合来设置参数
        dataSet.setColor(Color.GRAY);
        dataSet.setHighlightEnabled(true);
        dataSet.setDrawValues(false);
        dataSet.setBarSpacePercent(60f);
        dataSet.setValueTextSize(8f);     //数值显示的大小
        ArrayList<BarDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);
        BarData barData = new BarData(xValues, dataSets);
        return barData;
    }

    public static void initDataStyle(final BarChart barChart, BarData barData, final String Description, Context context) {
        //设置图表的描述
        barChart.setDescription(Description);
        barChart.setDescriptionPosition(1600f, 25f);
        //字的颜色
        barChart.setDescriptionColor(Color.BLACK);
        //字号
        barChart.setDescriptionTextSize(8f);
        barChart.setBackgroundColor(Color.parseColor("#f9f9f9")); //设置背景颜色
        //是否能否拖拽
        barChart.setDragEnabled(false);
        //是否能够缩放
        barChart.setPinchZoom(false);
        //允许X轴缩放
        barChart.setScaleXEnabled(false);
        //允许Y轴缩放
        barChart.setScaleYEnabled(false);
        //设置显示的动画,BarChart的动画有很多种 animateX，animateY，animateXY等
        barChart.animateXY(3000, 3000);
        //设置一个值，当表中的显示的树状图个数超过了设置的值，那么隐藏树状图上的值
        barChart.setMaxVisibleValueCount(90);
        barChart.getAxisLeft().setEnabled(true);
        barChart.getAxisRight().setEnabled(false);
        //设置比例图标的显示隐藏
        Legend legend = barChart.getLegend();
        legend.setEnabled(false);
        //取到X轴的操作对象
        XAxis xAxis = barChart.getXAxis();
        //是否显示X轴的线(与X轴垂直的线),默认为true
        xAxis.setDrawGridLines(false);
        //设置XAxis坐标的字在哪里显示 XAxisPosition{ TOP, BOTTOM, BOTH_SIDED, TOP_INSIDE, BOTTOM_INSIDE}
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelsToSkip(0);
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                barChart.setDescription(Description + ":"+entry.getVal());
            }

            @Override
            public void onNothingSelected() {

            }
        });
        barChart.setData(barData);
    }

    public static void setName(String name) {
        BarChartManager.name = name;
    }
}
