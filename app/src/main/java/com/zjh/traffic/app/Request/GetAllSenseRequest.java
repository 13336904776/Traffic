package com.zjh.traffic.app.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetAllSenseRequest extends BaseRequest {
    private String UserName;

    @Override
    public String getType() {
        return "GetAllSense";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.UserName = params[0].toString();
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("UserName", this.UserName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return body.toString();
    }

    @Override
    public Object onResponseParse(String response) {
        List<Integer> list = new ArrayList<>();
        try {
            String result = new JSONObject(response).getString("RESULT");
            if (result.equals("S")) {
                list.add(new JSONObject(response).getInt("temperature"));//温度
                list.add(new JSONObject(response).getInt("humidity"));//湿度
                list.add(new JSONObject(response).getInt("pm2.5"));//pm2.5
                list.add(new JSONObject(response).getInt("LightIntensity"));//光照强度
                list.add(new JSONObject(response).getInt("co2"));//二氧化碳

            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
