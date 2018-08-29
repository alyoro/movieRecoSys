package com.example.movieRecoSys.neo4j.repository;

import com.example.movieRecoSys.neo4j.domain.Node;
import com.example.movieRecoSys.neo4j.domain.Watched;
import com.example.movieRecoSys.neo4j.domain.WatchedDB;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WatchedRepository extends PagingAndSortingRepository<Watched, Long> {
    @Query("match (u:User)-[w:WATCHED]->(m:Movie) where ID(u)={0} and ID(m)={1} return  ID(u) as startNodeId, ID(m) as endNodeId, w.score as score")
    WatchedDB findByStartNodeAndEndNode(Node startNode, Node endNode);
}
