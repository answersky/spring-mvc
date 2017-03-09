package com.java.domain;

import java.util.Date;

/**
 * Created by Administrator on 2016/8/17.
 */
public class UserInfo {
    private Long id;
    /**
     * 访客的ip
     */
    private String ip;
    /**
     * 访问的链接
     */
    private String url;
    /**
     * 访客的cookie
     */
    private String cookie;
    /**
     * 访问的开始时间
     */
    private Date sTime;
    /**
     * 结束时间
     */
    private Date eTime;
    /**
     * 访问的时长
     */
    private String time;
    /**
     * ip的地理位置
     */
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public Date getsTime() {
        return sTime;
    }

    public void setsTime(Date sTime) {
        this.sTime = sTime;
    }

    public Date geteTime() {
        return eTime;
    }

    public void seteTime(Date eTime) {
        this.eTime = eTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                ", cookie='" + cookie + '\'' +
                ", sTime=" + sTime +
                ", eTime=" + eTime +
                ", time='" + time + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
