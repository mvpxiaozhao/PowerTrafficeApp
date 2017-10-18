/**
 *
 */
package com.example.powertrafficeapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.powertrafficeapp.R;
import com.example.powertrafficeapp.fragment.lukou.lukou_fragment_1;
import com.example.powertrafficeapp.fragment.lukou.lukou_fragment_3;
import com.example.powertrafficeapp.fragment.lukou.lukou_fragment_4;

import java.util.ArrayList;
import java.util.List;


public class Fragment_3 extends Fragment {
    List<String> list;
    ArrayAdapter adapter;
    private Spinner spinnerF3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout03, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        spinnerF3 = (Spinner) getActivity().findViewById(R.id.spinner_f3);
        list = new ArrayList<String>();
        list.add("路口升序");
        list.add("路口降序");
        list.add("绿灯升序");
        list.add("绿灯降序");
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerF3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        String data = (String) spinnerF3.getItemAtPosition(i);//从spinner中获取被选择的数据
                        getFragmentManager().beginTransaction().replace(R.id.sdsadfsdas, new lukou_fragment_1()).commit();
                        break;
                    case 1:
                        String datad = (String) spinnerF3.getItemAtPosition(i);//从spinner中获取被选择的数据
                        Toast.makeText(getActivity(), datad, Toast.LENGTH_SHORT).show();

                        break;
                    case 2:
                        String datadd = (String) spinnerF3.getItemAtPosition(i);//从spinner中获取被选择的数据
                        Toast.makeText(getActivity(), datadd, Toast.LENGTH_SHORT).show();
                        getFragmentManager().beginTransaction().replace(R.id.sdsadfsdas, new lukou_fragment_3()).commit();
                        break;
                    case 3:
                        String dataddd = (String) spinnerF3.getItemAtPosition(i);//从spinner中获取被选择的数据
                        Toast.makeText(getActivity(), dataddd, Toast.LENGTH_SHORT).show();
                        getFragmentManager().beginTransaction().replace(R.id.sdsadfsdas, new lukou_fragment_4()).commit();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinnerF3.setAdapter(adapter);
    }
}
