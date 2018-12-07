package com.wechart.util;

import org.json.JSONObject;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class TokenUtils {
    /**
     * 获取微信公众号的全局唯一调用凭证
     * 正式开发放redis缓存里
     */
    private static final String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx35e4ddc15fc64c19&secret=ad920f9c1aa2480a51530a2a81964abf";
    private static final String jsapi_ticket = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+getAccessTOken()+"&type=jsapi";

    public static String getAccessTOken() {
        String url = getUrl(access_token_url);
        JSONObject jsonObject = new JSONObject(url);
        String access_token = jsonObject.getString("access_token");
        return access_token;
    }

    public static String getJsapi_ticket() {
        String u = getUrl(jsapi_ticket);
        JSONObject jsonObject = new JSONObject(u);
        String ticket = jsonObject.getString("ticket");
        return ticket;
    }

    private static String getUrl(String urlstr) {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        String s = null;
        try {
            URL url = new URL(urlstr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream();
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = inputStream.read(b)) != -1) {
                byteArrayOutputStream.write(b, 0, len);
            }
            byte[] bytes = byteArrayOutputStream.toByteArray();
            s = new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return s;
    }

}
