package com.example.movieRecoSys.neo4j.repository;

import com.example.movieRecoSys.neo4j.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    /**
     * Getting user from neo4j database with specific username
     * @param username name of user
     * @return user from neo4j
     */
    User findByUsername(String username);

}
