package com.example.powertrafficeapp.fragment.Chart_Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.powertrafficeapp.R;
import com.example.powertrafficeapp.util.MyMarkerView;
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
 * Created by dell on 2017/08/01.
 */

public class Chart_Fragment_1 extends Fragment {
    String week[] = {"昨天", "今天", "明天", "周五", "周六", "周日", "周一"};
    private LineChart lineChart;
    private Random random;
    private LineData data1;
    private LineDataSet dataSet1;
    private LineDataSet dataSet2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chart_fragment_layout1, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lineChart = (LineChart) getActivity().findViewById(R.id.chart_f7_line);
        LineChart();
    }

    public void LineChart() {
        ArrayList<Entry> xeValsd = new ArrayList<>();
        ArrayList<Entry> yVals = new ArrayList<>();
        random = new Random();//产生随机数字
        for (int i = 0; i < 7; i++) {
            float x = random.nextInt(100);//获取value值
            yVals.add(new Entry(i, x));//创建Entry并且添加到Y值的list中，Y轴的值，一个entry代表一个显示的值
        }
        xeValsd.add(new Entry(0, 30));
        xeValsd.add(new Entry(1, 78));
        xeValsd.add(new Entry(2, 96));
        xeValsd.add(new Entry(3, 52));
        xeValsd.add(new Entry(4, 12));
        xeValsd.add(new Entry(5, 71));
        xeValsd.add(new Entry(6, 68));
        dataSet1 = new LineDataSet(yVals, "今天温度");//创建数据集并设置标签
        dataSet1.setColor(Color.WHITE);//设置数据集显示的颜色，预支颜色模版ColorTemplate，也可以设置单一颜色和colors
        dataSet1.setValueTextColor(Color.BLUE);//设置Value值的显示文字颜色，字体大小和字体种类，这里我没有添加对应字体可以自己修改
        dataSet1.setValueTextSize(10.0f);
        dataSet1.setValueTypeface(null);
        //加X轴数据
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
        xAxis.setGranularity(1f);//最小单位
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.TOP);//设置X轴的显示位置，通过XAxisPosition枚举类型来设置
        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.textview);
        lineChart.setMarker(mv);
        dataSet2 = new LineDataSet(xeValsd, "昨天温度");//创建数据集并设置标签
        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);
        dataSet2.setColor(Color.BLUE);//设置数据集显示的颜色，预支颜色模版ColorTemplate，也可以设置单一颜色和colors
        dataSet2.setHighlightEnabled(true);//设置高亮
        dataSet2.setValueTextColor(Color.BLACK);//设置Value值的显示文字颜色，字体大小和字体种类，这里我没有添加对应字体可以自己修改
        dataSet2.setValueTextSize(10.0f);
        dataSet2.setValueTypeface(null);
        data1 = new LineData(dataSet1);
        //创建LineData,x轴List和Y轴数据集为参数
        data1.addDataSet(dataSet2);
        Description description = new Description();
        description.setText("温度变化表");
        lineChart.setData(data1);//给图表添加数据
        lineChart.setDescription(description);//设置图表描述的内容位置，字体等等
        lineChart.getAxisRight().setEnabled(false);//关闭右边的Y轴，因为默认有两条，左边一条，右边一条，MPAndroidChart中有setEnabled方法的元素基本上都是使能的作用
        lineChart.animateY(3000);//动画效果，MPAndroidChart中还有很多动画效果可以挖掘
        lineChart.invalidate();//在chart中调用会使其刷新重绘
    }
}
