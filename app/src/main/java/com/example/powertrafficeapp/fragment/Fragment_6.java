package com.example.powertrafficeapp.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
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
import java.util.List;
import java.util.Random;


/**
 * Created by dell on 2017/07/30.
 */

public class Fragment_6 extends Fragment {
    int ss = 0;
    String week[] = {"昨天", "今天", "明天", "周五", "周六", "周日", "周一"};
    List<Fragment> list = new ArrayList<>();
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
    private ViewPager viewpager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout06, container, false);
        return view;
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
        viewpager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        list.add(new Fragment_f6_c1());
        list.add(new Fragment_f6_c2());
        list.add(new Fragment_f6_c3());
        list.add(new Fragment_f6_c4());
        Viewp viewp = new Viewp(getFragmentManager());
        viewpager.setAdapter(viewp);
        viewpager.addOnPageChangeListener(new Mypager());
        try {
            finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        viewpager.setCurrentItem(0);
        grour.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int uu = 0;
                switch (checkedId) {
                    case R.id.ridebutton1_f6:
                        uu = 0;
                        break;
                    case R.id.ridebutton2_f6:
                        uu = 1;
                        break;
                    case R.id.ridebutton3_f6:
                        uu = 2;
                        break;
                    case R.id.ridebutton4_f6:
                        uu = 3;
                        break;
                }
                if (viewpager.getCurrentItem() != uu) {
                    viewpager.setCurrentItem(uu);
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
        Legend legend = lineChart.getLegend();//设置图例
        legend.setEnabled(false);//不显示图例
        legend.setTextColor(Color.CYAN); //设置Legend 文本颜色
        lineChart.setData(data1);//给图表添加数据
        Description description = new Description();//设置描述
        description.setText("温度变化表");
        lineChart.setHighlightPerDragEnabled(true);//设置高亮
        lineChart.getAxisRight().setDrawGridLines(true);
        lineChart.getAxisLeft().setDrawGridLines(true);
        lineChart.getXAxis().setDrawGridLines(true);
        lineChart.setDescription(description);//设置图表描述的内容位置，字体等等
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.TOP);//设置X轴的显示位置，通过XAxisPosition枚举类型来设置
        lineChart.getAxisRight().setEnabled(false);//关闭右边的Y轴，因为默认有两条，左边一条，右边一条，MPAndroidChart中有setEnabled方法的元素基本上都是使能的作用
        lineChart.getAxisLeft().setEnabled(false);//关闭右边的Y轴，因为默认有两条，左边一条，右边一条，MPAndroidChart中有setEnabled方法的元素基本上都是使能的作用
        lineChart.animateY(3000);//动画效果，MPAndroidChart中还有很多动画效果可以挖掘
    }
    class Viewp extends FragmentPagerAdapter {
        public Viewp(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }
        @Override
        public int getCount() {
            return list.size();
        }
    }
    class Mypager implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {
            int current = viewpager.getCurrentItem();
            switch (current) {
                case 0:
                    grour.check(R.id.ridebutton1_f6);
                    break;
                case 1:
                    grour.check(R.id.ridebutton2_f6);
                    break;
                case 2:
                    grour.check(R.id.ridebutton3_f6);
                    break;
                case 3:
                    grour.check(R.id.ridebutton4_f6);
                    break;
            }
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
