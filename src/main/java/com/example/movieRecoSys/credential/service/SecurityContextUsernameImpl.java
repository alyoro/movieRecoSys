package com.example.movieRecoSys.credential.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextUsernameImpl implements SecurityContextUsername{

    public String getUsernameFromSecurityContext(){
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
