package com.example.powertrafficeapp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.powertrafficeapp.R;
import com.example.powertrafficeapp.util.UrlBean;
import com.example.powertrafficeapp.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by dell on 2017/07/30.
 */

public class Fragment_5 extends Fragment {
    String Httpurl = "";
    String Httpurllu = "";
    UrlBean urlBean;
    int dff1;
    int dff2;
    int dff3;
    int dff4;
    int dff5;
    int dff6;
    int dff7;
    int tiui = 1;
    private ImageView imageView6;
    private TextView fragment5ZhongZhong;
    private TextView fragment5Zuo;
    private TextView fragment5Zhong3;
    private TextView fragment5Zhong2;
    private TextView fragment5Shang;
    private TextView fragment5Zhong1;
    private TextView fragment5You;
    private TextView fragment5Xia;
    private TextView zhong1Xia;
    private TextView zhong2Xia;
    private Handler handler = new Handler();
    private TextView textViewF5Date;
    private TextView textViewF5Week;
    private TextView textViewF5Wendu;
    private TextView textViewF5Shidu;
    private TextView textViewF5Pm2;
    private Button imageButton;
    private ImageView image;
    private Runnable runnable = new Runnable();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout05, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragment5ZhongZhong = (TextView) getActivity().findViewById(R.id.fragment5_zhong_zhong);
        fragment5ZhongZhong.setBackgroundResource(R.color.color1);
        imageButton = (Button) getActivity().findViewById(R.id.imageButton_sguxin);
        fragment5Zuo = (TextView) getActivity().findViewById(R.id.fragment5_zuo);
        fragment5Zhong3 = (TextView) getActivity().findViewById(R.id.fragment5_zhong3);
        fragment5Zhong2 = (TextView) getActivity().findViewById(R.id.fragment5_zhong2);
        fragment5Shang = (TextView) getActivity().findViewById(R.id.fragment5_shang);
        fragment5Zhong1 = (TextView) getActivity().findViewById(R.id.fragment5_zhong1);
        fragment5You = (TextView) getActivity().findViewById(R.id.fragment5_you);
        fragment5Xia = (TextView) getActivity().findViewById(R.id.fragment5_xia);
        textViewF5Date = (TextView) getActivity().findViewById(R.id.textView_f5_date);
        textViewF5Week = (TextView) getActivity().findViewById(R.id.textView_f5_week);
        textViewF5Wendu = (TextView) getActivity().findViewById(R.id.textView_f5_wendu);
        textViewF5Shidu = (TextView) getActivity().findViewById(R.id.textView_f5_shidu);
        textViewF5Pm2 = (TextView) getActivity().findViewById(R.id.textView_f5_pm2);
        zhong1Xia = (TextView) getActivity().findViewById(R.id.zhong_1_xia);
        zhong2Xia = (TextView) getActivity().findViewById(R.id.zhong_2_xia);
        image = (ImageView) getActivity().findViewById(R.id.imageView7);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(runnable);
                String sd = "";
                getAllCarValue(Httpurl, sd);
            }
        });
        long time = System.currentTimeMillis();
        SimpleDateFormat format;
        Date date = new Date(time);
        format = new SimpleDateFormat("yyyy/MM/dd");
        textViewF5Date.setText(format.format(date));
        format = new SimpleDateFormat("EEEE");
        textViewF5Week.setText(format.format(date));
        urlBean = Util.loadSetting("httpbao", "http", "port", getContext());
        Httpurl = "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/type/jason/action/GetAllSense.do";
        Httpurllu = "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/type/jason/action/GetRoadStatus.do";
        String sd = "";
        Log.i("adasdasd", Httpurl);
        getAllCarValue(Httpurl, sd);
        handler.postDelayed(runnable, 4000);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(runnable);
    }
    private void getAllCarValue(String urlHostAction, String params) {
        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlHostAction, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stu
                Log.i("TAG volley", response.toString());
                String pm2 = response.optString("serverinfo");
                try {
                    JSONObject sdf = new JSONObject(pm2);
                    Log.i("TAG volleydasdas", sdf.toString());
                    String pm = sdf.getString("pm2.5");
                    String co = sdf.getString("co2");
                    String humidity = sdf.getString("humidity");
                    String LightIntensity = sdf.getString("LightIntensity");
                    String temperature = sdf.getString("temperature");
                    Toast.makeText(getActivity(), "pm2.5是:" + pm + "co2:" + co + "光强度：" + LightIntensity + "温度:" + temperature, Toast.LENGTH_SHORT).show();
                    textViewF5Wendu.setText("温度" + temperature + "℃");
                    textViewF5Shidu.setText("相对湿度" + humidity + "%");
                    textViewF5Pm2.setText("pm2.5" + pm + "ug/m3");
                    handler.postDelayed(runnable, 1000);
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

    private void getAllCarrrValue(String urlHostAction, JSONObject params) {
        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlHostAction, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stu
                Log.i("xfsdfdsf", response.toString());
                if (tiui == 1) {
                    try {
                        String fgh = response.getString("serverinfo");
                        Log.i("xfsdfdsf", fgh);
                        JSONObject dfdfdfg = new JSONObject(fgh);
                        dff1 = dfdfdfg.getInt("Status");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dsfafaf();
                } else if (tiui == 2) {
                    try {
                        String fgh = response.getString("serverinfo");
                        Log.i("xfsdfdsf", fgh);
                        JSONObject dfdfdfg = new JSONObject(fgh);
                        dff2 = dfdfdfg.getInt("Status");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dsfafaf();
                } else if (tiui == 3) {
                    try {
                        String fgh = response.getString("serverinfo");
                        Log.i("xfsdfdsf", fgh);
                        JSONObject dfdfdfg = new JSONObject(fgh);
                        dff3 = dfdfdfg.getInt("Status");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dsfafaf();
                } else if (tiui == 4) {
                    try {
                        String fgh = response.getString("serverinfo");
                        Log.i("xfsdfdsf", fgh);
                        JSONObject dfdfdfg = new JSONObject(fgh);
                        dff4 = dfdfdfg.getInt("Status");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dsfafaf();
                } else if (tiui == 5) {
                    try {
                        String fgh = response.getString("serverinfo");
                        Log.i("xfsdfdsf", fgh);
                        JSONObject dfdfdfg = new JSONObject(fgh);
                        dff5 = dfdfdfg.getInt("Status");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dsfafaf();
                } else if (tiui == 6) {
                    try {
                        String fgh = response.getString("serverinfo");
                        Log.i("xfsdfdsf", fgh);
                        JSONObject dfdfdfg = new JSONObject(fgh);
                        dff6 = dfdfdfg.getInt("Status");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dsfafaf();
                } else if (tiui == 7) {
                    try {
                        String fgh = response.getString("serverinfo");
                        Log.i("xfsdfdsf", fgh);
                        JSONObject dfdfdfg = new JSONObject(fgh);
                        dff7 = dfdfdfg.getInt("Status");
                        if (dff7 == 0) {
                            fragment5Shang.setBackgroundResource(R.drawable.f5_lelf1);
                            fragment5Zuo.setBackgroundResource(R.color.color1);
                            fragment5Xia.setBackgroundResource(R.drawable.f5_right1);
                            fragment5Shang.setText("环城快速路");
                            fragment5Zuo.setText("环城快速路");
                            fragment5Xia.setText("环城快速路");
                        } else if (dff7 == 1) {
                            fragment5Shang.setBackgroundResource(R.drawable.f5_lelf2);
                            fragment5Zuo.setBackgroundResource(R.color.color2);
                            fragment5Xia.setBackgroundResource(R.drawable.f5_right2);
                            fragment5Shang.setText("环城快速路");
                            fragment5Zuo.setText("环城快速路");
                            fragment5Xia.setText("环城快速路");
                        } else if (dff7 == 2) {
                            fragment5Shang.setBackgroundResource(R.drawable.f5_lelf3);
                            fragment5Zuo.setBackgroundResource(R.color.color3);
                            fragment5Xia.setBackgroundResource(R.drawable.f5_right3);
                            fragment5Shang.setText("环城快速路");
                            fragment5Zuo.setText("环城快速路");
                            fragment5Xia.setText("环城快速路");
                        } else if (dff7 == 3) {
                            fragment5Shang.setBackgroundResource(R.drawable.f5_lelf4);
                            fragment5Zuo.setBackgroundResource(R.color.color4);
                            fragment5Xia.setBackgroundResource(R.drawable.f5_right4);
                            fragment5Shang.setText("环城快速路");
                            fragment5Zuo.setText("环城快速路");
                            fragment5Xia.setText("环城快速路");
                        } else if (dff7 == 4) {
                            fragment5Shang.setBackgroundResource(R.drawable.f5_lelf5);
                            fragment5Zuo.setBackgroundResource(R.color.color5);
                            fragment5Xia.setBackgroundResource(R.drawable.f5_right5);
                            fragment5Shang.setText("环城快速路");
                            fragment5Zuo.setText("环城快速路");
                            fragment5Xia.setText("环城快速路");
                        }
                        if (dff7 == 0) {
                            fragment5Zhong3.setBackgroundResource(R.color.color1);
                            fragment5Zhong3.setText("停车场");
                        } else if (dff7 == 1) {
                            fragment5Zhong3.setBackgroundResource(R.color.color2);
                            fragment5Zhong3.setText("停车场");
                        } else if (dff7 == 2) {
                            fragment5Zhong3.setBackgroundResource(R.color.color3);
                            fragment5Zhong3.setText("停车场");
                        } else if (dff7 == 3) {
                            fragment5Zhong3.setBackgroundResource(R.color.color4);
                            fragment5Zhong3.setText("停车场");
                        } else if (dff7 == 4) {
                            fragment5Zhong3.setBackgroundResource(R.color.color5);
                            fragment5Zhong3.setText("停车场");
                        }
                        if (dff1 == 0) {
                            fragment5Zhong1.setBackgroundResource(R.color.color1);
                            fragment5Zhong1.setText("学院路");
                        } else if (dff1 == 1) {
                            fragment5Zhong1.setBackgroundResource(R.color.color2);
                            fragment5Zhong1.setText("学院路");
                        } else if (dff1 == 2) {
                            fragment5Zhong1.setBackgroundResource(R.color.color3);
                            fragment5Zhong1.setText("学院路");
                        } else if (dff1 == 3) {
                            fragment5Zhong1.setBackgroundResource(R.color.color4);
                            fragment5Zhong1.setText("学院路");
                        } else if (dff1 == 4) {
                            fragment5Zhong1.setBackgroundResource(R.color.color5);
                            fragment5Zhong1.setText("学院路");
                        }
                        if (dff2 == 0) {
                            fragment5Zhong2.setBackgroundResource(R.color.color1);
                            fragment5Zhong2.setText("联想路");

                        } else if (dff2 == 1) {
                            fragment5Zhong2.setBackgroundResource(R.color.color2);
                            fragment5Zhong2.setText("联想路");
                        } else if (dff2 == 2) {
                            fragment5Zhong2.setBackgroundResource(R.color.color3);
                            fragment5Zhong2.setText("联想路");
                        } else if (dff2 == 3) {
                            fragment5Zhong2.setBackgroundResource(R.color.color4);
                            fragment5Zhong2.setText("联想路");
                        } else if (dff2 == 4) {
                            fragment5Zhong2.setBackgroundResource(R.color.color5);
                            fragment5Zhong2.setText("联想路");
                        }
                        if (dff4 == 0) {
                            zhong1Xia.setBackgroundResource(R.color.color1);
                            zhong1Xia.setText("幸福路");
                        } else if (dff4 == 1) {
                            zhong1Xia.setBackgroundResource(R.color.color2);
                            zhong1Xia.setText("幸福路");
                        } else if (dff4 == 2) {
                            zhong1Xia.setBackgroundResource(R.color.color3);
                            zhong1Xia.setText("幸福路");
                        } else if (dff4 == 3) {
                            zhong1Xia.setBackgroundResource(R.color.color4);
                            zhong1Xia.setText("幸福路");
                        } else if (dff4 == 4) {
                            zhong1Xia.setBackgroundResource(R.color.color5);
                            zhong1Xia.setText("幸福路");
                        }
                        if (dff3 == 0) {
                            zhong2Xia.setBackgroundResource(R.color.color1);
                            zhong2Xia.setText("医院路");
                        } else if (dff3 == 1) {
                            zhong2Xia.setBackgroundResource(R.color.color2);
                            zhong2Xia.setText("医院路");
                        } else if (dff3 == 2) {
                            zhong2Xia.setBackgroundResource(R.color.color3);
                            zhong2Xia.setText("医院路");
                        } else if (dff3 == 3) {
                            zhong2Xia.setBackgroundResource(R.color.color4);
                            zhong2Xia.setText("医院路");
                        } else if (dff3 == 4) {
                            zhong2Xia.setBackgroundResource(R.color.color5);
                            zhong2Xia.setText("医院路");
                        }
                        if (dff6 == 0) {
                            fragment5You.setBackgroundResource(R.color.color1);
                            fragment5You.setText("环城高速");
                        } else if (dff6 == 1) {
                            fragment5You.setBackgroundResource(R.color.color2);
                            fragment5You.setText("环城高速");
                        } else if (dff6 == 2) {
                            fragment5You.setBackgroundResource(R.color.color3);
                            fragment5You.setText("环城高速");
                        } else if (dff6 == 3) {
                            fragment5You.setBackgroundResource(R.color.color4);
                            fragment5You.setText("环城高速");
                        } else if (dff6 == 4) {
                            fragment5You.setBackgroundResource(R.color.color5);
                            fragment5You.setText("环城高速");
                        }
                        handler.postDelayed(runnable, 4000);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }

//            fragment5Zuo = (TextView) getActivity().findViewById(R.id.fragment5_zuo);
//            fragment5Zhong3 = (TextView) getActivity().findViewById(R.id.fragment5_zhong3);
//            fragment5Zhong2 = (TextView) getActivity().findViewById(R.id.fragment5_zhong2);
//            fragment5Shang = (TextView) getActivity().findViewById(R.id.fragment5_shang);
//            fragment5Zhong1 = (TextView) getActivity().findViewById(R.id.fragment5_zhong1);
//            fragment5You = (TextView) getActivity().findViewById(R.id.fragment5_you);
//            fragment5Xia = (TextView) getActivity().findViewById(R.id.fragment5_xia);
//            zhong1Xia = (TextView) getActivity().findViewById(R.id.zhong_1_xia);
//            zhong2Xia = (TextView) getActivity().findViewById(R.id.zhong_2_xia);


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

    private void dsfafaf() {
        tiui++;
            JSONObject guyu = new JSONObject();
            try {
                guyu.put("RoadId", tiui);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getAllCarrrValue(Httpurllu, guyu);

    }
    class Runnable implements java.lang.Runnable {
        @Override
        public void run() {
            tiui = 1;
            JSONObject guyu = new JSONObject();
            try {
                guyu.put("RoadId", tiui);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("adasdasd", Httpurl + guyu);
            getAllCarrrValue(Httpurllu, guyu);
        }
    }
}
