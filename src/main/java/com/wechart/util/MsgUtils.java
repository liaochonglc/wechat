package com.wechart.util;

import com.wechart.entity.Wechart;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


import java.lang.reflect.Field;

public class MsgUtils {
    public static Wechart parse(String content) {
        Document document;
        Class wechart = Wechart.class;
        Wechart wechart1 = new Wechart();
        try {
            document = DocumentHelper.parseText(content);
            Element rootElement = document.getRootElement();
            Field[] declaredFields = wechart.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                String name = field.getName();
                Element element = rootElement.element(name);
                if (element != null) {
                    String textTrim = element.getTextTrim();
                    field.set(wechart1, textTrim);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wechart1;
    }

    public static String event(Wechart wechart) {
        StringBuilder stringBuilder = new StringBuilder();
        if (wechart.getMsgType().equals("event") && wechart.getEvent().equals("subscribe")||wechart.getEventKey().equals("mymenu")) {
            stringBuilder.append("<xml>");
            stringBuilder.append("<ToUserName>").append(wechart.getFromUserName()).append("</ToUserName>");
            stringBuilder.append("<FromUserName>").append(wechart.getToUserName()).append("</FromUserName>");
            stringBuilder.append("<CreateTime>").append(System.currentTimeMillis()).append("</CreateTime>");
            stringBuilder.append("<MsgType>").append("news").append("</MsgType>");
            stringBuilder.append("<ArticleCount>4</ArticleCount>");
            stringBuilder.append("<Articles>");
            stringBuilder.append("<item>");
            stringBuilder.append("<Title><![CDATA[小老弟欢迎关注我]]></Title>");
            stringBuilder.append("<PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz_jpg/4U3blciavyYE6QUr0Ml4icLRoBX0If6uBUibrsxiasbsCwYzdqFyRux88DhoKuic0wjiaDl17tp2mLN9NSsFPrTze2Xw/0]]></PicUrl>");
            stringBuilder.append("<Url><![CDATA[http://www.baidu.com]]></Url>");
            stringBuilder.append("</item>");
            stringBuilder.append("<item>");
            stringBuilder.append("<Title><![CDATA[小老弟你咋啦]]></Title>");
            stringBuilder.append("<PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz_jpg/4U3blciavyYE6QUr0Ml4icLRoBX0If6uBUUj5hENnzBEjOeuOEotqbVDsTicsCm3kIJFF04LkrWjjGeBZ1TlwaYYA/0]]></PicUrl>");
            stringBuilder.append("<Url><![CDATA[http://www.baidu.com]]></Url>");
            stringBuilder.append("</item>");
            stringBuilder.append("<item>");
            stringBuilder.append("<Title><![CDATA[嘿嘿]]></Title>");
            stringBuilder.append("<PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz_jpg/4U3blciavyYE6QUr0Ml4icLRoBX0If6uBUibexeJS4CeLMMwaxw3JuL3wO3GwPzXIJnxe4YNnlaXN62ORUqp24LXQ/0\n]]></PicUrl>");
            stringBuilder.append("<Url><![CDATA[http://www.baidu.com]]></Url>");
            stringBuilder.append("</item>");
            stringBuilder.append("<item>");
            stringBuilder.append("<Title><![CDATA[我的位置]]></Title>");
            stringBuilder.append("<PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz_jpg/4U3blciavyYE6QUr0Ml4icLRoBX0If6uBUv0O5iabWk4pPGpall66h7flYmB4SwbvYicbNDLRUpNHRC4B8OcnY1GiaQ/0]]></PicUrl>");
            stringBuilder.append("<Url><![CDATA[http://liaosonhg.free.idcfengye.com/location/map]]></Url>");
            stringBuilder.append("</item>");
            stringBuilder.append("</Articles>");
            stringBuilder.append("</xml>");
        }
        /*stringBuilder.append("<xml>");
        stringBuilder.append("<ToUserName>").append(wechart.getFromUserName()).append("</ToUserName>");
        stringBuilder.append("<FromUserName>").append(wechart.getToUserName()).append("</FromUserName>");
        stringBuilder.append("<CreateTime>").append(System.currentTimeMillis()).append("</CreateTime>");
        stringBuilder.append("<MsgType>").append("text").append("</MsgType>");
        stringBuilder.append("<Content>").append(text).append("</Content>");
        stringBuilder.append("</xml>");*/

        return stringBuilder.toString();
    }
}
