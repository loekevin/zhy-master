package com.finest.gateway.dto;

import java.util.Date;

/**
 * Created by kezy on 2019-11-22.
 * 服务限流实体类
 */
public class RateLimitDto {

    private String rateLimitId;
    /**
     * 限流路径，支持通配符，示例 /user/**
     */
    private String limitPath;
    /**
     * 每秒限流频率
     */
    private Integer permitsPerSecond;
    /**
     * 限流等待超时时间，单位s
     */
    private Integer permitsTimeOut;
    /**
     * 排序
     */
    private Integer orderNo;
    /**
     * 最大线程数
     */
    private Integer maxThread;
    /**
     * 创建时间
     */
    private Date CreateTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建人
     * */
    private String creator;

    /**
     * 修改人
     */
    private String mediator;

    public String getMediator() {
        return mediator;
    }

    public void setMediator(String mediator) {
        this.mediator = mediator;
    }

    public String getRateLimitId() {
        return rateLimitId;
    }

    public void setRateLimitId(String rateLimitId) {
        this.rateLimitId = rateLimitId;
    }

    public String getLimitPath() {
        return limitPath;
    }

    public void setLimitPath(String limitPath) {
        this.limitPath = limitPath;
    }

    public Integer getPermitsPerSecond() {
        return permitsPerSecond;
    }

    public void setPermitsPerSecond(Integer permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
    }

    public Integer getPermitsTimeOut() {
        return permitsTimeOut;
    }

    public void setPermitsTimeOut(Integer permitsTimeOut) {
        this.permitsTimeOut = permitsTimeOut;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getMaxThread() {
        return maxThread;
    }

    public void setMaxThread(Integer maxThread) {
        this.maxThread = maxThread;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


}
