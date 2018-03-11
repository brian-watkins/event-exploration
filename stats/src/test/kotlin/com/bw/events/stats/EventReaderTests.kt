package com.bw.events.stats

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.cloud.contract.stubrunner.StubTrigger
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@ExtendWith(SpringExtension::class)
@AutoConfigureStubRunner(ids = [ "com.bw.events:creator:0.0.1-SNAPSHOT" ])
class EventReaderTests {

    @MockBean
    private lateinit var fakeRecordStats: RecordStats

    @Autowired
    private lateinit var stubTrigger: StubTrigger

    @Test
    fun itPassesAGameStartedEventToTheProcessor() {
        stubTrigger.trigger("game.started.event")

        Mockito.verify(fakeRecordStats).execute("Started")
    }

}