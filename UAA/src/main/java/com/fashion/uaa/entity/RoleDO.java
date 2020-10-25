package com.fashion.uaa.entity;

import java.util.Date;
import java.util.Set;

public class RoleDO {

    private String id;
    private String roleName;
    private String description;
    private Date createTime;
    private Date updateTime;
    private String status;
    private Set<PermissionDO> permissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<PermissionDO> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionDO> permissions) {
        this.permissions = permissions;
    }
}
