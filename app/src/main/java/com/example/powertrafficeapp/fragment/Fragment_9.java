package com.example.powertrafficeapp.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.powertrafficeapp.R;
import com.example.powertrafficeapp.activity.MainActivity;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by dell on 2017/07/30.
 */

public class Fragment_9 extends Fragment {
    private static final int NOTIFICATION_1 = 1;
    private Context context;
    private NotificationManager notificationManager;
    private Notification notification;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout09, container, false);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        //创建图片的Bitmap
        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        //设置图片，通知标题，发送时间，提示方式等属性
        Notification.Builder builder = new Notification.Builder(getActivity());
        Intent deleteIntentf = new Intent();
        deleteIntentf.setClass(context, MainActivity.class);
        PendingIntent deleteIntent = PendingIntent.getActivity(context, 0, deleteIntentf, 0);
        //标题
        builder.setContentTitle("余额不足提示")
                .setContentText("状态栏会显示一个通知栏的图标") //内容
                .setSubText("丰富你的程序，运用手机多媒体") //内容下面的一小段文字
                .setTicker("余额不足提示")   //收到信息后状态显示的文字信息
                .setWhen(System.currentTimeMillis()) //设置通知时间
                .setSmallIcon(R.mipmap.ic_launcher) //设置小图片
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE) //设置默认的三色灯与振动器
                .setAutoCancel(true)//设置点击后取消Notification
                .setContentIntent(deleteIntent); //设置pendingIntent
        notification = builder.build();
        notificationManager.notify(NOTIFICATION_1, notification);
    }
}