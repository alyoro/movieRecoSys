package com.example.movieRecoSys.credential.service;

import org.springframework.stereotype.Service;

@Service
public interface SecurityContextUsername {

    String getUsernameFromSecurityContext();
}
