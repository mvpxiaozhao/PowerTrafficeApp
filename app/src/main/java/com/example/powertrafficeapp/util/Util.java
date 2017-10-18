package com.example.powertrafficeapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Util {

    public static int timedd;

    public static void saveSetting(String Httpbao, String Http, String port, String ipUrl, String ipPort, Context context) {
        SharedPreferences spSettingSave = context.getSharedPreferences(Httpbao, MODE_PRIVATE);// 将需要记录的数据保存在setting.xml文件中
        SharedPreferences.Editor editor = spSettingSave.edit();
        editor.putString(Http, ipUrl);
        editor.putString(port, ipPort);
        editor.commit();
    }

    public static void saveSettingtime(String dasd, String eee, int ipPort, Context context) {
        SharedPreferences spSettingSave = context.getSharedPreferences(dasd, MODE_PRIVATE);// 将需要记录的数据保存在setting.xml文件中
        SharedPreferences.Editor editor = spSettingSave.edit();
        editor.putInt(eee, ipPort);
        editor.commit();
    }
    public static UrlBean loadSetting(String Httpbao, String Http, String port, Context context) {
        UrlBean urlBean = new UrlBean();
        SharedPreferences loadSettingLoad = context.getSharedPreferences(Httpbao, MODE_PRIVATE);
        urlBean.setUrl(loadSettingLoad.getString(Http, ""));
        urlBean.setPort(loadSettingLoad.getString(port, ""));
        return urlBean;
    }

    public static int loadSettingtime(String dasd, String eee, Context context) {
        SharedPreferences loadSettingLoad = context.getSharedPreferences(dasd, MODE_PRIVATE);
        timedd = loadSettingLoad.getInt(eee, 0);
        return timedd;
    }
}
