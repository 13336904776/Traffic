package com.zjh.traffic.app.Request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class GetBusStationInfoRequest extends BaseRequest {
    private int BusStationID;
    private String UserName;

    @Override
    public String getType() {
        return "GetBusStationInfo";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.BusStationID = Integer.parseInt(params[0].toString());
        this.UserName = params[1].toString();
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("BusStationID", this.BusStationID);
            body.put("UserName", this.UserName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return body.toString();
    }

    @Override
    public Object onResponseParse(String response) {
        return "";
    }
}
