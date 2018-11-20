package com.zjh.traffic.app.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class GetRoadStatusRequest extends BaseRequest {
    private int RoadId;
    private String UserName;

    @Override
    public String getType() {
        return "GetRoadStatus";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.RoadId = Integer.parseInt(params[0].toString());
        this.UserName = params[1].toString();
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("RoadId", this.RoadId);
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
                return new JSONObject(response).getInt("Status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
