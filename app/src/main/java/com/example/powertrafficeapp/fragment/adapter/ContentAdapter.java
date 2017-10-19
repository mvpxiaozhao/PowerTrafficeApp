package com.example.powertrafficeapp.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.powertrafficeapp.R;

import java.util.ArrayList;
import java.util.Map;


/**
 * 项目名称：ITS02
 * 类描述：
 * 创建人：zhaowei
 * 创建时间：2017/4/20 14:40
 * 修改人：Administrator
 * 修改时间：2017/4/20 14:40
 * 修改备注：
 */
public class ContentAdapter extends BaseAdapter {//自定义适配器

    Check dd;
    int dddd = 0;
    Context context;
    private ArrayList<Map<String, Object>> mListItem;
    private LayoutInflater mInflater;
    private IClick mListener;

    public ContentAdapter(Context context, ArrayList<Map<String, Object>> contentList, IClick listener, Check lfistener, int shu) {
        mListItem = contentList;
        mInflater = LayoutInflater.from(context);
        mListener = listener;
        dddd = shu;
        dd = lfistener;
    }

    @Override
    public int getCount() {
        return mListItem.size();
    }

    @Override
    public Object getItem(int i) {
        return mListItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            view = mInflater.inflate(R.layout.f1_listitem_carinfro_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.carinfoTextViewNumber = (TextView) view.findViewById(R.id.carinfo_textView_number);
            viewHolder.carinfoImageviewLogo = (ImageView) view.findViewById(R.id.carinfo_imageview_logo);
            viewHolder.carinfoTextviewPlatenumber = (TextView) view.findViewById(R.id.carinfo_textview_platenumber);
            viewHolder.carinfoTextviewOwner = (TextView) view.findViewById(R.id.carinfo_textview_owner);
            viewHolder.carinfoTextViewBalance = (TextView) view.findViewById(R.id.carinfo_textView_balance);
            viewHolder.carinfoCheckBoxChongzhi = (CheckBox) view.findViewById(R.id.carinfo_checkBox_chongzhi);
            viewHolder.carinfoButtonChognzhi = (Button) view.findViewById(R.id.carinfo_button_chognzhi);
            viewHolder.mline_f1_moban = (LinearLayout) view.findViewById(R.id.line_f1_moban);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.carinfoTextViewNumber.setText(mListItem.get(i).get("number").toString());
        viewHolder.carinfoImageviewLogo.setBackgroundResource((Integer) mListItem.get(i).get("image"));
        viewHolder.carinfoTextviewPlatenumber.setText(mListItem.get(i).get("platenumber").toString());
        viewHolder.carinfoTextviewOwner.setText(mListItem.get(i).get("owner").toString());
        viewHolder.carinfoTextViewBalance.setText("余额" + mListItem.get(i).get("balance").toString() + "元");
        int ddo = (int) mListItem.get(i).get("balance");
        if (ddo <= dddd) {
            viewHolder.mline_f1_moban.setBackgroundResource(R.color.color8);
        }
        viewHolder.carinfoButtonChognzhi.setOnClickListener(mListener);
        viewHolder.carinfoCheckBoxChongzhi.setOnCheckedChangeListener(dd);
        viewHolder.carinfoCheckBoxChongzhi.setTag(i);
        viewHolder.carinfoButtonChognzhi.setTag(i);
        return view;
    }

    public class ViewHolder {
        TextView carinfoTextViewNumber;
        ImageView carinfoImageviewLogo;
        TextView carinfoTextviewPlatenumber;
        TextView carinfoTextviewOwner;
        TextView carinfoTextViewBalance;
        CheckBox carinfoCheckBoxChongzhi;
        Button carinfoButtonChognzhi;
        LinearLayout mline_f1_moban;
    }
}
