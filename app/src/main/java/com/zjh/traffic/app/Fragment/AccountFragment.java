package com.zjh.traffic.app.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.zjh.traffic.R;
import com.zjh.traffic.app.Callback.OnResponseListener;
import com.zjh.traffic.app.Request.GetCarAccountBalanceRequest;


public class AccountFragment extends Fragment {
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        button = view.findViewById(R.id.test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetCarAccountBalanceRequest().setParams(new Object[]{3, "admin"}).sendRequest(new OnResponseListener() {
                    @Override
                    public void onResponse(Object result) {
                        Toast.makeText(getContext(), result + "", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
