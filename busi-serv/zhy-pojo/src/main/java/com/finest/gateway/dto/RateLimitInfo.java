package com.finest.gateway.dto;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by kezy on 2019-11-22
 * 限流信息类
 */
public class RateLimitInfo {

    private RateLimiter rateLimiter;

    private RateLimitDto rateLimitDto;

    private long lastUpdateTime;

    public RateLimitInfo(RateLimiter rateLimiter,RateLimitDto rateLimitDto, long lastUpdateTime) {
        this.rateLimiter = rateLimiter;
        this.rateLimitDto = rateLimitDto;
        this.lastUpdateTime = lastUpdateTime;
    }

    public RateLimiter getRateLimiter() {
        return rateLimiter;
    }

    public void setRateLimiter(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    public RateLimitDto getRateLimitDto() {
        return rateLimitDto;
    }

    public void setRateLimitDto(RateLimitDto rateLimitDto) {
        this.rateLimitDto = rateLimitDto;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
