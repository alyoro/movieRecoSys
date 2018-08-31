package com.example.movieRecoSys.neo4j.repository;

import com.example.movieRecoSys.neo4j.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);

}
