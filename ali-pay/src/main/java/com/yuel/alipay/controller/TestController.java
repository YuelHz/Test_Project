package com.yuel.alipay.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @RequestMapping("/test")
    public Map test(){
        Map map = new HashMap<>();
        map.put("code",200);
        map.put("data","{dskjfkjhasd}");
        map.put("aaa","fdkjsajf");
        return map;
    }
}
