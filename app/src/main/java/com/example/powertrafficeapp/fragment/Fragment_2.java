/**
 *
 */
package com.example.powertrafficeapp.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fragment_2 extends Fragment {
    Listadpter sdf;
    int pus;
    int id = 1;
    String key1 = "zhao";
    String key2 = "hong";
    String key3 = "bin";
    String key4 = "hh";
    List<Map<String, Object>> groupData = new ArrayList<>();//大组成员
    List<List<Map<String, Object>>> childData = new ArrayList<>();//小组成员
    UrlBean urlBean;
    String urlHost;
    private ImageView mImageViewF2Tubiao;
    private TextView mTextViewBuy;
    private TextView mTextViewF2Time1;
    private TextView mTextViewF2Time;
    private TextView mTextView88;
    private TextView mTextViewd1;
    private TextView mTextViewd2;
    private TextView mTextViewd3;
    private TextView mTextViewd4;
    private Button mButton;
    private ExpandableListView mF2Expandable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout02, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImageViewF2Tubiao = (ImageView) getActivity().findViewById(R.id.imageView_F2_tubiao);
        mTextViewBuy = (TextView) getActivity().findViewById(R.id.textView_buy);
        mTextViewF2Time1 = (TextView) getActivity().findViewById(R.id.textView_F2_time1);
        mTextViewF2Time = (TextView) getActivity().findViewById(R.id.textView_F2_time_);
        mTextView88 = (TextView) getActivity().findViewById(R.id.textView88);
        mButton = (Button) getActivity().findViewById(R.id.button_F2);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setTitle("详情");
                dialog.getWindow().setContentView(R.layout.car_recharge);
                dialog.show();
            }
        });
        mTextViewd1 = (TextView) getActivity().findViewById(R.id.textView_D1);
        mTextViewd2 = (TextView) getActivity().findViewById(R.id.textView_D2);
        mTextViewd3 = (TextView) getActivity().findViewById(R.id.textView_D3);
        mTextViewd4 = (TextView) getActivity().findViewById(R.id.textView_D4);
        mF2Expandable = (ExpandableListView) getActivity().findViewById(R.id.F2_Expandable);

        urlBean = Util.loadSetting("httpbao", "http", "port", getContext());
        urlHost = "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/type/jason/action/GetBusStationInfo.do";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("BusStationId", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        pus = 1;
        getAllCarValue(urlHost, jsonObject);
    }
    private void getAllCarValue(String urlHostAction, JSONObject params) {
        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlHostAction, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stu
                String str = response.optString("serverinfo");
                if (pus == 1) {
                    try {
                        JSONArray sdf = new JSONArray(str);
                        JSONObject sdfg = sdf.getJSONObject(0);
                        String ddfgh = sdfg.getString("Distance");
                        Log.i("rrrr", ddfgh);
                        mTextViewd1.setText(ddfgh);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        JSONArray sdf = new JSONArray(str);
                        JSONObject sdfg = sdf.getJSONObject(1);
                        String ddfgh = sdfg.getString("Distance");
                        Log.i("rrrr", ddfgh);
                        mTextViewd2.setText(ddfgh);
                        car();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else if (pus == 2) {
                    try {
                        JSONArray sdf = new JSONArray(str);
                        JSONObject sdfg = sdf.getJSONObject(0);
                        String ddfgh = sdfg.getString("Distance");
                        Log.i("rrrr", ddfgh);
                        mTextViewd3.setText(ddfgh);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        JSONArray sdf = new JSONArray(str);
                        JSONObject sdfg = sdf.getJSONObject(1);
                        String ddfgh = sdfg.getString("Distance");
                        Log.i("rrrr", ddfgh);
                        mTextViewd4.setText(ddfgh);
                        drt();
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

    public void car() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("BusStationId", 2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        pus = 2;
        getAllCarValue(urlHost, jsonObject);
    }

    public void drt() {
        int d1 = Integer.parseInt(mTextViewd1.getText().toString());
        int d2 = Integer.parseInt(mTextViewd2.getText().toString());
        int d3 = Integer.parseInt(mTextViewd3.getText().toString());
        int d4 = Integer.parseInt(mTextViewd4.getText().toString());
        int dd;
        if (d1 > d2) {
            dd = d1;
            d1 = d2;
            d2 = dd;
        }
        float t1 = d1 / (20000 / 60);
        float t2 = d2 / (20000 / 60);
        float t3 = d3 / (20000 / 60);
        float t4 = d4 / (20000 / 60);
        Map<String, Object> gound = new HashMap<>();
        gound.put(key1, "中医院站");
        groupData.add(gound);
        List<Map<String, Object>> son = new ArrayList<>();
        if (d1 < d2) {
            Map<String, Object> goundl = new HashMap<>();
            goundl.put(key2, "1号（101）人");
            goundl.put(key3, t1 + "分钟");
            goundl.put(key4, "距离" + d1 + "m");
            son.add(goundl);
            goundl = new HashMap<>();
            goundl.put(key2, "2号（101）人");
            goundl.put(key3, t2 + "分钟");
            goundl.put(key4, "距离" + d2 + "m");
            son.add(goundl);
        } else {
            Map<String, Object> goundl = new HashMap<>();
            goundl.put(key2, "2号（101）人");
            goundl.put(key3, t2 + "分钟");
            goundl.put(key4, "距离" + d2 + "m");
            son.add(goundl);
            goundl = new HashMap<>();
            goundl.put(key2, "1号（101）人");
            goundl.put(key3, t1 + "分钟");
            goundl.put(key4, "距离" + d1 + "m");
            son.add(goundl);
        }
        childData.add(son);
        gound = new HashMap<>();
        gound.put(key1, "联想大厦站");
        groupData.add(gound);
        son = new ArrayList<>();
        if (d3 < d4) {
            Map<String, Object> goundl = new HashMap<>();
            goundl.put(key2, "1号（101）人");
            goundl.put(key3, t3 + "分钟");
            goundl.put(key4, "距离" + d3 + "m");
            son.add(goundl);
            goundl = new HashMap<>();
            goundl.put(key2, "2号（101）人");
            goundl.put(key3, t4 + "分钟");
            goundl.put(key4, "距离" + d4 + "m");
            son.add(goundl);

        } else {
            Map<String, Object> goundl = new HashMap<>();
            goundl.put(key2, "2号（101）人");
            goundl.put(key3, t4 + "分钟");
            goundl.put(key4, "距离" + d4 + "m");
            son.add(goundl);
            goundl = new HashMap<>();
            goundl.put(key2, "1号（101）人");
            goundl.put(key3, t3 + "分钟");
            goundl.put(key4, "距离" + d3 + "m");
            son.add(goundl);
        }
        childData.add(son);
        sdf = new Listadpter(getContext());
        mF2Expandable.setAdapter(sdf);
        mF2Expandable.setGroupIndicator(null);//不设置大组指示器图标，因为我们自定义设置了
        mF2Expandable.setDivider(null);//设置图片可拉伸的
    }
    class Listadpter extends BaseExpandableListAdapter {
        Context context;

        public Listadpter(Context context) {
            this.context = context;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                view = inflater.inflate(R.layout.list_f2_groud, null);
//			getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
            }
            ImageView imageViewListGount = (ImageView) view.findViewById(R.id.imageView_List_gount);
            TextView textViewListGount = (TextView) view.findViewById(R.id.textView_List_gount);
            textViewListGount.setText(getGroup(i).toString());
            imageViewListGount.setImageResource(R.drawable.jia);
            if (b) {
                imageViewListGount.setImageResource(R.drawable.jian);

            } else {
                imageViewListGount.setImageResource(R.drawable.jia);
            }
            return view;
        }

        //			getGroupView
        @Override
        public Object getGroup(int i) {
            return groupData.get(i);
        }

        @Override
        public int getGroupCount() {
            return groupData.size();
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public int getChildrenCount(int i) {
            return childData.get(i).size();
        }

        @Override
        public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                view = inflater.inflate(R.layout.list_f2_son, null);
            }
            ImageView imageView4 = (ImageView) view.findViewById(R.id.imageView4);
            TextView textViewListSon1 = (TextView) view.findViewById(R.id.textView_list_son1);
            TextView textViewListSon2 = (TextView) view.findViewById(R.id.textView_list_son2);
            TextView textViewListSon3 = (TextView) view.findViewById(R.id.textView_list_son3);
            textViewListSon1.setText(childData.get(i).get(i1).get(key2).toString());
            textViewListSon1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (i) {
                        case 0:
                            switch (i1) {
                                case 0:
                                    Toast.makeText(getActivity(), "1,1", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                    Toast.makeText(getActivity(), "1,2", Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    Toast.makeText(getActivity(), "1,3", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            break;
                        case 1:
                            switch (i1) {
                                case 0:
                                    Toast.makeText(getActivity(), "2,1", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                    Toast.makeText(getActivity(), "2,2", Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    Toast.makeText(getActivity(), "2,3", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            break;
                    }
                }
            });
            textViewListSon2.setText(childData.get(i).get(i1).get(key3).toString());
            textViewListSon3.setText(childData.get(i).get(i1).get(key4).toString());
            imageView4.setImageResource(R.drawable.home);
            return view;
        }

        @Override
        public Object getChild(int i, int i1) {
            return childData.get(i).get(i1);
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }
}
