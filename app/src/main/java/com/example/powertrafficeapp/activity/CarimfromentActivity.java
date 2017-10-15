package com.example.powertrafficeapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.powertrafficeapp.R;
import com.example.powertrafficeapp.fragment.carinfor.Carinfor_1;
import com.example.powertrafficeapp.fragment.carinfor.Carinfor_2;
import com.example.powertrafficeapp.fragment.carinfor.Carinfor_3;

public class CarimfromentActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    String hh = "rtyttr";
    String hsadasdh = "asdas";
    private RadioGroup RadiogroupF8;
    private LinearLayout LinearF8Xin;
    private TextView text_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carimfroment);
        RadiogroupF8 = (RadioGroup) findViewById(R.id.Radiogroup_f8);
        LinearF8Xin = (LinearLayout) findViewById(R.id.Linear_f8_xin);
        text_name = (TextView) findViewById(R.id.textView_car_name);
        RadiogroupF8.setOnCheckedChangeListener(this);
        Intent intent = getIntent();
        String value_name = intent.getStringExtra("key");
        text_name.setText(value_name);
        RadiogroupF8.check(R.id.Radiobutton_f8_1);
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case R.id.Radiobutton_f8_1:
                getSupportFragmentManager().beginTransaction().replace(R.id.Linear_f8_xin, new Carinfor_1()).commit();
                break;
            case R.id.Radiobutton_f8_2:
                getSupportFragmentManager().beginTransaction().replace(R.id.Linear_f8_xin, new Carinfor_2()).commit();
                break;
            case R.id.Radiobutton_f8_3:
                getSupportFragmentManager().beginTransaction().replace(R.id.Linear_f8_xin, new Carinfor_3()).commit();
                break;
        }

    }
}
