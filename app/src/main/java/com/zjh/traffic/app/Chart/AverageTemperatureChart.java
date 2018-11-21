package com.zjh.traffic.app.Chart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AverageTemperatureChart extends AbstractDemoChart {
    public String getName() {
        return "Average temperature";
    }

    public String getDesc() {
        return "The average temperature in 4 Greek islands (line chart)";
    }

    public Intent execute(Context context) {
        return null;
    }

    public GraphicalView getChartView(Context context) {
        String[] titles = {"", ""};
        List<double[]> x = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            x.add(new double[]{1, 2, 3, 4, 5, 6});//每个序列中点的X坐标
        }
        List<double[]> y = new ArrayList<>();
        y.add(new double[]{14, 15, 16, 17, 16, 16});//序列1中点的y坐标
        y.add(new double[]{22, 24, 25, 25, 25, 22});//序列2中点的Y坐标
        int[] colors = new int[]{Color.BLUE, Color.RED};//每个序列的颜色设置
        PointStyle[] styles = new PointStyle[]{PointStyle.CIRCLE, PointStyle.CIRCLE};//每个序列中点的形状设置


        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);//调用AbstractDemoChart中的方法设置renderer
        renderer.setMargins(new int[]{40, 30, 30, 20});//设置外边距，顺序为：上左下右
        renderer.setShowGridX(true);
        renderer.setShowGridY(false);
        renderer.setMarginsColor(Color.parseColor("#f9f9f9"));
        renderer.setZoomButtonsVisible(false);//设置缩放按钮是否可见
        renderer.setZoomEnabled(true); //图表是否可以缩放设置
        renderer.setClickEnabled(true);
        renderer.setDisplayValues(true);//是否显示值
        renderer.setShowLegend(false); //显示底部说明
        renderer.setGridColor(Color.BLACK);//设置网格颜色
        // 是否支持图表移动
        renderer.setPanEnabled(false, false);
        renderer.setPointSize(12);

        String[] days = getTime();
        renderer.setXLabels(0);
        renderer.setLabelsTextSize(20);

        for (int i = 1; i <= days.length; i++)
            renderer.addTextLabel(i, days[i-1]);
        renderer.setXAxisMin(1);//设置X轴的最小值为0.5
       renderer.setXAxisMax(6);//设置X轴的最大值为5
        renderer.setYAxisMin(10);//设置Y轴的最小值为0
       renderer.setYAxisMax(30);//设置Y轴最大值为500

        for (int i = 0; i < renderer.getSeriesRendererCount(); i++)
            ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);//设置图上的点为实心
        renderer.setXLabelsAlign(Paint.Align.LEFT);//刻度线与刻度标注之间的相对位置关系
        renderer.setYLabelsAlign(Paint.Align.CENTER);//刻度线与刻度标注之间的相对位置关系
        GraphicalView view = ChartFactory.getLineChartView(context, buildDataset(titles, x, y), renderer);
        return view;
    }

    private String[] getTime() {
        String[] days = new String[6];
        Date date = new Date();
        if (date.getDay() == 0)
            days = new String[]{"昨天", "今天", "明天", "周二", "周三", "周四"};
        else if (date.getDay() == 1)
            days = new String[]{"昨天", "今天", "明天", "周三", "周四", "周五"};
        else if (date.getDay() == 2)
            days = new String[]{"昨天", "今天", "明天", "周四", "周五", "周六"};
        else if (date.getDay() == 3)
            days = new String[]{"昨天", "今天", "明天", "周五", "周六", "周日"};
        else if (date.getDay() == 4)
            days = new String[]{"昨天", "今天", "明天", "周六", "周日", "周一"};
        else if (date.getDay() == 5)
            days = new String[]{"昨天", "今天", "明天", "周日", "周一", "周二"};
        else if (date.getDay() == 6)
            days = new String[]{"昨天", "今天", "明天", "周一", "周二", "周三"};
        return days;
    }

}