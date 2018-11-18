package com.zjh.traffic.app.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjh.traffic.R;
import com.zjh.traffic.app.Bean.buscxGroupBean;
import com.zjh.traffic.app.Bean.buscxItemBean;

import java.util.List;

public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {

    private List<buscxGroupBean> gData;
    private List<List<buscxItemBean>> iData;
    private Context mContext;

    public MyBaseExpandableListAdapter(List<buscxGroupBean> gData, List<List<buscxItemBean>> iData, Context mContext) {
        this.gData = gData;
        this.iData = iData;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return gData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return iData.get(groupPosition).size();
    }

    @Override
    public buscxGroupBean getGroup(int groupPosition) {
        return gData.get(groupPosition);
    }

    @Override
    public buscxItemBean getChild(int groupPosition, int childPosition) {
        return iData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //取得用于显示给定分组的视图. 这个方法仅返回分组的视图对象
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolderGroup groupHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_exlist_group, parent, false);
            groupHolder = new ViewHolderGroup();
            groupHolder.platform_name = convertView.findViewById(R.id.platform_name);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }
        groupHolder.platform_name.setText(gData.get(groupPosition).getPlatform_name());
        return convertView;
    }

    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem itemHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_exlist_item, parent, false);
            itemHolder = new ViewHolderItem();
            itemHolder.img_icon = convertView.findViewById(R.id.img_icon);
            itemHolder.bus_capacity = convertView.findViewById(R.id.bus_capacity);
            itemHolder.bus_remainingTime = convertView.findViewById(R.id.bus_remainingTime);
            itemHolder.bus_distance = convertView.findViewById(R.id.bus_distance);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ViewHolderItem) convertView.getTag();
        }
        itemHolder.img_icon.setImageResource(iData.get(groupPosition).get(childPosition).getImg_icon());
        itemHolder.bus_capacity.setText(iData.get(groupPosition).get(childPosition).getBus_capacity());
        itemHolder.bus_remainingTime.setText(iData.get(groupPosition).get(childPosition).getBus_remainingTime());
        itemHolder.bus_distance.setText(iData.get(groupPosition).get(childPosition).getBus_distance());
        return convertView;
    }

    //设置子列表是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private static class ViewHolderGroup {
        private TextView platform_name;
    }

    private static class ViewHolderItem {
        private ImageView img_icon;
        private TextView bus_capacity;
        private TextView bus_remainingTime;
        private TextView bus_distance;
    }

}