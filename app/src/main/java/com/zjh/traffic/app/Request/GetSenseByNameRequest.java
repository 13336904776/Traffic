package com.zjh.traffic.app.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class GetSenseByNameRequest extends BaseRequest {
    private String SenseName;
    private String UserName;

    @Override
    public String getType() {
        return "GetSenseByName";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.SenseName = params[0].toString();
        this.UserName = params[1].toString();
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("SenseName", this.SenseName);
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
                return new JSONObject(response).getInt(SenseName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "0";
    }
}
