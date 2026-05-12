package com.example.gramakalyanasports.data.model
import com.example.gramakalyanasports.data.model.Player


data class Score(

    val teamA: String = "",

    val teamB: String = "",

    val striker: Player = Player(),

    val nonStriker: Player = Player(),

    val bowler: Player = Player(),

    val teamAPlayers: List<String> = emptyList(),

    val teamBPlayers: List<String> = emptyList(),

    val strikerIndex: Int = 0,

    val nonStrikerIndex: Int = 1,

    val nextBatsmanIndex: Int = 2,

    val tossWinner: String = "",

    val tossDecision: String = "",

    val battingTeam: String = "",

    val bowlingTeam: String = "",

    val venue: String = "",

    val matchDate: String = "",

    val runs: Int = 0,

    val wickets: Int = 0,


    val overs: Int = 0,

    val balls: Int = 0,


    val fours: Int = 0,

    val sixes: Int = 0,


    val runRate: Double = 0.0,

    val innings: Int = 1,

    val target: Int = 0,

    val maxOvers: Int = 2,

    val firstInningsRuns: Int = 0,

    val wides: Int = 0,

    val noBalls: Int = 0,


    val status: String = "LIVE"
)