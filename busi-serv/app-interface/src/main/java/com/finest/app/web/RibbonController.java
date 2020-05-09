package com.finest.app.web;

import com.finest.app.servers.FeignServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kezy on 2020/4/8.
 */
@Slf4j
@RestController
@RequestMapping(value ="/ribbonResource")
public class RibbonController {

    @Autowired
    private FeignServer feignServer;

    @RequestMapping("/testFeignServer/{keyWord}")
    public String testFeignServer(@PathVariable("keyWord") String keyWord){
        return feignServer.testRealRibbon(keyWord);
    }
}
