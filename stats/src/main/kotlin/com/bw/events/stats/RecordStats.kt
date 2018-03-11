package com.bw.events.stats

import org.springframework.stereotype.Component

@Component
class RecordStats {

    fun execute(type: String) {
        println("Recording stat type: ${type}")
    }

}