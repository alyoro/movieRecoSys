package com.example.movieRecoSys.credential.controller;


import com.example.movieRecoSys.credential.domain.ApplicationUser;
import com.example.movieRecoSys.credential.repository.ApplicationUserRepository;
import com.example.movieRecoSys.exception.UserAlreadyInDataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) throws UserAlreadyInDataBaseException{
        if(null == applicationUserRepository.findByUsername(user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            applicationUserRepository.save(user);
        }
        else throw new UserAlreadyInDataBaseException();
    }

}
