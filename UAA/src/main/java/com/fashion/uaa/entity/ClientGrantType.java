package com.fashion.uaa.entity;

import java.util.Date;

public class ClientGrantType {

    private String id;
    private String clientId;
    private String authorizedGrantTypes;
    private Date createTime;
    private Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
