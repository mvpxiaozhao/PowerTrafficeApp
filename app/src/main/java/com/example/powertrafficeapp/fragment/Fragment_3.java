/**
 *
 */
package com.example.powertrafficeapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
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


public class Fragment_3 extends Fragment {
    int[] arrayw = {1, 2, 3, 4, 5};
    List<String> list;
    String urlHost;
    int honglu = 1;
    Button button;
    CheckBox check;
    ArrayAdapter adapter;
    LinearLayout Ralativeee;
    View relativeLayout;
    String hong1, huang1, lv1, hong2, huang2, lv2, hong3, huang3, lv3, hong4, huang4, lv4, hong5, huang5, lv5;
    int arraylu[];
    int arrayhuang[];
    int arrayhong[];
    String http = "";
    int pai = 1;
    private Spinner spinnerF3;
    private UrlBean urlBean;
    private String[] name = {"路口", "红灯时长", "黄灯时长", "绿灯时长", "操作项", " 设置 "};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout03, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        spinnerF3 = (Spinner) getActivity().findViewById(R.id.spinner_f3);
        Ralativeee = (LinearLayout) getActivity().findViewById(R.id.Ralativeerrrddroe);
        list = new ArrayList<String>();
        list.add("路口升序");
        list.add("路口降序");
        list.add("绿灯升序");
        list.add("绿灯降序");
        urlBean = Util.loadSetting("httpbao", "http", "port", getContext());
        http = "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/type/jason/action/GetTrafficLightConfigAction.do";
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerF3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Ralativeee.removeAllViews();
                        pai = 1;
                        intent();
                        break;
                    case 1:
                        Ralativeee.removeAllViews();
                        pai = 2;
                        intent();
                        break;
                    case 2:
                        pai = 3;
                        Ralativeee.removeAllViews();
                        intent();
                        break;
                    case 3:
                        Ralativeee.removeAllViews();
                        pai = 4;
                        intent();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinnerF3.setAdapter(adapter);
    }

    private void intent() {
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
        JSONObject object = new JSONObject();
        try {
            object.put("TrafficLightId", honglu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("saasdasd", http + object);
        getAllCarValue(http, object);
    }

    private void getAllCarValue(String urlHostAction, JSONObject params) {
        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlHostAction, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stu
                Log.i("TAG volley", response.toString());
                String str = response.toString();
                if (honglu == 1) {
                    try {
                        JSONObject df = new JSONObject(str);
                        String dsd = df.getString("serverinfo");
                        JSONObject dfee = new JSONObject(dsd);
                        hong1 = dfee.getString("RedTime");
                        huang1 = dfee.getString("YellowTime");
                        lv1 = dfee.getString("GreenTime");
                        shuju();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (honglu == 2) {
                    try {
                        JSONObject df = new JSONObject(str);
                        String dsd = df.getString("serverinfo");
                        JSONObject dfee = new JSONObject(dsd);
                        hong2 = dfee.getString("RedTime");
                        huang2 = dfee.getString("YellowTime");
                        lv2 = dfee.getString("GreenTime");
                        shuju();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (honglu == 3) {
                    try {
                        JSONObject df = new JSONObject(str);
                        String dsd = df.getString("serverinfo");
                        JSONObject dfee = new JSONObject(dsd);
                        hong3 = dfee.getString("RedTime");
                        huang3 = dfee.getString("YellowTime");
                        lv3 = dfee.getString("GreenTime");
                        shuju();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (honglu == 4) {
                    try {
                        JSONObject df = new JSONObject(str);
                        String dsd = df.getString("serverinfo");
                        JSONObject dfee = new JSONObject(dsd);
                        hong4 = dfee.getString("RedTime");
                        huang4 = dfee.getString("YellowTime");
                        lv4 = dfee.getString("GreenTime");
                        shuju();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (honglu == 5) {
                    try {
                        JSONObject df = new JSONObject(str);
                        String dsd = df.getString("serverinfo");
                        JSONObject dfee = new JSONObject(dsd);
                        hong5 = dfee.getString("RedTime");
                        huang5 = dfee.getString("YellowTime");
                        lv5 = dfee.getString("GreenTime");
                        int lu1 = Integer.parseInt(lv1);
                        int lu2 = Integer.parseInt(lv2);
                        int lu3 = Integer.parseInt(lv3);
                        int lu4 = Integer.parseInt(lv4);
                        int lu5 = Integer.parseInt(lv5);
                        int hu1 = Integer.parseInt(huang1);
                        int hu2 = Integer.parseInt(huang2);
                        int hu3 = Integer.parseInt(huang3);
                        int hu4 = Integer.parseInt(huang4);
                        int hu5 = Integer.parseInt(huang5);
                        int ho1 = Integer.parseInt(hong1);
                        int ho2 = Integer.parseInt(hong2);
                        int ho3 = Integer.parseInt(hong3);
                        int ho4 = Integer.parseInt(hong4);
                        int ho5 = Integer.parseInt(hong5);
                        arraylu = new int[]{lu1, lu2, lu3, lu4, lu5};
                        arrayhuang = new int[]{hu1, hu2, hu3, hu4, hu5};
                        arrayhong = new int[]{ho1, ho2, ho3, ho4, ho5};
                        if (pai == 1) {
                            arrayw = new int[]{1, 2, 3, 4, 5};
                            arraylu = new int[]{lu1, lu2, lu3, lu4, lu5};
                            arrayhuang = new int[]{hu1, hu2, hu3, hu4, hu5};
                            arrayhong = new int[]{ho1, ho2, ho3, ho4, ho5};
                        } else if (pai == 2) {
                            arrayw = new int[]{5, 4, 3, 2, 1};
                            arraylu = new int[]{lu5, lu4, lu3, lu2, lu1};
                            arrayhuang = new int[]{hu5, hu4, hu3, hu2, hu1};
                            arrayhong = new int[]{ho5, ho4, ho3, ho2, ho1};

                        } else if (pai == 3) {
                            for (int e = 0; e < (arraylu.length - 1); e++) {
                                for (int j = e + 1; j < arraylu.length; j++) {
                                    if (arraylu[e] > arraylu[j]) {
                                        int lu = arraylu[e];
                                        arraylu[e] = arraylu[j];
                                        arraylu[j] = lu;
                                        int huang = arrayhuang[e];
                                        arrayhuang[e] = arrayhuang[j];
                                        arrayhuang[j] = huang;
                                        int hong = arrayhong[e];
                                        arrayhong[e] = arrayhong[j];
                                        arrayhong[j] = hong;
                                        int pai = arrayw[e];
                                        arrayw[e] = arrayw[j];
                                        arrayw[j] = pai;
                                    }
                                }
                            }
                        } else if (pai == 4) {
                            for (int e = 0; e < (arraylu.length - 1); e++) {
                                for (int j = e + 1; j < arraylu.length; j++) {
                                    if (arraylu[e] < arraylu[j]) {
                                        int lu = arraylu[e];
                                        arraylu[e] = arraylu[j];
                                        arraylu[j] = lu;
                                        int huang = arrayhuang[e];
                                        arrayhuang[e] = arrayhuang[j];
                                        arrayhuang[j] = huang;
                                        int hong = arrayhong[e];
                                        arrayhong[e] = arrayhong[j];
                                        arrayhong[j] = hong;
                                        int pai = arrayw[e];
                                        arrayw[e] = arrayw[j];
                                        arrayw[j] = pai;
                                    }
                                }
                            }
                        }

                        sdf();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
            getAllCarValue(http, object);
        } else {
            sdf();
        }
    }

    public void sdf() {
        for (int i = 0; i < arrayw.length; i++) {
            relativeLayout = LayoutInflater.from(getActivity()).inflate(R.layout.table, null);
            MyTableTextView title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
            title.setText(String.valueOf(arrayw[i]));
            title.setTextColor(Color.BLUE);
            title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
            title.setText(String.valueOf(arrayhong[i]));
            title.setTextColor(Color.BLUE);
            title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
            title.setText(String.valueOf(arrayhuang[i]));
            title.setTextColor(Color.BLUE);
            title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
            title.setText(String.valueOf(arraylu[i]));
            title.setTextColor(Color.BLUE);
            final int finalI = i;
            check = (CheckBox) relativeLayout.findViewById(R.id.checkbox);
            check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String sads = String.valueOf(isChecked);
                    switch (finalI) {
                        case 0:
                            Toast.makeText(getActivity(), sads, Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Toast.makeText(getActivity(), sads, Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(getActivity(), sads, Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(getActivity(), sads, Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            Toast.makeText(getActivity(), sads, Toast.LENGTH_SHORT).show();
                            break;

                    }
                }
            });
            button = (Button) relativeLayout.findViewById(R.id.btn_shezhi);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (finalI) {
                        case 0:
                            Toast.makeText(getActivity(), String.valueOf(arrayw[finalI]), Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Toast.makeText(getActivity(), String.valueOf(arrayw[finalI]), Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(getActivity(), String.valueOf(arrayw[finalI]), Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(getActivity(), String.valueOf(arrayw[finalI]), Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            Toast.makeText(getActivity(), String.valueOf(arrayw[finalI]), Toast.LENGTH_SHORT).show();
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
