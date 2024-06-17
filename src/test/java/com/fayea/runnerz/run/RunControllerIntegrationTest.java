package com.fayea.runnerz.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RunControllerIntegrationTest {
    @LocalServerPort
    int randomServerPort;


    RestClient restClient;
    @Autowired
    JdbcClientRunRepository jdbcClientRunRepository;

    @BeforeEach
    void setUp() {
        restClient = RestClient.create("http://localhost:" + randomServerPort);
    }
    @Test
    void findAll(){
        List<Run> runs= restClient.get().uri("/api/runs").retrieve().body(new ParameterizedTypeReference<>() {
        });
        assertEquals(10,runs.size());
    }

}
