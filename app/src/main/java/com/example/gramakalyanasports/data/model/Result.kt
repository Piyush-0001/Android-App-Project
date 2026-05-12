package com.example.gramakalyanasports.data.model


data class Result(

    val teamA: String = "",

    val teamB: String = "",


    val winner: String = "",

    val winningText: String = "",

    val winMargin: String = "",


    val finalRuns: Int = 0,

    val finalWickets: Int = 0,

    val finalOvers: Int = 0,

    val finalBalls: Int = 0
)