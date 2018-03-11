package com.bw.events.creator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source

@SpringBootApplication
@EnableBinding(Source::class)
class CreatorApplication

fun main(args: Array<String>) {
    runApplication<CreatorApplication>(*args)
}
