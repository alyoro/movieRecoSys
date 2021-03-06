package com.example.movieRecoSys.credential.controller;

import com.example.movieRecoSys.credential.domain.ApplicationUser;
import com.example.movieRecoSys.credential.repository.ApplicationUserRepository;
import com.example.movieRecoSys.neo4j.domain.User;
import com.example.movieRecoSys.neo4j.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class ApplicationUserController {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * Endpoint used to register new user
     * @param user json contains username and password
     * @return status of action
     */
    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody ApplicationUser user){
        if(null == applicationUserRepository.findByUsername(user.getUsername()) && null == userRepository.findByUsername(user.getUsername())) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setAdmin(false);
            user.setActive(true);
            userRepository.save(new User(user.getUsername()));
            applicationUserRepository.save(user);
            return ResponseEntity.ok(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }
}


