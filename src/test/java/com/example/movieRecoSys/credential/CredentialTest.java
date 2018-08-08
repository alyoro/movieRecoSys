package com.example.movieRecoSys.credential;


import com.example.movieRecoSys.credential.repository.ApplicationUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CredentialTest {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Test
    public void checkConnectionAndFindUserInPostgresqlTest() {
        Long id = 1L;
        Assert.assertEquals("test",applicationUserRepository.findByUsername("test").getPassword());
    }


}
