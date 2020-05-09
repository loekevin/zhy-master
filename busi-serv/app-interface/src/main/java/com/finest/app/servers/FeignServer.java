package com.finest.app.servers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述：
 *    远程调用
 * Created by kezy on 2020/4/7.
 */
@FeignClient(value = "resources-interface" , fallbackFactory = FeignServerFactoryImpl.class )
public interface FeignServer {

    @RequestMapping(value ="/resource/testFeignServer/{keyWord}")
    String testRealRibbon(@RequestParam("keyWord") String keyWord);
}
