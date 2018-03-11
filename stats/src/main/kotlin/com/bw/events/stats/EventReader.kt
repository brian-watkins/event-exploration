package com.bw.events.stats

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.stereotype.Component

@Component
class EventReader @Autowired constructor (
        private val recordStats: RecordStats
) {

    @StreamListener(target = Sink.INPUT)
    fun readEvent (event: GameEvent) {
        println("Got an event: ${event}")
        recordStats.execute(event.type)
    }

}