package com.example.movieRecoSys.repository;

import com.example.movieRecoSys.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    List<User> findByUsername(String username);

}
