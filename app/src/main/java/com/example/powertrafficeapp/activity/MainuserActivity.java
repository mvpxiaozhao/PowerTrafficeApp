package com.example.powertrafficeapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.powertrafficeapp.R;
import com.example.powertrafficeapp.fragment.FragmentHome;
import com.example.powertrafficeapp.fragment.Fragment_2;
import com.example.powertrafficeapp.fragment.Fragment_5;
import com.example.powertrafficeapp.fragment.Fragment_6;
import com.example.powertrafficeapp.fragment.Fragment_8;
import com.example.powertrafficeapp.fragment.Fragment_9;

import java.util.ArrayList;
import java.util.HashMap;

public class MainuserActivity extends AppCompatActivity {
    ArrayList<HashMap<String, Object>> actionItems;
    SimpleAdapter actionAdapter;
    TextView tV_title;
    ImageView iVSliding;
    ImageView ivHome;
    private SlidingPaneLayout slidepanel;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slidepanel = (SlidingPaneLayout) findViewById(R.id.slidingPL);
        listView = (ListView) findViewById(R.id.listView1);
        ivHome = (ImageView) findViewById(R.id.imageView_home);
        ivHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setHome();
            }
        });
        iVSliding = (ImageView) findViewById(R.id.imageView_Sliding);
        tV_title = (TextView) findViewById(R.id.tv_title);
        tV_title.setText(getString(R.string.app_name));
        iVSliding.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (slidepanel.isOpen()) {
                    slidepanel.closePane();
                } else {
                    slidepanel.openPane();
                }
            }
        });
        final String[] actionTexts = new String[]{
                getString(R.string.res_left_zhanghuguanli),
                getString(R.string.res_left_zhanghuguanli5),
                getString(R.string.res_left_honglvdengguanli6),
                getString(R.string.res_left_zhanghuguanli8),
                getString(R.string.res_left_chuanyi),
                getString(R.string.res_left_exit)
        };
        int[] actionImages = new int[]{
                R.drawable.btn_l_arrows,
                R.drawable.btn_l_arrows,
                R.drawable.btn_l_arrows,
                R.drawable.btn_l_arrows,
                R.drawable.btn_l_arrows,
                R.drawable.btn_l_arrows,
        };
        actionItems = new ArrayList<HashMap<String, Object>>();
        actionAdapter = new SimpleAdapter(getApplicationContext(), actionItems, R.layout.left_list_fragment_item,
                new String[]{"action_icon", "action_name"},
                new int[]{R.id.recharge_method_icon, R.id.recharge_method_name});

        for (int i = 0; i < actionImages.length; ++i) {
            HashMap<String, Object> item1 = new HashMap<String, Object>();
            item1.put("action_icon", actionImages[i]);
            item1.put("action_name", actionTexts[i]);
            actionItems.add(item1);
        }
        listView.setAdapter(actionAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                switch (arg2) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_2()).commit();
//                        当api>=14时，使用getFragmentManager()，有时候使用Fragment的时候为了兼容低版本，会引入android-support-v4.jar支持包，这个时候，需要使用getSupportFragmentManager()。
                        tV_title.setText(actionTexts[arg2]);
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_5()).commit();
                        tV_title.setText(actionTexts[arg2]);
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_6()).commit();
                        tV_title.setText(actionTexts[arg2]);
                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_8()).commit();
                        tV_title.setText(actionTexts[arg2]);
                        break;
                    case 4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_9()).commit();
                        break;
                    case 5:
                        exitAppDialog();
                        break;
                    default:
                        break;
                }
            }
        });
        //  fragmentManager = getFragmentManager();
        setHome();
    }

    public void setHome() {
        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new FragmentHome()).commit();
        tV_title.setText(R.string.app_name);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        // 按下键盘上返回按钮
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitAppDialog();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    public void exitAppDialog() {
        new AlertDialog.Builder(this)
                // .setIcon(android.R.drawable.ic_menu_info_detailsp)
                .setTitle("提示").setMessage("你确定要退出吗").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener()

        {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        }).show();

    }
}
