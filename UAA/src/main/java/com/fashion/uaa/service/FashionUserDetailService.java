package com.fashion.uaa.service;

import com.fashion.uaa.dao.UserDao;
import com.fashion.uaa.entity.PermissionDO;
import com.fashion.uaa.entity.RoleDO;
import com.fashion.uaa.entity.UserDO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service
public class FashionUserDetailService implements UserDetailsService {

    @Resource
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //get user info with username
        System.out.println("Login by username : " + username);
        //UserDetails userDetails = User.withUsername("zhangsan").password("$2a$10$sE1m0TNfWCCLqqP3cJnra.v9ZwWrnI9eARAiTVevkFmwSMoZr9WJG").authorities("user").build();
        UserDO userDO = userDao.loadUserByUsername(username);
        if(userDO != null){
            Set<String> permissionStrs = new HashSet<>();
            Set<RoleDO> roles = userDO.getRoles();
            if(roles != null && roles.size() > 0){
                roles.forEach(role -> {
                    Set<PermissionDO> permissions = role.getPermissions();
                    if(permissions != null && permissions.size() > 0){
                        permissions.forEach(permission -> {
                            permissionStrs.add(permission.getCode());
                        });
                    }
                });
            }
            String[] authorities = permissionStrs.toArray(new String[permissionStrs.size()]);
            UserDetails userDetails = User.withUsername(userDO.getUsername()).password(userDO.getPassword()).authorities(authorities).build();
            return userDetails;
        }else{
            throw new UsernameNotFoundException("用户不存在");
        }
    }
}
