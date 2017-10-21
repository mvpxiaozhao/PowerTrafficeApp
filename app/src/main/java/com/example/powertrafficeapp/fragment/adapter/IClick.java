package com.example.powertrafficeapp.fragment.adapter;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * 项目名称：ITS02
 * 类描述：
 * 创建人：zhaowei
 * 创建时间：2017/4/20 14:34
 * 修改人：Administrator
 * 修改时间：2017/4/20 14:34
 * 修改备注：
 */
public abstract class IClick implements OnClickListener {
    public abstract void listViewItemClick(int position, View v);

    @Override
    public void onClick(View v) {
        listViewItemClick((Integer) v.getTag(), v);
    }
//nanaisjci
}
