/**
 *
 */
package com.example.powertrafficeapp.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.powertrafficeapp.R;
import com.example.powertrafficeapp.fragment.adapter.Check;
import com.example.powertrafficeapp.fragment.adapter.ContentAdapter;
import com.example.powertrafficeapp.fragment.adapter.IClick;
import com.example.powertrafficeapp.util.Carinformation;
import com.example.powertrafficeapp.util.DBAdapter;
import com.example.powertrafficeapp.util.UrlBean;
import com.example.powertrafficeapp.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Fragment_1 extends Fragment implements View.OnClickListener {
    DBAdapter adpter;
    android.support.v4.app.FragmentManager fm;
    TextView textView_chepaihae;
    boolean che1 = false;
    boolean che2 = false;
    boolean che3 = false;
    boolean che4 = false;
    String chepaihao;
    //车辆数量
    int carCount = 4;
    //当前车辆号
    int carCurrent = 1;
    //判断是充值还是查询
    int netFlag = 0;
    ArrayList<Map<String, Object>> arrayListItem;
    private String urlHost, urlHostAction;
    private UrlBean urlBean;
    private Button buttonPlcz;
    private Button buttonCzjl;
    private ListView listCarInfo;
    private String carname[] = {"张三", "李四", "王五", "赵六"};
    private String[] netAction = {"transportservice/type/jason/action/GetCarAccountBalance.do", "transportservice/type/jason/action/SetCarAccountRecharge.do"};
    private ContentAdapter contentAdapter;
    private Check dd = new Check() {
        @Override
        public void listViewIddddtemClick(int position, boolean b) {
            Log.i("afssdf", String.valueOf(b));
            Log.i("afssdf", String.valueOf(position));
            switch (position + 1) {
                case 1:
                    che1 = b;
                    break;
                case 2:
                    che2 = b;
                    break;
                case 3:
                    che3 = b;
                    break;
                case 4:
                    che4 = b;
                    break;
            }
        }
    };
    /*** 实现类，响应按钮点击事件*/
    private IClick mLitener = new IClick() {
        @Override
        public void listViewItemClick(int position, View v) {
            chepaihao = "鲁Q12345" + (position + 1);
            reChange(position + 1);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout01, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        urlBean = Util.loadSetting("httpbao", "http", "port", getContext());
        urlHost = "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/";
        buttonPlcz = (Button) getActivity().findViewById(R.id.button_plcz);
        buttonCzjl = (Button) getActivity().findViewById(R.id.button_czjl);
        listCarInfo = (ListView) getActivity().findViewById(R.id.list_car_info);
        adpter = new DBAdapter(getActivity());
        adpter.openDB();
        carCurrent = 1;
        arrayListItem = new ArrayList<Map<String, Object>>();
        urlHostAction = urlHost + netAction[0];
        JSONObject strJson = new JSONObject();
        try {
            strJson.put("CarId", carCurrent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        netFlag = 0;
        Log.i("asasdasd", urlHostAction + strJson);
        getAllCarValue(urlHostAction, strJson);
        buttonPlcz.setOnClickListener(this);
        buttonCzjl.setOnClickListener(this);
    }

    private void getAllCarValue(String urlHostAction, JSONObject params) {
        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlHostAction, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stu
                Log.i("gdfgdfg", response.toString());
                String str = response.toString();
                if (netFlag == 0) {
                    parseBalanceData(str);
                } else if (netFlag == 1) {
                    fm = getActivity().getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.maincontent, new Fragment_1()).commit();
                } else if (netFlag == 3) {
                    Toast.makeText(getContext(), "充值成功", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
                Log.i("gdfgdfg", error.toString());

            }
        });
        mQueue.add(jsonObjectRequest);
    }

    /**
     * @param balanceData
     */
    private void parseBalanceData(String balanceData) {              //Json解析
        try {
            JSONObject jsonObj = new JSONObject(balanceData);
            String wer = jsonObj.optString("serverinfo");
            JSONObject dfg = new JSONObject(wer);
            int sdf = dfg.getInt("Balance");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("number", carCurrent);
            map.put("image", R.mipmap.ic_launcher); //图片ICON                           //图片ICON
            map.put("platenumber", "鲁Q12345" + carCurrent);
            map.put("owner", carname[carCurrent - 1]);
            map.put("balance", sdf);    //余额
            map.put("chongzhi", false);      //车号
            arrayListItem.add(map);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.v("zms", "e:" + e.toString());
            e.printStackTrace();
        }
            carCurrent = carCurrent + 1;
            if (carCurrent <= carCount) {
                JSONObject strJson = new JSONObject();
                try {
                    strJson.put("CarId", carCurrent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                netFlag = 0;
                getAllCarValue(urlHostAction, strJson);
            } else {
                int ddd = Util.loadSettingtime("chabao", "cha", getActivity());
                contentAdapter = new ContentAdapter(getContext(), arrayListItem, mLitener, dd, ddd);
                listCarInfo.setAdapter(contentAdapter);

            }

    }

    public void reChange(final int position) {
        final Dialog rechargeDialog = new Dialog(getActivity());
        rechargeDialog.setTitle("充值窗口");
        rechargeDialog.getWindow().setContentView(R.layout.car_recharge);
        final EditText edit_car_recharge = (EditText) rechargeDialog.getWindow().findViewById(R.id.car_recharge);
        textView_chepaihae = (TextView) rechargeDialog.getWindow().findViewById(R.id.textView_chepaihao);
        textView_chepaihae.setText(chepaihao);
        Button save = (Button) rechargeDialog.getWindow().findViewById(R.id.save);
        Button cancel = (Button) rechargeDialog.getWindow().findViewById(R.id.cancel);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String carMoney = edit_car_recharge.getText().toString();
                if (carMoney.equals("")) {
                    Toast.makeText(getActivity(), "输入金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Integer.parseInt(carMoney) > 999) {
                    Toast.makeText(getActivity(), "不能大于999", Toast.LENGTH_SHORT).show();
                    return;
                }
                urlHostAction = urlHost + netAction[1];
                JSONObject strJson = new JSONObject();
                try {
                    strJson.put("CarId", position);
                    strJson.put("Money", Integer.parseInt(edit_car_recharge.getText().toString()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String name = carname[position - 1];
                long time = System.currentTimeMillis();
                Date date = new Date(time);
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 EEEE");
                format = new SimpleDateFormat("MM月dd日HH时mm分ss秒");
                String timee = format.format(date);
                String carner = "鲁Q12345";
                Carinformation people = new Carinformation(name, carMoney, timee, carner);
                adpter.Insert(people);
                Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();
                netFlag = 1;
                getAllCarValue(urlHostAction, strJson);
                rechargeDialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                rechargeDialog.dismiss();
            }
        });
        rechargeDialog.show();
    }

    public void Sql(int idd, int Money) {
        JSONObject strJson = new JSONObject();
        try {
            strJson.put("CarId", idd);
            strJson.put("Money", Money);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String name = carname[idd - 1];
        long time = System.currentTimeMillis();
        System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日HH时mm分ss秒");
        String timee = format.format(date);
        String carner = "鲁Q12345" + idd;
        String Moneii = String.valueOf(Money);
        Carinformation people = new Carinformation(name, Moneii, timee, carner);
        adpter.Insert(people);
        netFlag = 3;
        getAllCarValue(urlHostAction, strJson);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_plcz:
                final Dialog rechargeDialog = new Dialog(getActivity());
                rechargeDialog.show();
                rechargeDialog.setTitle("充值窗口");
                rechargeDialog.getWindow().setContentView(R.layout.car_recharge);
                textView_chepaihae = (TextView) rechargeDialog.getWindow().findViewById(R.id.textView_chepaihao);
                textView_chepaihae.setText("全部");
                final EditText edit_car_recharge = (EditText) rechargeDialog.getWindow().findViewById(R.id.car_recharge);
                Button save = (Button) rechargeDialog.getWindow().findViewById(R.id.save);
                final Button cancel = (Button) rechargeDialog.getWindow().findViewById(R.id.cancel);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        String carMoney = edit_car_recharge.getText().toString().trim();
                        if (carMoney.equals("")) {
                            Toast.makeText(getActivity(), "输入金额", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (Integer.parseInt(carMoney) > 999) {
                            Toast.makeText(getActivity(), "不能大于999", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        urlHostAction = urlHost + netAction[1];
                        int ddd = Integer.parseInt(edit_car_recharge.getText().toString());
                        if (che1 == true) {
                            Sql(1, ddd);
                        }
                        if (che2 == true) {
                            Sql(2, ddd);
                        }
                        if (che3 == true) {
                            Sql(3, ddd);
                        }
                        if (che4 == true) {
                            Sql(4, ddd);

                        }
                        rechargeDialog.dismiss();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                fm = getActivity().getSupportFragmentManager();
                                fm.beginTransaction().replace(R.id.maincontent, new Fragment_1()).commit();
                            }
                        }, 2000);
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        rechargeDialog.dismiss();
                    }
                });
                rechargeDialog.show();
                break;
            case R.id.button_czjl:
                break;
        }
    }
}
