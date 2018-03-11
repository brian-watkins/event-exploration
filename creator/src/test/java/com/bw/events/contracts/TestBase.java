package com.bw.events.contracts;

import com.bw.events.creator.CreatorApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CreatorApplication.class)
@AutoConfigureMessageVerifier
public abstract class TestBase {

    @Autowired
    ApplicationContext applicationContext;

    WebTestClient webTestClient;

    @Before
    public void before() {
        webTestClient = WebTestClient.bindToApplicationContext(applicationContext)
                .build();
    }

    public void emitGameStarted() {
        webTestClient.post().uri("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody("{}")
                .exchange();
    }

}
