package com.finest.terminal.servers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述：
 *    远程调用
 * Created by kezy on 2020/4/7.
 */
@FeignClient(value = "app-interface" , fallbackFactory = FeignServerFactoryImpl.class )
public interface FeignServer {

    @RequestMapping(value ="/testRealRibbon",method= RequestMethod.GET)
    String testRealRibbon(@RequestParam("content") String content);
}
