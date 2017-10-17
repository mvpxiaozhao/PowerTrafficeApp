package com.example.powertrafficeapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.powertrafficeapp.R;
import com.example.powertrafficeapp.fragment.f6.Fragment_f6_c1;
import com.example.powertrafficeapp.fragment.f6.Fragment_f6_c2;
import com.example.powertrafficeapp.fragment.f6.Fragment_f6_c3;
import com.example.powertrafficeapp.fragment.f6.Fragment_f6_c4;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by dell on 2017/07/30.
 */

public class Fragment_6 extends Fragment {
    int ss = 0;
    String week[] = {"昨天", "今天", "明天", "周五", "周六", "周日", "周一"};
    private LineChart lineChart;
    private Random random;
    private LineData data1;
    private LineDataSet dataSet1;
    private LineDataSet dataSet2;
    private TextView textView5;
    private TextView textView6;
    private Button button2;
    private TextView textView7;
    private TextView textView22;
    private TextView textView16;
    private TextView textView9;
    private TextView textView8;
    private RadioGroup grour;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout06, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lineChart = (LineChart) getActivity().findViewById(R.id.chart_line);
        textView5 = (TextView) getActivity().findViewById(R.id.textView5);
        textView6 = (TextView) getActivity().findViewById(R.id.textView6);
        button2 = (Button) getActivity().findViewById(R.id.button2);
        textView7 = (TextView) getActivity().findViewById(R.id.textView7);
        textView22 = (TextView) getActivity().findViewById(R.id.textView22);
        textView16 = (TextView) getActivity().findViewById(R.id.textView16);
        textView9 = (TextView) getActivity().findViewById(R.id.textView9);
        textView8 = (TextView) getActivity().findViewById(R.id.textView8);
        grour = (RadioGroup) getActivity().findViewById(R.id.grour);
        getFragmentManager().beginTransaction().replace(R.id.linerrrr, new Fragment_f6_c1()).commit();
        grour.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.ridebutton1_f6:
                        getFragmentManager().beginTransaction().replace(R.id.linerrrr, new Fragment_f6_c1()).commit();
                        break;
                    case R.id.ridebutton2_f6:
                        getFragmentManager().beginTransaction().replace(R.id.linerrrr, new Fragment_f6_c2()).commit();
                        break;
                    case R.id.ridebutton3_f6:
                        getFragmentManager().beginTransaction().replace(R.id.linerrrr, new Fragment_f6_c3()).commit();
                        break;
                    case R.id.ridebutton4_f6:
                        getFragmentManager().beginTransaction().replace(R.id.linerrrr, new Fragment_f6_c4()).commit();
                        break;
                }
            }
        });
        LineChart();
    }
    public void LineChart() {
        ArrayList<Entry> yVals = new ArrayList<>();
        ArrayList<Entry> yValse = new ArrayList<>();
        random = new Random();//产生随机数字
        for (int i = 0; i < week.length; i++) {
            int ff = random.nextInt(100);
            yVals.add(new Entry(i, ff));
        }
        for (int i = 0; i < week.length; i++) {
            int ee = random.nextInt(100);
            yValse.add(new Entry(i, ee));
        }
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return week[(int) v % week.length];
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });
        dataSet1 = new LineDataSet(yVals, "温度");//创建数据集并设置标签
        dataSet1.setValueTextColor(Color.BLUE);//设置Value值的显示文字颜色，字体大小和字体种类，这里我没有添加对应字体可以自己修改
        dataSet1.setValueTextSize(10f);
        dataSet1.setValueTypeface(null);
        dataSet1.setDrawCircles(false);      // 不显示坐标点的小圆点

        dataSet1.setHighlightEnabled(false);  // 不显示定位线
        dataSet2 = new LineDataSet(yValse, "温度cc");//创建数据集并设置标签
        dataSet2.setDrawCircles(false);      // 不显示坐标点的小圆点

        dataSet2.setHighlightEnabled(false);  // 不显示定位线
        dataSet2.setValueTextColor(Color.BLUE);//设置Value值的显示文字颜色，字体大小和字体种类，这里我没有添加对应字体可以自己修改
        dataSet2.setValueTextSize(10f);
        dataSet2.setColor(R.color.color1);
        dataSet2.setValueTypeface(null);
        data1 = new LineData(dataSet1);//创建LineData,x轴List和Y轴数据集为参数
        data1.addDataSet(dataSet2);
//        // 是否在折线图上添加边框
//        mLineChart.setDrawBorders(false);
//        mLineChart.setDescription("描述@ZhangPhil");// 数据描述
//        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
//        mLineChart.setNoDataTextDescription("如果传给MPAndroidChart的数据为空，那么你将看到这段文字。@Zhang Phil");
//        // 是否绘制背景颜色。
//        // 如果mLineChart.setDrawGridBackground(false)，
//        // 那么mLineChart.setGridBackgroundColor(Color.CYAN)将失效;
//        mLineChart.setDrawGridBackground(false);
//        mLineChart.setGridBackgroundColor(Color.CYAN);
//        // 触摸
//        mLineChart.setTouchEnabled(true);
//        // 拖拽
//        mLineChart.setDragEnabled(true);
//        // 缩放
//        mLineChart.setScaleEnabled(true);
//        mLineChart.setPinchZoom(false);
//        // 隐藏右边 的坐标轴
//        mLineChart.getAxisRight().setEnabled(false);
//        // 让x轴在下面
//        mLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
//        // // 隐藏左边坐标轴横网格线
//        // mLineChart.getAxisLeft().setDrawGridLines(false);
//        // // 隐藏右边坐标轴横网格线
//        // mLineChart.getAxisRight().setDrawGridLines(false);
//        // // 隐藏X轴竖网格线
//        // mLineChart.getXAxis().setDrawGridLines(false);
//        mLineChart.getAxisRight().setEnabled(true); // 隐藏右边 的坐标轴(true不隐藏)
        Legend legend = lineChart.getLegend();//设置图例
        legend.setEnabled(false);//不显示图例
        legend.setTextColor(Color.CYAN); //设置Legend 文本颜色
        lineChart.setData(data1);//给图表添加数据
        Description description = new Description();//设置描述
        description.setText("温度变化表");
        lineChart.setHighlightPerDragEnabled(true);//设置高亮
        lineChart.getAxisRight().setDrawGridLines(true);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.setDescription(description);//设置图表描述的内容位置，字体等等
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.TOP);//设置X轴的显示位置，通过XAxisPosition枚举类型来设置
        lineChart.getAxisRight().setEnabled(false);//关闭右边的Y轴，因为默认有两条，左边一条，右边一条，MPAndroidChart中有setEnabled方法的元素基本上都是使能的作用
        lineChart.getAxisLeft().setEnabled(true);//关闭右边的Y轴，因为默认有两条，左边一条，右边一条，MPAndroidChart中有setEnabled方法的元素基本上都是使能的作用
        lineChart.animateY(3000);//动画效果，MPAndroidChart中还有很多动画效果可以挖掘
    }
}
