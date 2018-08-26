package com.example.movieRecoSys.neo4j.repository;

import com.example.movieRecoSys.neo4j.domain.Node;
import com.example.movieRecoSys.neo4j.domain.Watched;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface WatchedRepository extends PagingAndSortingRepository<Watched, Long> {
    @Query("match (u:User)-[w:WATCHED]->(m:Movie) where ID(u)={0} and ID(m)={1} return w")
    List<Watched> findByStartNodeAndEndNode(Node startNode, Node endNode);
}
