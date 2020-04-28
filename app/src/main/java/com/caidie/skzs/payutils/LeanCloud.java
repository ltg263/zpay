package com.caidie.skzs.payutils;

import com.alibaba.fastjson.JSON;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LeanCloud {
    private String appId = "qGpowKJBhfJAFlsz4NtCLjlP-gzGzoHsz";

    private String appKey = "YKRMzp6TjQQ9op2YGxpYpmHg";

    private String mediaType = "application/json; charset=utf-8";

    private String session;

    private static LeanCloud leanCloud = new LeanCloud();

    private LeanCloud() {
    }

    public static LeanCloud instance() {
        return leanCloud;
    }

    public void login() {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse(mediaType), "{\"username\":\"admin\",\"password\":\"lg12345\"}");
        final Request request = new Request.Builder()
                .url("https://qgpowkjb.api.lncld.net/1.1/login")
                .addHeader("X-LC-Id", appId)
                .addHeader("X-LC-Key", appKey)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String s = response.body().string();
            AVResponse av = JSON.parseObject(s, AVResponse.class);
            session = av.getSessionToken();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void save(Order order) {
        if (session == null) {
            login();
        }
        OkHttpClient client = new OkHttpClient();
        String json = JSON.toJSONString(order);
        RequestBody body = RequestBody.create(MediaType.parse(mediaType), json);
        final Request request = new Request.Builder()
                .url("https://qgpowkjb.api.lncld.net/1.1/classes/Order")
                .addHeader("X-LC-Id", appId)
                .addHeader("X-LC-Key", appKey)
                .addHeader("X-LC-Session", session)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String s = response.body().string();
            AVResponse av = JSON.parseObject(s, AVResponse.class);
            session = av.getSessionToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class AVResponse {
        private String sessionToken;

        public String getSessionToken() {
            return sessionToken;
        }

        public void setSessionToken(String sessionToken) {
            this.sessionToken = sessionToken;
        }
    }
}
