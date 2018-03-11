package com.bw.events.creator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.stream.messaging.Source
import org.springframework.http.ResponseEntity
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class GameEventCreator @Autowired constructor(val channel: Source) {

    @RequestMapping("/games", method = [ RequestMethod.POST ])
    fun gameStart () : ResponseEntity<String> {
        val event = GameStartedEvent()

        val message = MessageBuilder
                .withPayload(event)
                .setHeader("content-type", "application/json")
                .build()

        channel.output().send(message)

        return ResponseEntity.created(URI.create("/games/${event.id}")).build()
    }

}