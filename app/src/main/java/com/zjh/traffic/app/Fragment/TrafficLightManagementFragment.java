package com.zjh.traffic.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.zjh.traffic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 红绿灯管理
 */
public class TrafficLightManagementFragment extends Fragment {
    private Spinner spinner;
    private List<String> list;
    private ArrayAdapter<String> arrayAdapter;
    private TableLayout tableLayout;

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

        tableLayout = view.findViewById(R.id.tableLayout);
        initTableLayout();
    }

    private class SelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    private void initTableLayout() {
        ArrayList<String> tabCol = new ArrayList<>();
        ArrayList<String> tabH = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            tabCol.add("第 (" + i + ") 列");
            tabH.add("第 (" + i + ") 行");
        }

        //控制行数
        for (int row = 0; row < tabH.size(); row++) {

            TableRow tabRow = new TableRow(getContext());
            //控制列数
            for (int col = 0; col < tabCol.size(); col++) {

                TextView tv = new TextView(getContext());
                tv.setText(tabCol.get(col) + tabH.get(row));
                tv.setGravity(Gravity.CENTER);
                tv.setBackgroundResource(R.drawable.ic_tablebg);
                tabRow.addView(tv);

            }
            tableLayout.addView(tabRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }
}
