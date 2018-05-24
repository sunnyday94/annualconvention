package com.chunmi.annualconvention.po;

import java.util.Date;

public class Schedule {
    private Long id;

    private String scheduleUrl;

    private Date createTime;

    private Date updateTime;

    private String delFlag;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScheduleUrl() {
        return scheduleUrl;
    }

    public void setScheduleUrl(String scheduleUrl) {
        this.scheduleUrl = scheduleUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
     
}