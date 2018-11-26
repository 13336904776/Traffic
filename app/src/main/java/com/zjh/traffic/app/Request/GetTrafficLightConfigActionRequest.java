package com.zjh.traffic.app.Request;

import com.zjh.traffic.app.Bean.tableListBean;

import org.json.JSONException;
import org.json.JSONObject;

public class GetTrafficLightConfigActionRequest extends BaseRequest {
    private String TrafficLightId;
    private String UserName;

    @Override
    public String getType() {
        return "GetTrafficLightConfigAction";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.TrafficLightId = params[0].toString();
        this.UserName = params[1].toString();
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject body = new JSONObject();
        try {
            body.put("TrafficLightId", this.TrafficLightId);
            body.put("UserName", this.UserName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return body.toString();
    }

    @Override
    public Object onResponseParse(String response) {
        tableListBean tableListBean;
        try {
            String result = new JSONObject(response).getString("RESULT");
            if (result.equals("S")) {
                tableListBean = new tableListBean(Integer.parseInt(TrafficLightId),
                        Integer.parseInt(new JSONObject(response).getString("RedTime")),
                        Integer.parseInt(new JSONObject(response).getString("YellowTime")),
                        Integer.parseInt(new JSONObject(response).getString("GreenTime")));
                return tableListBean;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableListBean = new tableListBean(1, 25, 5, 55);
        return tableListBean;
    }
}
