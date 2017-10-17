package com.example.powertrafficeapp.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.powertrafficeapp.R;
import com.example.powertrafficeapp.fragment.Chart_Fragment.Chart_Fragment_1;
import com.example.powertrafficeapp.fragment.Chart_Fragment.Chart_Fragment_2;
import com.example.powertrafficeapp.fragment.Chart_Fragment.Chart_Fragment_3;
import com.example.powertrafficeapp.fragment.Chart_Fragment.Chart_Fragment_4;
import com.example.powertrafficeapp.fragment.Chart_Fragment.Chart_Fragment_5;
import com.example.powertrafficeapp.fragment.Chart_Fragment.Chart_Fragment_6;
import com.example.powertrafficeapp.fragment.Chart_Fragment.Chart_Fragment_7;

import java.util.ArrayList;


/**
 * Created by dell on 2017/07/30.
 */

public class Fragment_7 extends Fragment implements RadioGroup.OnCheckedChangeListener {
    ArrayList<Fragment> fragments;
    MychartAdaptere mychart;
    String biaoming[] = {"表1", "表2", "表3", "表4", "表5", "表6", "表7"};
    private ViewPager VpF7Chart;
    private TextView textviewF7biaoming;
    private RadioGroup radioGroupF7;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout07, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        VpF7Chart = (ViewPager) getActivity().findViewById(R.id.Vp_F7_chart);
        textviewF7biaoming = (TextView) getActivity().findViewById(R.id.textView_F7_biaoming);
        radioGroupF7 = (RadioGroup) getActivity().findViewById(R.id.RadioGroup_F);
        fragments = new ArrayList<Fragment>();
        Fragment chart1 = new Chart_Fragment_1();
        Fragment chart2 = new Chart_Fragment_2();
        Fragment chart3 = new Chart_Fragment_3();
        Fragment chart4 = new Chart_Fragment_4();
        Fragment chart5 = new Chart_Fragment_5();
        Fragment chart6 = new Chart_Fragment_6();
        Fragment chart7 = new Chart_Fragment_7();
        fragments.add(chart1);
        fragments.add(chart2);
        fragments.add(chart3);
        fragments.add(chart4);
        fragments.add(chart5);
        fragments.add(chart6);
        fragments.add(chart7);
        mychart = new MychartAdaptere(getFragmentManager(), fragments);
        VpF7Chart.setAdapter(mychart);

        VpF7Chart.addOnPageChangeListener(new Mylistner());
        radioGroupF7.setOnCheckedChangeListener(this);
        VpF7Chart.setCurrentItem(0);
        radioGroupF7.check(R.id.radioButton_F7_1);
        textviewF7biaoming.setText(biaoming[0]);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        int current = 0;
        switch (i) {
            case R.id.radioButton_F7_1:
                current = 0;
                break;
            case R.id.radioButton_F7_2:
                current = 1;
                break;
            case R.id.radioButton_F7_3:
                current = 2;
                break;
            case R.id.radioButton_F7_4:
                current = 3;
                break;
            case R.id.radioButton_F7_5:
                current = 4;
                break;
            case R.id.radioButton_F7_6:
                current = 5;
                break;
            case R.id.radioButton_F7_7:
                current = 6;
                break;
        }
        if (VpF7Chart.getCurrentItem() != current) {
            VpF7Chart.setCurrentItem(current);
            textviewF7biaoming.setText(biaoming[current]);
        }

    }

    public class Mylistner implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int current = VpF7Chart.getCurrentItem();
            switch (current) {
                case 0:
                    radioGroupF7.check(R.id.radioButton_F7_1);
                    textviewF7biaoming.setText(biaoming[current]);
                    break;
                case 1:
                    radioGroupF7.check(R.id.radioButton_F7_2);
                    textviewF7biaoming.setText(biaoming[current]);
                    break;
                case 2:
                    radioGroupF7.check(R.id.radioButton_F7_3);
                    textviewF7biaoming.setText(biaoming[current]);
                    break;
                case 3:
                    radioGroupF7.check(R.id.radioButton_F7_4);
                    textviewF7biaoming.setText(biaoming[current]);
                    break;
                case 4:
                    radioGroupF7.check(R.id.radioButton_F7_5);
                    textviewF7biaoming.setText(biaoming[current]);
                    break;
                case 5:
                    radioGroupF7.check(R.id.radioButton_F7_6);
                    textviewF7biaoming.setText(biaoming[current]);
                    break;
                case 6:
                    radioGroupF7.check(R.id.radioButton_F7_7);
                    textviewF7biaoming.setText(biaoming[current]);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    class MychartAdaptere extends FragmentPagerAdapter {
        ArrayList<Fragment> list;
        public MychartAdaptere(FragmentManager fm, ArrayList<Fragment> chart) {
            super(fm);
            list = chart;
        }
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }
        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}