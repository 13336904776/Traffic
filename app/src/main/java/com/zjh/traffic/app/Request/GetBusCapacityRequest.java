package com.zjh.traffic.app.Request;

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
        return body.toString();
    }

    @Override
    public Object onResponseParse(String response) {
        try {
            String result = new JSONObject(response).getString("RESULT");
            if (result.equals("S"))
                return new JSONObject(response).getInt("BusCapacity");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "0";
    }
}
