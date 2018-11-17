package com.zjh.traffic.app.Request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class GetBusCapacityRequest extends BaseRequest {
    private int BusId;
    private String UserName;

    @Override
    public String getType() {
        return "GetBusCapacity";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.BusId = Integer.parseInt(params[0].toString());
        this.UserName = params[1].toString();
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("BusId", this.BusId);
            body.put("UserName", this.UserName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("zjh_GetBusCapacity", body.toString());
        return body.toString();
    }

    @Override
    public Object onResponseParse(String response) {
        try {
            String result = new JSONObject(response).getString("RESULT");
            if (result.equals("S"))
                return BusId + "号(" + new JSONObject(response).getInt("BusCapacity") + "人)";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return BusId + "号(0人)";
    }
}
