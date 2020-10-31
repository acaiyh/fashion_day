package com.fashion.uaa.service.impl;

import com.fashion.uaa.dao.ClientDao;
import com.fashion.uaa.service.UAAClientDetails;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UAAClientDetailsService implements ClientDetailsService {

    @Resource
    private ClientDao clientDao;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        UAAClientDetails clientDetails = clientDao.loadClientByClientId(clientId);
        return clientDetails;
    }

}
