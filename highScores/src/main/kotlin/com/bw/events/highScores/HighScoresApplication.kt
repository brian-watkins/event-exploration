package com.bw.events.highScores

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Sink

@SpringBootApplication
@EnableBinding(Sink::class)
class HighScoresApplication

fun main(args: Array<String>) {
    runApplication<HighScoresApplication>(*args)
}
