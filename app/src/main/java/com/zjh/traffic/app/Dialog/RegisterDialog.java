package com.zjh.traffic.app.Dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zjh.traffic.R;
import com.zjh.traffic.app.Application.App;

public class RegisterDialog extends DialogFragment {
    private EditText re_username, re_password, re_again_password;
    private Button re_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_register, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        re_username = view.findViewById(R.id.re_username);
        re_password = view.findViewById(R.id.re_password);
        re_again_password = view.findViewById(R.id.re_again_password);
        re_btn = view.findViewById(R.id.re_btn);
        re_btn.setOnClickListener(new Click());
    }

    class Click implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.re_btn) {
                if (!re_username.getText().toString().equals("") && !re_password.getText().toString().equals("") && !re_again_password.getText().toString().equals("")) {
                    if (re_password.getText().toString().equals(re_again_password.getText().toString())) {
                        App.setUserName(re_username.getText().toString());
                        App.setPassword(re_password.getText().toString());
                        RegisterDialog.this.dismiss();
                        App.showAlertDialog(getContext(), "成功", "注册成功");
                    } else
                        App.showAlertDialog(getContext(), "失败", "两次密码不同");
                } else {
                    App.showAlertDialog(getContext(), "失败", "账号密码不能为空");
                }
            }
        }
    }
}
