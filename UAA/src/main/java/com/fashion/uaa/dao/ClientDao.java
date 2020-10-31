package com.fashion.uaa.dao;

import com.fashion.uaa.service.UAAClientDetails;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDao {

    UAAClientDetails loadClientByClientId(@Param("clientId") String clientId);
}
