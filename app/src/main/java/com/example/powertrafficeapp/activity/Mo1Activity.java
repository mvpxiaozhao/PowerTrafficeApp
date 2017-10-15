
package com.example.powertrafficeapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.powertrafficeapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Mo1Activity extends AppCompatActivity {
    private static final String GROUP_TEXT = "group_text";//大组成员Map的key
    private static final String CHILD_TEXT1 = "child_text1";//小组成员Map的第一个key
    private static final String CHILD_TEXT2 = "child_text2";//小组成员Map的第二个key
    List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();//大组成员
    List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();//小组成员
    ExAdapter adapter;
    ExpandableListView exList;//可扩展的List
    
    String ff="fefiurif";
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mo1);
        //为大小组中添加数据
        for (int i = 1; i < 3; i++) {
            Map<String, String> curGroupMap = new HashMap<String, String>();
            curGroupMap.put(GROUP_TEXT, "第" + i + "大组");
            groupData.add(curGroupMap);
            List<Map<String, String>> children = new ArrayList<Map<String, String>>();
            for (int j = 1; j < 6; j++) {
                Map<String, String> curChildMap = new HashMap<String, String>();
                curChildMap.put(CHILD_TEXT1, "第" + j + "小组");
                curChildMap.put(CHILD_TEXT2, "第" + j + "小组签名");
                children.add(curChildMap);
            }
            childData.add(children);
        }
        adapter = new ExAdapter(Mo1Activity.this);
        exList = (ExpandableListView) findViewById(R.id.list);
        exList.setAdapter(adapter);
        exList.setGroupIndicator(null);//不设置大组指示器图标，因为我们自定义设置了
        exList.setDivider(null);//设置图片可拉伸的
    }

    //关键代码是这个可扩展的listView适配器
    class ExAdapter extends BaseExpandableListAdapter {
        Context context;

        public ExAdapter(Context context) {
            this.context = context;
        }

        //            //大组适配  //得到大组成员的view
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.member_listview, null);
            }
            TextView title = (TextView) convertView.findViewById(R.id.content_001);
            title.setText(getGroup(groupPosition).toString());//设置大组成员名称
            ImageView image = (ImageView) convertView.findViewById(R.id.tubiao);//是否展开大组的箭头图标
            if (isExpanded)//大组展开时
                image.setBackgroundResource(R.drawable.jian);
            else//大组合并时
                image.setBackgroundResource(R.drawable.jia);
            return convertView;
        }

        //得到大组成员的id
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        //得到大组成员名称
        public Object getGroup(int groupPosition) {
            return groupData.get(groupPosition).get(GROUP_TEXT).toString();
        }

        //得到大组成员总数
        public int getGroupCount() {
            return groupData.size();
        }

        // 得到小组成员的view
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.member_childitem, null);
            }
            final TextView title = (TextView) convertView.findViewById(R.id.child_text);
            title.setText(childData.get(childPosition).get(groupPosition).get(CHILD_TEXT1).toString());//大标题
            final TextView title2 = (TextView) convertView.findViewById(R.id.child_text2);
            title2.setText(childData.get(childPosition).get(groupPosition).get(CHILD_TEXT2).toString());//小标题
            return convertView;
        }

        //得到小组成员id
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        //得3到小组成员的名称
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        //得到小组成员的数量
        public int getChildrenCount(int groupPosition) {
            return childData.get(groupPosition).size();
        }

        /**
         * Indicates whether the child and group IDs are stable across changes to the
         * underlying data.
         * 表明大組和小组id是否稳定的更改底层数据。
         *
         * @return whether or not the same ID always refers to the same object
         * @see
         */
        public boolean hasStableIds() {
            return true;
        }

        //得到小组成员是否被选择
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}

