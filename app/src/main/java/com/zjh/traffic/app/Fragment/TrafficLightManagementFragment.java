package com.zjh.traffic.app.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.zjh.traffic.R;
import com.zjh.traffic.app.Adapter.MyBaseAdapter;
import com.zjh.traffic.app.Bean.tableListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 红绿灯管理
 */
public class TrafficLightManagementFragment extends Fragment {
    private Spinner spinner;
    private List<String> list;
    private ArrayAdapter<String> arrayAdapter;
    private MyBaseAdapter<tableListBean> myBaseAdapter;
    private ListView tableList;
    private List<tableListBean> listData;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trafficlightmanagement, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        spinner = view.findViewById(R.id.spinner);
        list = new ArrayList<>();
        list.add("路口升序");
        list.add("路口降序");
        list.add("红灯升序");
        list.add("红灯降序");
        list.add("绿灯升序");
        list.add("绿灯降序");
        list.add("黄灯升序");
        list.add("黄灯降序");
        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new SelectedListener());
        listData = new ArrayList<>();
        tableList = view.findViewById(R.id.tableList);
        myBaseAdapter = new MyBaseAdapter<tableListBean>(listData, R.layout.item_table_list) {
            @Override
            public void bindView(ViewHolder holder, tableListBean obj) {
                holder.setText(R.id.roadId, obj.getRoadId() + "");
                holder.setText(R.id.redLightTime, obj.getRedLightTime() + "");
                holder.setText(R.id.yellowLightTime, obj.getYellowLightTime() + "");
                holder.setText(R.id.greenLightTime, obj.getGreenLightTime() + "");
                holder.setOnCheckedChangeListener(R.id.checkbox, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    }
                });
                holder.setOnClickListener(R.id.btn_setting, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        };
        for (int i = 0; i < 5; i++) {
            myBaseAdapter.add(new tableListBean(i + 1, 6, 6, 6));
        }
        tableList.setAdapter(myBaseAdapter);
    }

    private class SelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

}
