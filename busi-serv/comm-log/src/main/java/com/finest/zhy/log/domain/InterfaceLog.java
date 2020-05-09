package com.finest.zhy.log.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by Administrator on 2018/6/26 0026.
 */
@Entity
@Table(name = "INTERFACE_LOG", schema = "ZHY_ANBAO", catalog = "")
public class InterfaceLog {
    private String id;
    private String requestMethod;
    private String requestParams;
    private Date requestTime;
    private String requestIp;
    private String requestUrl;
    private String requestDesc;
    private String responseParams;
    private Long timeDif;
    private Date responseTime;
    private String issuccess;
    private String remark;
    private String imei;
    private String syscode;
    private String requestId;
    private String author;
    private String userId;



    public InterfaceLog(){
        issuccess="0"; //默认成功;
    }


    @Id
    @Column(name = "ID")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AUTHOR")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "REQUEST_METHOD")
    public String getRequestMethod() {
        return requestMethod;
    }

    @Basic
    @Column(name = "USER_ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    @Basic
    @Column(name = "REQUEST_PARAMS")
    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    @Basic
    @Column(name = "REQUEST_TIME")
    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    @Basic
    @Column(name = "REQUEST_IP")
    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    @Basic
    @Column(name = "REQUEST_URL")
    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Basic
    @Column(name = "REQUEST_DESC")
    public String getRequestDesc() {
        return requestDesc;
    }

    public void setRequestDesc(String requestDesc) {
        this.requestDesc = requestDesc;
    }

    @Basic
    @Column(name = "RESPONSE_PARAMS")
    public String getResponseParams() {
        return responseParams;
    }

    public void setResponseParams(String responseParams) {
        this.responseParams = responseParams;
    }

    @Basic
    @Column(name = "TIME_DIF")
    public Long getTimeDif() {
        return timeDif;
    }

    public void setTimeDif(Long timeDif) {
        this.timeDif = timeDif;
    }

    @Basic
    @Column(name = "RESPONSE_TIME")
    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    @Basic
    @Column(name = "ISSUCCESS")
    public String getIssuccess() {
        return issuccess;
    }

    public void setIssuccess(String issuccess) {
        this.issuccess = issuccess;
    }

    @Basic
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "IMEI")
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Basic
    @Column(name = "SYSCODE")
    public String getSyscode() {
        return syscode;
    }

    public void setSyscode(String syscode) {
        this.syscode = syscode;
    }

    @Basic
    @Column(name = "REQUEST_ID")
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterfaceLog that = (InterfaceLog) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (requestMethod != null ? !requestMethod.equals(that.requestMethod) : that.requestMethod != null)
            return false;
        if (requestParams != null ? !requestParams.equals(that.requestParams) : that.requestParams != null)
            return false;
        if (requestTime != null ? !requestTime.equals(that.requestTime) : that.requestTime != null) return false;
        if (requestIp != null ? !requestIp.equals(that.requestIp) : that.requestIp != null) return false;
        if (requestUrl != null ? !requestUrl.equals(that.requestUrl) : that.requestUrl != null) return false;
        if (requestDesc != null ? !requestDesc.equals(that.requestDesc) : that.requestDesc != null) return false;
        if (responseParams != null ? !responseParams.equals(that.responseParams) : that.responseParams != null)
            return false;
        if (timeDif != null ? !timeDif.equals(that.timeDif) : that.timeDif != null) return false;
        if (responseTime != null ? !responseTime.equals(that.responseTime) : that.responseTime != null) return false;
        if (issuccess != null ? !issuccess.equals(that.issuccess) : that.issuccess != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (imei != null ? !imei.equals(that.imei) : that.imei != null) return false;
        if (syscode != null ? !syscode.equals(that.syscode) : that.syscode != null) return false;
        if (requestId != null ? !requestId.equals(that.requestId) : that.requestId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (requestMethod != null ? requestMethod.hashCode() : 0);
        result = 31 * result + (requestParams != null ? requestParams.hashCode() : 0);
        result = 31 * result + (requestTime != null ? requestTime.hashCode() : 0);
        result = 31 * result + (requestIp != null ? requestIp.hashCode() : 0);
        result = 31 * result + (requestUrl != null ? requestUrl.hashCode() : 0);
        result = 31 * result + (requestDesc != null ? requestDesc.hashCode() : 0);
        result = 31 * result + (responseParams != null ? responseParams.hashCode() : 0);
        result = 31 * result + (timeDif != null ? timeDif.hashCode() : 0);
        result = 31 * result + (responseTime != null ? responseTime.hashCode() : 0);
        result = 31 * result + (issuccess != null ? issuccess.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (imei != null ? imei.hashCode() : 0);
        result = 31 * result + (syscode != null ? syscode.hashCode() : 0);
        result = 31 * result + (requestId != null ? requestId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InterfaceLog{" +
                "id='" + id + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", requestParams='" + requestParams + '\'' +
                ", requestTime=" + requestTime +
                ", requestIp='" + requestIp + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", requestDesc='" + requestDesc + '\'' +
                ", responseParams='" + responseParams + '\'' +
                ", timeDif=" + timeDif +
                ", responseTime=" + responseTime +
                ", issuccess='" + issuccess + '\'' +
                ", remark='" + remark + '\'' +
                ", imei='" + imei + '\'' +
                ", syscode='" + syscode + '\'' +
                ", requestId='" + requestId + '\'' +
                ", author='" + author + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
