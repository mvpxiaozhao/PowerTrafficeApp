package com.example.powertrafficeapp.fragment.carinfor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.powertrafficeapp.R;
import com.example.powertrafficeapp.util.Util;


/**
 * Created by dell on 2017/08/01.
 */

public class Carinfor_3 extends Fragment {
    Util util;
    private TextView textView42;
    private TextView textViewF8C3;
    private TextView textView39;
    private TextView textView44;
    private EditText editTextF8C3;
    private TextView textView43;
    private Button buttonF8C3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.carinfor_3, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView42 = (TextView) getActivity().findViewById(R.id.textView42);
        textViewF8C3 = (TextView) getActivity().findViewById(R.id.textView_f8_c3);
        textView39 = (TextView) getActivity().findViewById(R.id.textView39);
        textView44 = (TextView) getActivity().findViewById(R.id.textView44);
        editTextF8C3 = (EditText) getActivity().findViewById(R.id.editText_f8_c3);
        textView43 = (TextView) getActivity().findViewById(R.id.textView43);
        buttonF8C3 = (Button) getActivity().findViewById(R.id.button_f8_c3);
        int ert = Util.loadSettingtime("chabao", "cha", getActivity());
        String err = String.valueOf(ert);
        textViewF8C3.setText(err);
        buttonF8C3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ert = editTextF8C3.getText().toString();
                if (ert.equals("")) {
                    Toast.makeText(getActivity(), "时间不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    int time = Integer.parseInt(ert);
                    Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_SHORT).show();
                    Util.saveSettingtime("chabao", "cha", time, getActivity());
                }
            }
        });
    }
}
