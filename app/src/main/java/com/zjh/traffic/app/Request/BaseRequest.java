package com.zjh.traffic.app.Request;

import com.zjh.traffic.app.Callback.OnResponseListener;
import com.zjh.traffic.app.Util.NetUtils;

public abstract class BaseRequest {
    /**
     * 返回url拼接类型
     *
     * @return
     */
    public abstract String getType();

    /**
     * 设置参数
     *
     * @param params
     * @return
     */
    public abstract BaseRequest setParams(Object[] params);

    /**
     * post请求参数
     *
     * @return
     */
    public abstract String getRequestBody();


    /**
     * 解析数据
     *
     * @param response
     * @return
     */
    public abstract Object onResponseParse(String response);


    public void sendRequest(final OnResponseListener responseListener) {
        String url = "http://192.168.0.7:8080/transportservice2/action/" + getType() + ".do";
        NetUtils.asyncRequest(url, getRequestBody(), new OnResponseListener() {
            @Override
            public void onResponse(Object result) {
                responseListener.onResponse(onResponseParse(result.toString()));
            }
        });
    }
}
