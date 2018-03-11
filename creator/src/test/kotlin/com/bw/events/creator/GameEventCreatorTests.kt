package com.bw.events.creator

import com.jayway.jsonpath.JsonPath
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.stream.messaging.Source
import org.springframework.cloud.stream.test.binder.MessageCollector
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient


@SpringBootTest
@ExtendWith(SpringExtension::class)
class GameEventCreatorTests {

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    @Autowired
    private lateinit var messageCollector: MessageCollector

    @Autowired
    private lateinit var source: Source

    private lateinit var webTestClient: WebTestClient

    @BeforeEach
    fun setup() {
        webTestClient = WebTestClient.bindToApplicationContext(applicationContext)
                .build()
    }

    @Test
    fun testCreateGameEvent() {
        val response = webTestClient.post().uri("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody("{}")
                .exchange()

        val payload = messageCollector.forChannel(source.output()).poll().payload

        assertThat(payloadProperty(payload, "$.type")).isEqualTo("Started")

        val eventId = payloadProperty(payload, "$.id")

        response.expectStatus().isCreated
                .expectHeader().valueEquals("location", "/games/${eventId}")
                .expectBody().isEmpty
    }

    fun payloadProperty(payload: Any, path: String): String {
        return JsonPath.parse(payload as String).read(path, String::class.java)
    }

}