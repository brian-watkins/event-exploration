package com.bw.events.highScores.command

import org.springframework.stereotype.Component

@Component("RecordGameStarted")
class RecordGameStarted {

    fun execute(gameId: String) {
        println("Started Game: ${gameId}")
    }
}