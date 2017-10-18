package com.example.powertrafficeapp.fragment.Chart_Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.powertrafficeapp.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by dell on 2017/08/01.
 */

public class Chart_Fragment_3 extends Fragment {

    Random random;
    String week[] = {"昨天", "今天", "明天", "周五", "周六", "周日", "周一"};
    private PieChart mPieChart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chart_fragment_layout3, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPieChart = (PieChart) getActivity().findViewById(R.id.mPieChart);
        mPieChart.setCenterText("你好");
        // 触摸旋转
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(40, "优秀"));
        entries.add(new PieEntry(20, "满分"));
        entries.add(new PieEntry(30, "及格"));
        entries.add(new PieEntry(10, "不及格"));
        PieDataSet dataSet = new PieDataSet(entries, "三年级一班");
        dataSet.setColors(getColors());
        PieData data = new PieData(dataSet);
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mPieChart.setData(data);
        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        Legend l = mPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(10f);
        l.setYEntrySpace(10f);
        l.setYOffset(10f);
    }

    private int[] getColors() {
        int stacksize = 4;
        //有尽可能多的颜色每项堆栈值
        int[] colors = new int[stacksize];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = ColorTemplate.MATERIAL_COLORS[i];
        }
        return colors;
    }
}
