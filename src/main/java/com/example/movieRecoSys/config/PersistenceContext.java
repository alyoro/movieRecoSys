package com.example.movieRecoSys.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Class building context and connection with neo4j database
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.example.movieRecoSys.neo4j")
@EnableNeo4jRepositories("com.example.movieRecoSys.neo4j.repository")
public class PersistenceContext {

    @Autowired
    private Environment env;


    @Bean
    public SessionFactory getSessionFactory() {
        return new SessionFactory(configuration(), "com.example.movieRecoSys.neo4j.domain");
    }

    @Bean
    public Neo4jTransactionManager transactionManager() throws Exception {
        return new Neo4jTransactionManager(getSessionFactory());
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        return new org.neo4j.ogm.config.Configuration.Builder()
                .uri(env.getProperty("neo4j.datasoruce.uri"))
                .build();
    }



}
