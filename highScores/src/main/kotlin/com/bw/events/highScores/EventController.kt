package com.bw.events.highScores

import com.bw.events.highScores.command.RecordGameStarted
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.stereotype.Component

@Component
class EventController @Autowired constructor(
        private val recordGameStarted: RecordGameStarted
){

    @StreamListener(target = Sink.INPUT)
    fun processEvent (event: GameEvent) {
        println("Got an event: ${event}")
        if (event.type == "Started") {
            recordGameStarted.execute(event.id)
        }
    }

}