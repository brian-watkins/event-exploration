package com.bw.events.creator

import java.time.Instant
import java.util.*

data class GameStartedEvent (
        val id: String = UUID.randomUUID().toString(),
        val type: String = "Started",
        val instant: String = Instant.now().toString()
)