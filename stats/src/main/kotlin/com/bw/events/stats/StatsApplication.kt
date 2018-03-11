package com.bw.events.stats

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Sink

@SpringBootApplication
@EnableBinding(Sink::class)
class StatsApplication

fun main(args: Array<String>) {
    runApplication<StatsApplication>(*args)
}
