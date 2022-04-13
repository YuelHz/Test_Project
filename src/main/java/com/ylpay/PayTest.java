package com.ylpay;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jeequan.jeepay.Jeepay;
import com.jeequan.jeepay.JeepayClient;
import com.jeequan.jeepay.exception.JeepayException;
import com.jeequan.jeepay.model.PayOrderCreateReqModel;
import com.jeequan.jeepay.request.PayOrderCreateRequest;
import com.jeequan.jeepay.response.PayOrderCreateResponse;
import com.jeequan.jeepay.util.JeepayKit;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class PayTest {

    public static void main(String[] args) throws JeepayException {
        // 创建客户端
        JeepayClient jeepayClient = JeepayClient.getInstance("624c218063c8dd6c5a5ecac9", "wnRjg0HR4kUH8mvWfl8scHSSe4U7G2ZytR8OMM5IJ1ztv25gtyxe51d6ehHzhV8yd9Ep5KUsGUa6r1lBfp9TJzd4P5ulYAFudrjluDMz9CzxR1HIxjtoOjCuqOb4dVaQ");

        Jeepay.setApiBase("http://192.168.3.92:9216/api/pay/unifiedOrder");

        jeepayClient.setApiBase("http://192.168.3.92:9216/api/pay/unifiedOrder");
        // 构建请求数据
        String wayCode = "ALI_QR";                           // 支付方式
        PayOrderCreateRequest request = new PayOrderCreateRequest();
        PayOrderCreateReqModel model = new PayOrderCreateReqModel();
        model.setMchNo("M1649156459");                       // 商户号
        model.setAppId(jeepayClient.getAppId());            // 应用ID
        String orderNo = "mho" + System.currentTimeMillis();
        model.setMchOrderNo(orderNo);                       // 商户订单号
        model.setWayCode(wayCode);                          // 支付方式
        model.setAmount(1L);                                // 金额，单位分
        model.setCurrency("CNY");                           // 币种，目前只支持cny
        model.setClientIp("192.166.1.132");                 // 发起支付请求客户端的IP地址
        model.setSubject("测试");                         // 商品标题
        model.setBody("商品描述");                            // 商品描述
//        model.setNotifyUrl("https://www.jeequan.com");      // 异步通知地址
//        model.setReturnUrl("");                             // 前端跳转地址
//        model.setExtParam("");                              // 商户扩展参数,回调时原样返回
        request.setBizModel(model);
        // 发起统一下单
        PayOrderCreateResponse response = jeepayClient.execute(request);

        System.out.println(JSON.toJSON(response));
        // 验证返回数据签名
        response.checkSign(Jeepay.apiKey);

        // 判断下单是否返回成功
        response.isSuccess(Jeepay.apiKey);
    }

    @Test
    public void testPay(){
        Map map = new HashMap<>();
        map.put("mchNo","M1645182535");
        map.put("appId","620f7e7760b21cfa52cfe73f");
        map.put("mchOrderNo","mho" + System.currentTimeMillis());
        map.put("wayCode","WX_NATIVE");
        map.put("amount","1");
        map.put("currency","cny");
        map.put("subject","商品标题测试");
        map.put("body","测试");
        map.put("reqTime", System.currentTimeMillis());
        map.put("version","1.0");
        map.put("signType","MD5");
        String sign = JeepayKit.getStrSort(map);
        System.out.println("待签名："+sign);
        String s = SecureUtil.md5(sign+"key=PVDuEHvPDjE7nMHhBefWebojed2Qkd3jufIwawEXdExeyA9qk5AXVIJFS9erIx1zQXsNKVJNQJRV4YY9eaQVTz1iyJtIHf9nExKDRkCvwuvGbyD8ndR4RyDGBR2SVPGU").toUpperCase();

        map.put("sign",s);

        System.out.println(map);

        String body = HttpRequest.post("http://cas.wnhlf.top/api/pay/unifiedOrder").form(map).execute().body();
        System.out.println(body);

    }

    @Test
    public void  test(){
        //amount=1&appId=620f7e7760b21cfa52cfe73f&body=测试&currency=cny&mchNo=M1645182535&mchOrderNo=mho1649348194668&reqTime=1649348194668&signType=MD5&subject=商品标题测试&version=1.0&wayCode=ALI_H5&key=PVDuEHvPDjE7nMHhBefWebojed2Qkd3jufIwawEXdExeyA9qk5AXVIJFS9erIx1zQXsNKVJNQJRV4YY9eaQVTz1iyJtIHf9nExKDRkCvwuvGbyD8ndR4RyDGBR2SVPGU
        //amount=1&appId=624c218063c8dd6c5a5ecac9&body=测试&currency=cny&mchNo=M1649156459&mchOrderNo=mho1649163161400&reqTime=1649163161400&signType=MD5&subject=商品标题测试&version=1.0&wayCode=ALI_QR&key=wnRjg0HR4kUH8mvWfl8scHSSe4U7G2ZytR8OMM5IJ1ztv25gtyxe51d6ehHzhV8yd9Ep5KUsGUa6r1lBfp9TJzd4P5ulYAFudrjluDMz9CzxR1HIxjtoOjCuqOb4dVaQ
        //amount=1&appId=624c218063c8dd6c5a5ecac9&body=测试&currency=cny&mchNo=M1649156459&mchOrderNo=mho1649163161400&reqTime=1649163161400&signType=MD5&subject=商品标题测试&version=1.0&wayCode=ALI_QR
        String s = SecureUtil.md5("amount=100&appId=620f7e7760b21cfa52cfe73f&body=接口调试[M1645182535商户联调]&channelExtra={\"payDataType\":\"payurl\"}&clientIp=39.144.228.197&currency=CNY&divisionMode=0&mchNo=M1645182535&mchOrderNo=M16497516428615508&notifyUrl=http://mch.rodinia.top/api/anon/paytestNotify/payOrder&reqTime=1649751649&signType=MD5&subject=接口调试[M1645182535商户联调]&version=1.0&wayCode=WX_H5&key=PVDuEHvPDjE7nMHhBefWebojed2Qkd3jufIwawEXdExeyA9qk5AXVIJFS9erIx1zQXsNKVJNQJRV4YY9eaQVTz1iyJtIHf9nExKDRkCvwuvGbyD8ndR4RyDGBR2SVPGU");
        System.out.println(s);
    }
}
