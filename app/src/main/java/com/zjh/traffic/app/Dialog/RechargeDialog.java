package com.zjh.traffic.app.Dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zjh.traffic.R;

import java.util.List;

public class RechargeDialog extends DialogFragment {
    private List<Integer> rechargeCarId;//充值小车ID
    private List<String> rechargePlate;//充值小车车牌号

    public RechargeDialog() {
    }

    @SuppressLint("ValidFragment")
    public RechargeDialog(List<Integer> rechargeCarId, List<String> rechargePlate) {
        this.rechargeCarId = rechargeCarId;
        this.rechargePlate = rechargePlate;
    }

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_recharge, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (int i = 0; i < rechargeCarId.size(); i++)
            Toast.makeText(getContext(), rechargePlate.get(i) + "" + rechargeCarId.get(i), Toast.LENGTH_SHORT).show();
    }
}
