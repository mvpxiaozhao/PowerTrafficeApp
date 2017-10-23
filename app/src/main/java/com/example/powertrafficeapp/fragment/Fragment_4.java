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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.powertrafficeapp.R;


public class Fragment_4 extends Fragment {
    android.support.v4.app.FragmentManager fm;
    private TextView textView2;
    private TextView textView4;
    private EditText editText;
    private Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout04, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView2 = (TextView) getActivity().findViewById(R.id.textView2);
        textView4 = (TextView) getActivity().findViewById(R.id.textView4);
        editText = (EditText) getActivity().findViewById(R.id.editText);
        button = (Button) getActivity().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
