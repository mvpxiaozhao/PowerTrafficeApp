package com.example.powertrafficeapp.fragment.f6;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.powertrafficeapp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by 赵洪斌 on 2017/10/16.
 */

public class Fragment_f6_c1 extends Fragment {
    Random random = new Random();
    BarDataSet barDataSet;
    Handler handler = new Handler();
    BarData data;
    Rong rong = new Rong();
    ArrayList<Integer> intyy = new ArrayList<>();//横坐标标签
    ArrayList<String> xVals = new ArrayList<>();//横坐标标签
    ArrayList<BarEntry> entries = new ArrayList<>();//显示条目
    int ss = 0;
    private BarChart barchart1;
    private TextView textF6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("asasdasd", "onCreateView");
        View view = inflater.inflate(R.layout.f6_c1, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i("asasdasd", "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        textF6 = (TextView) getActivity().findViewById(R.id.text_f6);
        barchart1 = (BarChart) getActivity().findViewById(R.id.barchart_1);
        handler.postDelayed(rong, 1000);
    }

    @Override
    public void onAttach(Context context) {
        Log.i("asasdasd", "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("asasdasd", "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.i("asasdasd", "onStart");
        super.onStart();
    }

    @Override
    public void onPause() {
        Log.i("asasdasd", "onPause");
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.i("asasdasd", "onResume");
        super.onResume();
    }

    @Override
    public void onStop() {
        Log.i("asasdasd", "onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.i("asasdasd", "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.i("asasdasd", "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.i("asasdasd", "onDetach");
        super.onDetach();
    }
    class Rong implements Runnable {
        @Override
        public void run() {
            if (ss <= 20) {
            xVals.add(String.valueOf(ss * 3));
            random = new Random();//随机数
            int profit = random.nextInt(100);
            entries.add(new BarEntry(ss, profit));
            intyy.add(profit);
            Log.i("asdasdasd", xVals.get(0));
            XAxis xAxis = barchart1.getXAxis();
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
            xAxis.setAxisLineWidth(3f);
            xAxis.setTextSize(10f);
            xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线  
            Legend legend = barchart1.getLegend();
            legend.setEnabled(false);
            barchart1.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            barDataSet = new BarDataSet(entries, "公司年利润报表");
            barDataSet.setColor(R.color.color6);
            data = new BarData(barDataSet);
            barchart1.setData(data);
                barchart1.notifyDataSetChanged();
            barchart1.invalidate();
            Description description = new Description();
            description.setText("公司前半年财务报表(单位：万元)");
            barchart1.setDescription(description);
            Collections.sort(intyy);
            Log.i("ajsdhaksdhkashd", String.valueOf(intyy.get(intyy.size() - 1)));
            textF6.setText("二氧化碳最大值" + String.valueOf(intyy.get(intyy.size() - 1)) + "     ");
                ss++;
                handler.postDelayed(rong, 1000);
            } else {
                handler.removeCallbacks(rong);
            }

        }
    }
}
