package com.example.movieRecoSys.neo4j.repository;

import com.example.movieRecoSys.neo4j.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    List<User> findByUsername(String username);

}
