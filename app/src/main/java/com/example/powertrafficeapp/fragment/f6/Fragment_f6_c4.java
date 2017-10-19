package com.example.powertrafficeapp.fragment.f6;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.powertrafficeapp.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by 赵洪斌 on 2017/10/16.
 */

public class Fragment_f6_c4 extends Fragment {
    Random random = new Random();
    LineDataSet lineDataSet;
    LineData lineData;
    Rongc4 rongc4 = new Rongc4();
    Handler handler = new Handler();
    ArrayList<Integer> intyy = new ArrayList<>();//横坐标标签
    ArrayList<String> xVals = new ArrayList<>();//横坐标标签
    ArrayList<Entry> entries = new ArrayList<>();//显示条目
    int ss = 0;
    private TextView textF6C4;
    private LineChart linec4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f6_c4, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textF6C4 = (TextView) getActivity().findViewById(R.id.text_f6_c4);
        linec4 = (LineChart) getActivity().findViewById(R.id.linec_4);
        handler.postDelayed(rongc4, 1000);
    }

    class Rongc4 implements Runnable {

        @Override
        public void run() {

            if (ss <= 20) {
            xVals.add(String.valueOf(ss * 3));
            random = new Random();//随机数
            int profit = random.nextInt(100);
            entries.add(new BarEntry(ss, profit));
            intyy.add(profit);
            XAxis xAxis = linec4.getXAxis();
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float v, AxisBase axisBase) {

                    return xVals.get((int) v % xVals.size());
                }

                @Override
                public int getDecimalDigits() {
                    return 0;
                }
            });
//                YAxis leftAxis = mChart.getAxisLeft();
//                leftAxis.setTextColor(Color.WHITE);
//
//                // 最大值
//                leftAxis.setAxisMaxValue(90f);
//
//                // 最小值
//                leftAxis.setAxisMinValue(40f);
//
//                // 不一定要从0开始
//                leftAxis.setStartAtZero(false);
            YAxis rightAxis = linec4.getAxisRight();
            rightAxis.setSpaceMin(0f);
            xAxis.setAxisLineWidth(3f);
            xAxis.setTextSize(10f);
            xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线  
            Legend legend = linec4.getLegend();
            legend.setEnabled(false);
            linec4.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            lineDataSet = new LineDataSet(entries, "公司年利润报表");
            lineDataSet.setColor(R.color.color6);
            lineData = new LineData(lineDataSet);
            linec4.setData(lineData);
            linec4.invalidate();
                linec4.notifyDataSetChanged();
                Description description = new Description();
            description.setText("公司前半年财务报表(单位：万元)");
            linec4.setDescription(description);
            Collections.sort(intyy);
            textF6C4.setText("粒度最大值" + String.valueOf(intyy.get(intyy.size() - 1)) + "     ");
                ss++;
                handler.postDelayed(rongc4, 1000);

            } else {
                handler.removeCallbacks(rongc4);
            }

        }
    }
}