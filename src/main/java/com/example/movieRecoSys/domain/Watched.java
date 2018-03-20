package com.example.movieRecoSys.domain;

import org.neo4j.ogm.annotation.RelationshipEntity;

@RelationshipEntity(type = "WATCHED")
public class Watched extends Edge{

}
