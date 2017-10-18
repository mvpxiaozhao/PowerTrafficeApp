package com.example.powertrafficeapp.fragment.lukou;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.powertrafficeapp.R;
import com.example.powertrafficeapp.View.MyTableTextView;
import com.example.powertrafficeapp.util.UrlBean;
import com.example.powertrafficeapp.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dell on 2017/08/30.
 */

public class lukou_fragment_1 extends Fragment {
    String urlHost;
    int honglu = 1;
    Button button;
    CheckBox check;
    int dd[] = {1, 2, 3, 4, 5};
    List<String> lhong = new ArrayList<>();
    List<String> lhuang = new ArrayList<>();
    List<String> llv = new ArrayList<>();
    private Button buttonF3Refer;
    private Button buttonF3Pl;
    private Button buttonCuo;
    private LinearLayout Ralativeee;
    private UrlBean urlBean;
    private View relativeLayout;
    private String[] name = {"路口", "红灯时长", "黄灯时长", "绿灯时长", "操作项", " 设置 "};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lukou_fragment_1, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Ralativeee = (LinearLayout) getActivity().findViewById(R.id.Ralativeerrrroe);
        urlBean = Util.loadSetting("httpbao", "http", "port", getContext());
        LayoutInflater inflate = LayoutInflater.from(getActivity());
        relativeLayout = inflate.inflate(R.layout.table, null);
        MyTableTextView title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
        title.setText(name[0]);
        title.setTextColor(Color.BLUE);
        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
        title.setText(name[1]);
        title.setTextColor(Color.BLUE);
        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
        title.setText(name[2]);
        title.setTextColor(Color.BLUE);
        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
        title.setText(name[3]);
        title.setTextColor(Color.BLUE);
        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_5);
        title.setText(name[4]);
        title.setTextColor(Color.BLUE);
        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_6);
        title.setText(name[5]);
        title.setTextColor(Color.BLUE);
        check = (CheckBox) relativeLayout.findViewById(R.id.checkbox);
        check.setVisibility(View.GONE);
        button = (Button) relativeLayout.findViewById(R.id.btn_shezhi);
        button.setVisibility(View.GONE);
        Ralativeee.addView(relativeLayout);
        urlHost = "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/type/jason/action/GetTrafficLightConfigAction.do";
        JSONObject object = new JSONObject();
        try {
            object.put("TrafficLightId", honglu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getAllCarValue(urlHost, object);
    }

    private void getAllCarValue(String urlHostAction, JSONObject params) {
        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlHostAction, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stu
                Log.i("TAG volley", response.toString());
                String str = response.toString();
                try {
                    JSONObject df = new JSONObject(str);
                    String dsd = df.getString("serverinfo");
                    JSONObject dfee = new JSONObject(dsd);
                    String hong = dfee.getString("RedTime");
                    String huang = dfee.getString("YellowTime");
                    String lv = dfee.getString("GreenTime");
                    lhong.add(hong);
                    lhuang.add(huang);
                    llv.add(lv);
                    shuju();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
                Log.i("TAG volley", error.toString());
            }
        });
        mQueue.add(jsonObjectRequest);
    }

    public void shuju() {

        honglu++;
        if (honglu < 6) {
            JSONObject object = new JSONObject();
            try {
                object.put("TrafficLightId", honglu);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("dasds", String.valueOf(object));
            getAllCarValue(urlHost, object);
        } else {
            sdf();
        }
    }

    public void dialoge() {
        final Dialog dialoge = new Dialog(getActivity());
        Log.i("TAG volley", "sdfsdfsdfsdfsdfsdfs");
        dialoge.setTitle("查询结果");
        dialoge.getWindow().setContentView(R.layout.dialog_sdf_f3);
        buttonCuo = (Button) dialoge.getWindow().findViewById(R.id.button_cuo);
        buttonCuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialoge.dismiss();
            }
        });
        dialoge.show();
    }

    public void sdf() {
        for (int i = 0; i < lhuang.size(); i++) {
            relativeLayout = LayoutInflater.from(getActivity()).inflate(R.layout.table, null);
            MyTableTextView title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
            title.setText(String.valueOf(i));
            title.setTextColor(Color.BLUE);
            title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
            title.setText(String.valueOf(lhong.get(i)));
            title.setTextColor(Color.BLUE);
            title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
            title.setText(String.valueOf(lhuang.get(i)));
            title.setTextColor(Color.BLUE);
            title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
            title.setText(String.valueOf(llv.get(i)));
            title.setTextColor(Color.BLUE);
            check = (CheckBox) relativeLayout.findViewById(R.id.checkbox);
            check.isChecked();
            button = (Button) relativeLayout.findViewById(R.id.btn_shezhi);
            final int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (finalI) {
                        case 0:
                            Log.i("dsad", "0");
                            break;
                        case 1:
                            Log.i("dsad", "1");
                            break;
                        case 2:
                            Log.i("dsad", "2");
                            break;
                        case 3:
                            Log.i("dsad", "3");
                            break;
                        case 4:
                            Log.i("dsad", "3");
                            break;

                    }
                }
            });
            button.setText(" 设置 ");

            Log.i("dsad", String.valueOf(button.getId()));
            Ralativeee.addView(relativeLayout);
            Log.i("dsad", String.valueOf(relativeLayout.getId()));
        }

    }
}

