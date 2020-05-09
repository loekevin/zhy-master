package com.finest.terminal.servers;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by kezy on 2020/4/7.
 */
@Component
public class FeignServerFactoryImpl implements FallbackFactory<FeignServer>{

    @Override
    public FeignServer create(Throwable throwable) {
        return new FeignServer() {
            public String testRealRibbon(String content){
                return content + ", it's fallback Factory with feign";
            }
        };

    }
}
