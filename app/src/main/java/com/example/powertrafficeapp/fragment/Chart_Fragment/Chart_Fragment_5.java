package com.example.powertrafficeapp.fragment.Chart_Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.powertrafficeapp.R;
import com.example.powertrafficeapp.util.MyMarkerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by dell on 2017/08/01.
 */

public class Chart_Fragment_5 extends Fragment {

    private BarChart chart_f5;
    private Random random;
    private BarData data;
    private BarDataSet dataSet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chart_fragment_layout5, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        chart_f5 = (BarChart) getActivity().findViewById(R.id.chart_f5);
        barchart();

    }

    public void barchart() {
        final String yuefen[] = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
        final ArrayList<BarEntry> entries = new ArrayList<>();//显示条目
        final ArrayList<String> xVals = new ArrayList<String>();//横坐标标签
        random = new Random();//随机数
        for (int i = 0; i < 12; i++) {
            int profit = random.nextInt(100);
            entries.add(new BarEntry(i, profit));
            xVals.add((i + 1) + "月");
        }
        XAxis xAxis = chart_f5.getXAxis();
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return yuefen[(int) v];
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });
        chart_f5.getAxisRight().setEnabled(false);//关闭右边的Y轴，因为默认有两条，左边一条，右边一条，MPAndroidChart中有setEnabled方法的元素基本上都是使能的作用
        chart_f5.getAxisRight().setDrawAxisLine(false);
        dataSet = new BarDataSet(entries, "温度变化表");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);//颜色模板
        data = new BarData(dataSet);
        chart_f5.setData(data);
        chart_f5.animateY(3000);  //设置Y方向上动画animateY(int time);
        //图表描述
        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.textview);
        chart_f5.setMarker(mv);
        Description description = new Description();
        description.setText("温度变化表(单位:摄氏度)");
        chart_f5.setDescription(description);
        chart_f5.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, Highlight highlight) {
            }

            @Override
            public void onNothingSelected() {
            }
        });
    }
}
