package com.wechart.controller;

import com.wechart.entity.Wechart;
import com.wechart.util.MsgUtils;
import com.wechart.util.SHA1;
import com.wechart.util.TokenUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;


/**
 * 微信接入入口
 */
@RestController
@RequestMapping("/wechart")
public class WechatControl {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String wechatIndex(String signature, String timestamp, String nonce, String echostr) {
        System.out.println(signature);
        System.out.println(timestamp);
        System.out.println(nonce);
        System.out.println(echostr);
        return echostr;
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String wechartget(@RequestBody String content) throws Exception {
        System.out.println(content);
        Wechart parse = MsgUtils.parse(content);
        System.out.println(parse);
        String event = MsgUtils.event(parse);
        System.out.println(event);
        return event;
    }

    /**
     * jssdk的认证
     */
    @RequestMapping("/jssdk")
    public String jssdk(String url) {
        //随机字符串
        String nonceStr = UUID.randomUUID().toString();
        //有效的jsapi_ticket
        String jsapi = TokenUtils.getJsapi_ticket();
        //时间
        long timestamp = System.currentTimeMillis();
        //字典序排序
        Set<String> set = new TreeSet<>();
        set.add("url=" + url);
        set.add("noncestr=" + nonceStr);
        set.add("jsapi_ticket=" + jsapi);
        set.add("timestamp=" + timestamp);
        Iterator<String> iterator = set.iterator();
        String str = "";
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (str != "") {
                str += "&";
            }
            str += next;
        }
        String encode = SHA1.encode(str);
        String json = "{\"signature\":\""+encode+"\",\"noncestr\":"+nonceStr+"\",\"timestamp\":\""+timestamp+"\"}";
        return json;
    }
}
