package com.example.powertrafficeapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.powertrafficeapp.R;
import com.example.powertrafficeapp.activity.CarimfromentActivity;


/**
 * Created by dell on 2017/07/30.
 */

public class Fragment_8 extends Fragment {
    private Button button_f8;
    private EditText editText_f8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout08, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        button_f8 = (Button) getActivity().findViewById(R.id.button_f8_cha);
        editText_f8 = (EditText) getActivity().findViewById(R.id.ed_f8_name);
        button_f8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String editText = editText_f8.getText().toString().trim();
                if (editText.equals("")) {
                    Toast.makeText(getActivity(), "姓名不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent1 = new Intent(getActivity(), CarimfromentActivity.class);
                    intent1.putExtra("key", editText);
                    startActivity(intent1);
                }
            }
        });
        super.onActivityCreated(savedInstanceState);
    }
}
