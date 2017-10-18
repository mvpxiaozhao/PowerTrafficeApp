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


/**
 * Created by dell on 2017/08/30.
 */

public class lukou_fragment_4 extends Fragment {
    int ddd;
    int[] arrayw = {1, 2, 3, 4, 5};
    String urlHost;
    int honglu = 1;
    String hong1, huang1, lv1, hong2, huang2, lv2, hong3, huang3, lv3, hong4, huang4, lv4, hong5, huang5, lv5;
    Button button;
    CheckBox check;
    int arraylu[], arrayhuang[], arrayhong[];
    private Button buttonCuo;
    private LinearLayout Ralativeee;
    private UrlBean urlBean;
    private View relativeLayout;
    private String[] name = {"路口", "红灯时长", "黄灯时长", "绿灯时长", "操作项", " 设置 "};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lukou_fragment_4, container, false);


        return view;
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Ralativeee = (LinearLayout) getActivity().findViewById(R.id.Ralativeerrssrsess);
        relativeLayout = LayoutInflater.from(getActivity()).inflate(R.layout.table, null);
        urlBean = Util.loadSetting(getContext());
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
        getAllCarrrValue(urlHost, object);


    }
    private void getAllCarrrValue(String urlHostAction, JSONObject params) {
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
                        jiashuju();
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
        JSONObject object = new JSONObject();
        try {
            object.put("TrafficLightId", honglu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("dasds", String.valueOf(object));
        getAllCarrrValue(urlHost, object);
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

    private void jiashuju() {
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
            check = (CheckBox) relativeLayout.findViewById(R.id.checkbox);
            check.isChecked();
            button = (Button) relativeLayout.findViewById(R.id.btn_shezhi);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialoge();
                    Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
                }
            });
            button.setText(" 设置 ");
            Ralativeee.addView(relativeLayout);
        }
    }
}
