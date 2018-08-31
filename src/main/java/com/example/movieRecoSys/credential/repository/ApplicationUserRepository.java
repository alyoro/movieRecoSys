package com.example.movieRecoSys.credential.repository;

import com.example.movieRecoSys.credential.domain.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}