package com.fashion.uaa.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class UAAClientDetails implements ClientDetails {

    private String clientId;
    private String clientSecret;
    private Set<String> resourceIds;
    private int accessTokenValidity;
    private int refreshTokenValidity;
    private Set<String> scope;
    private Set<String> grantTypes;
    private Set<String> redirectUris;

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public Set<String> getScope() {
        return this.scope;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return this.grantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return this.redirectUris;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    @Override
    public Set<String> getResourceIds() {
        return this.resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return this.clientSecret != null;
    }

    @Override
    public boolean isScoped() {
        return this.scope != null && !this.scope.isEmpty();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValidity;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValidity;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

}
