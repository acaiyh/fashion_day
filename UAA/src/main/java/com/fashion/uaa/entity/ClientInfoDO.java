package com.fashion.uaa.entity;

import java.util.Date;
import java.util.Set;

public class ClientInfoDO {

    private String clientId;
    private String clientSecret;
    private String resourceIds;
    private int accessTokenValidity;
    private int refreshTokenValidity;
    private String additionalInformation;
    private Date createTime;
    private int archived;
    private int trusted;
    private String autoApprove;
    private Set<ClientScopeDO> scopes;
    private Set<ClientGrantType> grantTypes;
    private Set<ClientRedirectUri> rediirectUris;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public int getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(int accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public int getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(int refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getArchived() {
        return archived;
    }

    public void setArchived(int archived) {
        this.archived = archived;
    }

    public int getTrusted() {
        return trusted;
    }

    public void setTrusted(int trusted) {
        this.trusted = trusted;
    }

    public String getAutoApprove() {
        return autoApprove;
    }

    public void setAutoApprove(String autoApprove) {
        this.autoApprove = autoApprove;
    }

    public Set<ClientScopeDO> getScopes() {
        return scopes;
    }

    public void setScopes(Set<ClientScopeDO> scopes) {
        this.scopes = scopes;
    }

    public Set<ClientGrantType> getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(Set<ClientGrantType> grantTypes) {
        this.grantTypes = grantTypes;
    }

    public Set<ClientRedirectUri> getRediirectUris() {
        return rediirectUris;
    }

    public void setRediirectUris(Set<ClientRedirectUri> rediirectUris) {
        this.rediirectUris = rediirectUris;
    }
}
