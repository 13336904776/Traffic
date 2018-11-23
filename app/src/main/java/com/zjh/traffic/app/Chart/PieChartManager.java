package com.zjh.traffic.app.Chart;



import android.graphics.Color;
import android.graphics.Typeface;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.List;

public class PieChartManager {

    public PieChart pieChart;

    public PieChartManager(PieChart pieChart) {
        this.pieChart = pieChart;
        initPieChart();
    }

    //初始化
    private void initPieChart() {
        //  是否显示中间的洞
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleRadius(40f);//设置中间洞的大小
        // 半透明圈
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setTransparentCircleColor(Color.WHITE); //设置半透明圆圈的颜色
        pieChart.setTransparentCircleAlpha(125); //设置半透明圆圈的透明度

        //饼状图中间可以添加文字
        pieChart.setDrawCenterText(false);
        pieChart.setCenterText(""); //设置中间文字
        pieChart.setCenterTextColor(Color.parseColor("#a1a1a1")); //中间问题的颜色
        pieChart.setCenterTextSizePixels(36);  //中间文字的大小px
        pieChart.setCenterTextRadiusPercent(1f);
        pieChart.setCenterTextTypeface(Typeface.DEFAULT); //中间文字的样式

        pieChart.setRotationAngle(0);// 初始旋转角度
        pieChart.setRotationEnabled(true);// 可以手动旋转
        pieChart.setUsePercentValues(true);//显示成百分比
        pieChart.setDescriptionPosition(1100f,30f);
        pieChart.setDescriptionColor(Color.BLACK);
        pieChart.setDescriptionTextSize(14);

        //图相对于上下左右的偏移
        pieChart.setExtraOffsets(20, 20, 20, 20);
        //图标的背景色
        pieChart.setBackgroundColor(Color.TRANSPARENT);
//        设置pieChart图表转动阻力摩擦系数[0,1]
        pieChart.setDragDecelerationFrictionCoef(0.75f);
        //获取图例
        Legend legend = pieChart.getLegend();
        legend.setTextColor(Color.BLACK); //图例文字的颜色
        legend.setTextSize(12);  //图例文字的大小
        legend.setEnabled(true);                    //是否启用图列（true：下面属性才有意义
        legend.setFormSize(10);                      //设置图例的大小
        legend.setFormToTextSpace(5f);              //设置每个图例实体中标签和形状之间的间距
        legend.setWordWrapEnabled(true);              //设置图列换行(注意使用影响性能,仅适用legend位于图表下面)
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        legend.setForm(Legend.LegendForm.SQUARE);//设置比例块形状，默认为方块
//        mLegend.setEnabled(false);//设置禁用比例块

        pieChart.animateY(1500, Easing.EasingOption.EaseInOutQuad);// 设置pieChart图表展示动画效果
    }


    /**
     * 显示实心圆
     *
     * @param yvals
     * @param colors
     */
    public void showSolidPieChart(List<String> xVals, List<Entry> yvals, List<Integer> colors) {
        //数据集合
        PieDataSet dataset = new PieDataSet(yvals, "");
        //填充每个区域的颜色
        dataset.setColors(colors);
        //是否在图上显示数值
        dataset.setDrawValues(true);
        //文字的大小
        dataset.setValueTextSize(10);
        //文字的颜色
        dataset.setValueTextColor(Color.BLACK);
        //文字的样式
        dataset.setValueTypeface(Typeface.DEFAULT);
        //设置每条之前的间隙
        dataset.setSliceSpace(5f);           //设置饼状Item之间的间隙
        //设置饼状Item被选中时变化的距离
//        dataset.setSelectionShift(10f);
//
//        dataset.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData pieData = new PieData(xVals, dataset);
        //格式化显示的数据为%百分比
        pieData.setValueFormatter(new PercentFormatter());
        //显示视图
        pieChart.setData(pieData);
    }


    /**
     * 显示圆环
     *
     * @param yvals
     * @param colors
     */
    public void showRingPieChart(List<String> xVals, List<Entry> yvals, List<Integer> colors) {
        //显示为圆环
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(85f);//设置中间洞的大小

        //数据集合
        PieDataSet dataset = new PieDataSet(yvals, "");
        //填充每个区域的颜色
        dataset.setColors(colors);
        //是否在图上显示数值
        dataset.setDrawValues(false);
//        文字的大小
        dataset.setValueTextSize(12);
//        文字的颜色
        dataset.setValueTextColor(Color.BLACK);
//        文字的样式
        dataset.setValueTypeface(Typeface.DEFAULT_BOLD);

//        设置每条之前的间隙
        dataset.setSliceSpace(0);

        //设置饼状Item被选中时变化的距离
        dataset.setSelectionShift(0f);
        //填充数据
        PieData pieData = new PieData(xVals, dataset);
//        格式化显示的数据为%百分比
        pieData.setValueFormatter(new PercentFormatter());
//        显示视图
        pieChart.setData(pieData);

    }


}