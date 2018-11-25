package com.zjh.traffic.app.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class SetTrafficLightConfigRequest extends BaseRequest {
    private int TrafficLightId;
    private int RedTime;
    private int GreenTime;
    private int YellowTime;
    private String UserName;

    @Override
    public String getType() {
        return "SetTrafficLightConfig";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.TrafficLightId = Integer.parseInt(params[0].toString());
        this.RedTime = Integer.parseInt(params[1].toString());
        this.GreenTime = Integer.parseInt(params[2].toString());
        this.YellowTime = Integer.parseInt(params[3].toString());
        this.UserName = params[4].toString();
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("TrafficLightId", this.TrafficLightId);
            body.put("RedTime", this.RedTime);
            body.put("GreenTime", this.GreenTime);
            body.put("YellowTime", this.YellowTime);
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
                return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
