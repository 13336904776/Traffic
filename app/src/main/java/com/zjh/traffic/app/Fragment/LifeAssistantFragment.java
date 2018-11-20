package com.zjh.traffic.app.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zjh.traffic.R;

/**
 * 生活助手
 */
public class LifeAssistantFragment extends Fragment {
    private TextView temperature_now, temperature_today, UVI_intensity, UVI_data,
            cold_intensity, cold_data, dress_intensity, dress_data, sports_intensity,
            sports_data, air_intensity, air_data;
    private ImageButton btn_refresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lifeassistant, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        temperature_now = view.findViewById(R.id.temperature_now);
        temperature_today = view.findViewById(R.id.temperature_today);
        UVI_intensity = view.findViewById(R.id.UVI_intensity);
        UVI_data = view.findViewById(R.id.UVI_data);
        cold_intensity = view.findViewById(R.id.cold_intensity);
        cold_data = view.findViewById(R.id.cold_data);
        dress_intensity = view.findViewById(R.id.dress_intensity);
        dress_data = view.findViewById(R.id.dress_data);
        sports_intensity = view.findViewById(R.id.sports_intensity);
        sports_data = view.findViewById(R.id.sports_data);
        air_intensity = view.findViewById(R.id.air_intensity);
        air_data = view.findViewById(R.id.air_data);
        btn_refresh = view.findViewById(R.id.btn_refresh);
    }
}
