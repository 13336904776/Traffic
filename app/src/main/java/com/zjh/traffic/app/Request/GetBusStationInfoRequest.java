package com.zjh.traffic.app.Request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class GetBusStationInfoRequest extends BaseRequest {
    private int BusStationId;
    private String UserName;

    @Override
    public String getType() {
        return "GetBusStationInfo";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.BusStationId = Integer.parseInt(params[0].toString());
        this.UserName = params[1].toString();
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("BusStationId", this.BusStationId);
            body.put("UserName", this.UserName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("zjh_GetBusStationInfo", body.toString());
        return body.toString();
    }

    @Override
    public Object onResponseParse(String response) {
        Log.i("zjh_GetBusStationInfo", response);
        return response;
    }
}
