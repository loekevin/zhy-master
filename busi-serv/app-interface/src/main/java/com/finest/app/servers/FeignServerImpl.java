package com.finest.app.servers;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kezy on 2020/4/7.
 */
@Component
public class FeignServerImpl implements FeignServer {

    public String testRealRibbon(@RequestParam("content") String content) {
        return content + ", it's fallback with feign";
    }
}