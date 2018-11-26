package com.zjh.traffic.app.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.zjh.traffic.R;
import com.zjh.traffic.app.Adapter.MyBaseAdapter;
import com.zjh.traffic.app.Application.App;
import com.zjh.traffic.app.Bean.tableListBean;
import com.zjh.traffic.app.Callback.OnResponseListener;
import com.zjh.traffic.app.Dialog.LightSettingsDialog;
import com.zjh.traffic.app.Request.GetTrafficLightConfigActionRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 红绿灯管理
 */
public class TrafficLightManagementFragment extends Fragment implements View.OnClickListener {
    private Spinner spinner;
    private List<String> list;
    private String sort;//记录排序
    private ArrayAdapter<String> arrayAdapter;
    private MyBaseAdapter<tableListBean> myBaseAdapter;
    private ListView tableList;
    private List<tableListBean> listData;
    private List<tableListBean> listBeans;
    private Button btn_Search, btn_AllSettings;
    private List<Integer> select_ID;
    private Boolean[] CompoundButton_isChecked;//记录复选按钮状态
    private int[] sortLightTime;//记录id排序

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trafficlightmanagement, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CompoundButton_isChecked = new Boolean[]{false, false, false, false, false};
        initView(view);
        upData();
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
        sort = list.get(0);
        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new SelectedListener());
        btn_Search = view.findViewById(R.id.btn_Search);
        btn_AllSettings = view.findViewById(R.id.btn_AllSettings);
        btn_Search.setOnClickListener(this);
        btn_AllSettings.setOnClickListener(this);
        listData = new ArrayList<>();
        tableList = view.findViewById(R.id.tableList);
        myBaseAdapter = new MyBaseAdapter<tableListBean>(listData, R.layout.item_table_list) {
            @Override
            public void bindView(final ViewHolder holder, tableListBean obj) {
                holder.setText(R.id.TrafficLightId, obj.getTrafficLightId() + "");
                holder.setText(R.id.redLightTime, obj.getRedLightTime() + "");
                holder.setText(R.id.yellowLightTime, obj.getYellowLightTime() + "");
                holder.setText(R.id.greenLightTime, obj.getGreenLightTime() + "");
                holder.setOnCheckedChangeListener(R.id.checkbox, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        select_ID = new ArrayList<>();
                        CompoundButton_isChecked[sortLightTime[holder.getItemPosition()]] = isChecked;
                        Log.i("zjh", sortLightTime[holder.getItemPosition()]
                                + "" + CompoundButton_isChecked[sortLightTime[holder.getItemPosition()]] + "");
                        for (int i = 1; i <= CompoundButton_isChecked.length; i++)
                            if (CompoundButton_isChecked[i - 1])
                                select_ID.add(i);
                    }
                });
                holder.setOnClickListener(R.id.btn_setting, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<Integer> list = new ArrayList<>();
                        list.add(listData.get(holder.getItemPosition()).getTrafficLightId());
                        if (getFragmentManager() != null) {
                            LightSettingsDialog lightSettingsDialog = new LightSettingsDialog(list);
                            lightSettingsDialog.setTargetFragment(TrafficLightManagementFragment.this, 0);
                            lightSettingsDialog.show(getFragmentManager(), "LightSettings");
                        }

                    }
                });
            }
        };
        listBeans = new ArrayList<>();
        listBeans.add(new tableListBean(1, 10, 5, 2));
        listBeans.add(new tableListBean(2, 45, 53, 3));
        listBeans.add(new tableListBean(3, 2, 8, 6));
        listBeans.add(new tableListBean(4, 50, 10, 1));
        listBeans.add(new tableListBean(5, 7, 6, 4));
        for (int i = 0; i < listBeans.size(); i++)
            myBaseAdapter.add(listBeans.get(i));
        tableList.setAdapter(myBaseAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_Search) {
            upData();
        } else if (v.getId() == R.id.btn_AllSettings) {
            if (getFragmentManager() != null) {
                LightSettingsDialog lightSettingsDialog = new LightSettingsDialog(select_ID);
                lightSettingsDialog.setTargetFragment(TrafficLightManagementFragment.this, 0);
                lightSettingsDialog.show(getFragmentManager(), "LightSettings");
            }
        }
    }

    private class SelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            sort = list.get(position);//更改排序规则
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 0:
                    upData();
                    break;
            }
        }
    }

    private void upData() {
        listBeans.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    if (i != 1) {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    final int finalI = i;
                    new GetTrafficLightConfigActionRequest().setParams(new Object[]{String.valueOf(i), App.getUserName()})
                            .sendRequest(new OnResponseListener() {
                                @Override
                                public void onResponse(Object result) {
                                    Log.i("zjh", ((tableListBean) result).getTrafficLightId() + "");
                                    listBeans.add((tableListBean) result);
                                    if (finalI == 5) {
                                        Message message = new Message();
                                        message.what = 200;
                                        handler.sendMessage(message);
                                    }
                                }
                            });
                }
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    myBaseAdapter.clear();
                    if (sort.equals(list.get(0))) {
                        sortLightTime = new int[]{0, 1, 2, 3, 4};
                        for (int i = 0; i < listBeans.size(); i++)
                            myBaseAdapter.add(listBeans.get(i));
                    } else if (sort.equals(list.get(1))) {
                        sortLightTime = new int[]{4, 3, 2, 1, 0};
                        for (int i = listBeans.size() - 1; i >= 0; i--)
                            myBaseAdapter.add(listBeans.get(i));
                    } else if (sort.equals(list.get(2))) {
                        int[] RedLightTime = new int[listBeans.size()];
                        for (int i = 0; i < listBeans.size(); i++)
                            RedLightTime[i] = listBeans.get(i).getRedLightTime();
                        sortLightTime = sort(RedLightTime, true);
                        for (int i = 0; i < sortLightTime.length; i++)
                            myBaseAdapter.add(listBeans.get(sortLightTime[i]));
                    } else if (sort.equals(list.get(3))) {
                        int[] RedLightTime = new int[listBeans.size()];
                        for (int i = 0; i < listBeans.size(); i++)
                            RedLightTime[i] = listBeans.get(i).getRedLightTime();
                        sortLightTime = sort(RedLightTime, false);
                        for (int i = 0; i < sortLightTime.length; i++)
                            myBaseAdapter.add(listBeans.get(sortLightTime[i]));
                    } else if (sort.equals(list.get(4))) {
                        int[] GreenLightTime = new int[listBeans.size()];
                        for (int i = 0; i < listBeans.size(); i++)
                            GreenLightTime[i] = listBeans.get(i).getGreenLightTime();
                        sortLightTime = sort(GreenLightTime, true);
                        for (int i = 0; i < sortLightTime.length; i++)
                            myBaseAdapter.add(listBeans.get(sortLightTime[i]));
                    } else if (sort.equals(list.get(5))) {
                        int[] GreenLightTime = new int[listBeans.size()];
                        for (int i = 0; i < listBeans.size(); i++)
                            GreenLightTime[i] = listBeans.get(i).getGreenLightTime();
                        sortLightTime = sort(GreenLightTime, false);
                        for (int i = 0; i < sortLightTime.length; i++)
                            myBaseAdapter.add(listBeans.get(sortLightTime[i]));
                    } else if (sort.equals(list.get(6))) {
                        int[] YellowLightTime = new int[listBeans.size()];
                        for (int i = 0; i < listBeans.size(); i++)
                            YellowLightTime[i] = listBeans.get(i).getYellowLightTime();
                        sortLightTime = sort(YellowLightTime, true);
                        for (int i = 0; i < sortLightTime.length; i++)
                            myBaseAdapter.add(listBeans.get(sortLightTime[i]));
                    } else if (sort.equals(list.get(7))) {
                        int[] YellowLightTime = new int[listBeans.size()];
                        for (int i = 0; i < listBeans.size(); i++)
                            YellowLightTime[i] = listBeans.get(i).getYellowLightTime();
                        sortLightTime = sort(YellowLightTime, false);
                        for (int i = 0; i < sortLightTime.length; i++)
                            myBaseAdapter.add(listBeans.get(sortLightTime[i]));
                    }
            }
        }
    };

    /**
     * is true升序 false降序
     *
     * @param array
     * @param is
     * @return
     */
    private int[] sort(int[] array, Boolean is) {
        int[] id = new int[]{0, 1, 2, 3, 4};
        if (is)
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[i] > array[j]) {
                        int temp1 = array[i];
                        int temp2 = id[i];
                        array[i] = array[j];
                        array[j] = temp1;
                        id[i] = id[j];
                        id[j] = temp2;
                    }
                }
            }
        else
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[i] < array[j]) {
                        int temp1 = array[i];
                        int temp2 = id[i];
                        array[i] = array[j];
                        array[j] = temp1;
                        id[i] = id[j];
                        id[j] = temp2;
                    }
                }
            }

        return id;
    }
}
