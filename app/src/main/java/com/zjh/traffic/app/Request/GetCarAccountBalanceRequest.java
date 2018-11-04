package com.zjh.traffic.app.Request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class GetCarAccountBalanceRequest extends BaseRequest {
    private int CarId;
    private String UserName;

    @Override
    public String getType() {
        return "GetCarAccountBalance";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.CarId = Integer.parseInt(params[0].toString());
        this.UserName = params[1].toString();
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("CarId", this.CarId);
            body.put("UserName", this.UserName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("zjh", body.toString());
        return body.toString();
    }

    @Override
    public Object onResponseParse(String response) {

        try {
            String result = new JSONObject(response).getString("RESULT");
            if (result.equals("S")) {
                return new JSONObject(response).getInt("Balance");
            } else {
                return 500;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
