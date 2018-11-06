package com.zjh.traffic.app.Request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class SetCarAccountRechargeRequest extends BaseRequest {
    private int CarId;
    private int Money;
    private String UserName;

    @Override
    public String getType() {
        return "SetCarAccountRecharge";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.CarId = Integer.parseInt(params[0].toString());
        this.Money = Integer.parseInt(params[1].toString());
        this.UserName = params[2].toString();
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("CarId", this.CarId);
            body.put("Money", this.Money);
            body.put("UserName", this.UserName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("zjh_SetCarAccount", body.toString());
        return body.toString();
    }

    @Override
    public Object onResponseParse(String response) {
        try {
            String result = new JSONObject(response).getString("RESULT");
            if (result.equals("S"))
                return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
