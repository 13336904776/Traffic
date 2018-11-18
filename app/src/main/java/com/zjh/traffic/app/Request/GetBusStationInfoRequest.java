package com.zjh.traffic.app.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
        return body.toString();
    }

    @Override
    public Object onResponseParse(String response) {
        ArrayList<String> Distance = new ArrayList<>();
        try {
            String result = new JSONObject(response).getString("RESULT");
            if (result.equals("S")) {
                result = new JSONObject(response).getString("ROWS_DETAIL");
                JSONArray jsonArray = new JSONArray(result);
                Distance.add(0, jsonArray.getJSONObject(0).getString("Distance"));
                Distance.add(1, jsonArray.getJSONObject(1).getString("Distance"));
                return Distance;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Distance.add(0, "1000");
        Distance.add(1, "2000");
        return Distance;
    }
}
