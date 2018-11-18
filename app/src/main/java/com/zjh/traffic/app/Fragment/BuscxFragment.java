package com.zjh.traffic.app.Fragment;

import android.annotation.SuppressLint;
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
import android.widget.ExpandableListView;

import com.zjh.traffic.R;
import com.zjh.traffic.app.Adapter.MyBaseExpandableListAdapter;
import com.zjh.traffic.app.Application.App;
import com.zjh.traffic.app.Bean.buscxGroupBean;
import com.zjh.traffic.app.Bean.buscxItemBean;
import com.zjh.traffic.app.Callback.OnResponseListener;
import com.zjh.traffic.app.Request.GetBusCapacityRequest;
import com.zjh.traffic.app.Request.GetBusStationInfoRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BuscxFragment extends Fragment {
    private List<buscxGroupBean> buscxGroupList;
    private List<buscxItemBean> buscxItemList_1;
    private List<buscxItemBean> buscxItemList_2;
    private List<List<buscxItemBean>> Data;
    private ExpandableListView expandableListView;
    private MyBaseExpandableListAdapter myAdapter;
    private int[] BusId = {1, 2};//公交车ID
    private int[] BusStationId = {1, 2};//公交站台ID
    private String[] BusCapacity = new String[BusId.length];
    private List<ArrayList<String>> Distance = new ArrayList<>();
    private TimerTask task = null;
    private Timer timer = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buscx, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initTask();
        if (timer == null) {
            timer = new Timer();
        } else {
            timer.cancel();
            timer = new Timer();
        }
        timer.schedule(task, 0, 3 * 1000);
    }


    private void initView(View view) {
        expandableListView = view.findViewById(R.id.expand_list);
        Data = new ArrayList<>();
        //组数据准备
        buscxGroupList = new ArrayList<>();
        buscxGroupList.add(new buscxGroupBean("中医院站"));
        buscxGroupList.add(new buscxGroupBean("联想大厦站"));

        //中医院站组
        buscxItemList_1 = new ArrayList<>();
        buscxItemList_1.add(new buscxItemBean(BusId[0] + "号(0人)", "1000"));
        buscxItemList_1.add(new buscxItemBean(BusId[1] + "号(0人)", "1000"));
        Data.add(buscxItemList_1);
        //联想大厦站组
        buscxItemList_2 = new ArrayList<>();
        buscxItemList_2.add(new buscxItemBean(BusId[0] + "号(0人)", "2000"));
        buscxItemList_2.add(new buscxItemBean(BusId[1] + "号(0人)", "2000"));
        Data.add(buscxItemList_2);

        myAdapter = new MyBaseExpandableListAdapter(buscxGroupList, Data, getContext());
        expandableListView.setAdapter(myAdapter);
    }

    private void initTask() {
        task = new TimerTask() {
            @Override
            public void run() {

                for (int i = 0; i < BusId.length; i++) {
                    if (i != 0) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    final int finalI = i;
                    new GetBusCapacityRequest().setParams(new Object[]{BusId[i], App.getUserName()})
                            .sendRequest(new OnResponseListener() {
                                @Override
                                public void onResponse(Object result) {
                                    Log.i("zjh_GetBusCapacity", BusId[finalI] + "号公交车" + result.toString());
                                    BusCapacity[finalI] = BusId[finalI] + "号(" + result.toString()+"人)";
                                }
                            });
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    new GetBusStationInfoRequest().setParams(new Object[]{BusStationId[i], App.getUserName()})
                            .sendRequest(new OnResponseListener() {
                                @Override
                                public void onResponse(Object result) {
                                    Log.i("zjh_GetBusStationInfo", BusStationId[finalI] + "号公交站台" + result.toString());
                                    ArrayList<String> arrayList = (ArrayList<String>) result;
                                    Distance.add(finalI, arrayList);
                                    if (finalI == BusId.length - 1) {
                                        Message message = new Message();
                                        message.what = 100;
                                        handler.sendMessage(message);
                                    }
                                }
                            });
                }
            }
        };
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    Data.clear();
                    //中医院站组
                    buscxItemList_1 = new ArrayList<>();
                    if (Double.parseDouble(Distance.get(0).get(0)) < Double.parseDouble(Distance.get(0).get(1))) {
                        buscxItemList_1.add(new buscxItemBean(BusCapacity[0], Distance.get(0).get(0)));
                        buscxItemList_1.add(new buscxItemBean(BusCapacity[1], Distance.get(0).get(1)));
                    } else {
                        buscxItemList_1.add(new buscxItemBean(BusCapacity[1], Distance.get(0).get(1)));
                        buscxItemList_1.add(new buscxItemBean(BusCapacity[0], Distance.get(0).get(0)));
                    }
                    Data.add(buscxItemList_1);
                    //联想大厦站组
                    buscxItemList_2 = new ArrayList<>();
                    if (Double.parseDouble(Distance.get(1).get(0)) < Double.parseDouble(Distance.get(1).get(1))) {
                        buscxItemList_2.add(new buscxItemBean(BusCapacity[0], Distance.get(1).get(0)));
                        buscxItemList_2.add(new buscxItemBean(BusCapacity[1], Distance.get(1).get(1)));
                    } else {
                        buscxItemList_2.add(new buscxItemBean(BusCapacity[1], Distance.get(1).get(1)));
                        buscxItemList_2.add(new buscxItemBean(BusCapacity[0], Distance.get(1).get(0)));
                    }
                    Data.add(buscxItemList_2);
                    myAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}