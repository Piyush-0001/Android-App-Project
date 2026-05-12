package com.example.gramakalyanasports.data.model

data class ScheduledMatch(

    val teamA: String = "",

    val teamB: String = "",

    val date: String = "",

    val venue: String = "",

    val scoreA: Int = 0,

    val scoreB: Int = 0,

    val status: String = "LIVE"
)