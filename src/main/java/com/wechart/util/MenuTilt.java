package com.wechart.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MenuTilt {
    /**
     * 创建自己的菜单
     */
    private static final String menu_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+TokenUtils.getAccessTOken();

    public static void main(String[] args) {
        creatMenu();
    }
    public static void creatMenu() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        stringBuilder.append("\"button\":[");
        //第一个按钮
        stringBuilder.append("{");
        stringBuilder.append("\"type\":\"click\",");
        stringBuilder.append("\"name\":\"菜单\",");
        stringBuilder.append("\"key\":\"mymenu\"");
        stringBuilder.append("},");
        //第二个按钮二级菜单
        stringBuilder.append("{");
        stringBuilder.append("\"name\":\"帮助\",");
        stringBuilder.append("\"sub_button\":[");
        //第一个的
        stringBuilder.append("{");
        stringBuilder.append("\"type\":\"view\",");
        stringBuilder.append("\"name\":\"关于我们\",");
        stringBuilder.append("\"url\":\"http://www.baidu.com\"");
        stringBuilder.append("},");
        //第二个的
        stringBuilder.append("{");
        stringBuilder.append("\"type\":\"view\",");
        stringBuilder.append("\"name\":\"联系地址\",");
        stringBuilder.append("\"url\":\"http://www.baidu.com\"");
        stringBuilder.append("}");

        stringBuilder.append("]");
        stringBuilder.append("}");
        stringBuilder.append("]");
        stringBuilder.append("}");
        //发送post请求
        try {
            URL u = new URL(menu_url);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(stringBuilder.toString().getBytes("utf-8"));
            //接受响应
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(b)) != -1) {
                out.write(b, 0, len);
            }
            byte[] bytes = out.toByteArray();
            String s = new String(bytes);
            System.out.println("接受的响应"+s);
            connection.disconnect();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
