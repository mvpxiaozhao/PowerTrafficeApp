package com.example.powertrafficeapp.fragment.Chart_Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.powertrafficeapp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by dell on 2017/08/01.
 */

public class Chart_Fragment_4 extends Fragment {
    private BarChart mBarChart;
    private LineChart lineChart;

    private Random random;

    private BarData data;
    private LineData data1;
    private BarDataSet dataSet;
    private LineDataSet dataSet1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chart_fragment_layout4, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBarChart = (BarChart) getActivity().findViewById(R.id.bar2);
        mBarChart.getDescription().setEnabled(false);

        //设置最大的能够在图表上显示数字的图数
        mBarChart.setMaxVisibleValueCount(60);
        //设置背景是否网格显示
        mBarChart.setDrawGridBackground(false);

        //设置高亮显示
        mBarChart.setDrawValueAboveBar(false);
        mBarChart.setHighlightFullBarEnabled(false);


        // 改变y标签的位置
        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f);

        //消失右y轴的查看
        mBarChart.getAxisRight().setEnabled(false);
        XAxis xLabels = mBarChart.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.TOP);
        Legend l = mBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);
        setData();
    }

    private void setData() {
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < 30 + 1; i++) {
            float mult = (50 + 1);
            float val1 = (float) (Math.random() * mult) + mult / 3;
            float val2 = (float) (Math.random() * mult) + mult / 3;
            float val3 = (float) (Math.random() * mult) + mult / 3;
            yVals1.add(new BarEntry(i, new float[]{val1, val2, val3}));
        }
        BarDataSet set1;
        set1 = new BarDataSet(yVals1, "三年级一班期末考试");
        set1.setColors(getColors());
        set1.setStackLabels(new String[]{"及格", "优秀", "不及格"});
        BarData data = new BarData();
        data.addDataSet(set1);
        data.setValueTextColor(Color.WHITE);
        mBarChart.setData(data);
        mBarChart.setFitBars(true);
        mBarChart.invalidate();
    }

    private int[] getColors() {
        int stacksize = 3;
        //有尽可能多的颜色每项堆栈值
        int[] colors = new int[stacksize];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = ColorTemplate.MATERIAL_COLORS[i];
        }
        return colors;
    }
}
