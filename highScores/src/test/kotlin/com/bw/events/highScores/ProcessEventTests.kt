package com.bw.events.highScores

import com.bw.events.highScores.command.RecordGameStarted
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.cloud.contract.stubrunner.StubFinder
import org.springframework.cloud.contract.stubrunner.StubTrigger
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.messaging.support.MessageBuilder
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@ExtendWith(SpringExtension::class)
@AutoConfigureStubRunner(ids = [ "com.bw.events:creator:0.0.1-SNAPSHOT" ])
class ProcessEventTests {

    @MockBean
    private lateinit var fakeRecordGameStarted: RecordGameStarted

    @Autowired
    private lateinit var sink: Sink

    @Autowired
    private lateinit var stubTrigger: StubTrigger

    @Test
    fun itPassesAGameStartedEventToTheProcessor() {
        stubTrigger.trigger("game.started.event")

        verify(fakeRecordGameStarted).execute("56fb5ce5-d669-45ea-8f6d-04a52a5d2e7e")
    }

    @Test
    fun itIgnoresAnEventWithAnotherType() {
        val payload = "{\"id\":\"fake-id\",\"type\":\"NotStarted\",\"instant\":\"fake-instant\"}"

        sink.input().send(MessageBuilder.withPayload(payload).build())

        verify(fakeRecordGameStarted, times(0)).execute(anyString())
    }

}