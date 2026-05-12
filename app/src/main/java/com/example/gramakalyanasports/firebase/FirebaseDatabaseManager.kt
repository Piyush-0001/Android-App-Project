package com.example.gramakalyanasports.firebase

import com.example.gramakalyanasports.data.model.Tournament
import com.google.firebase.database.FirebaseDatabase
import com.example.gramakalyanasports.data.model.Team
import com.example.gramakalyanasports.data.model.Player
import com.example.gramakalyanasports.data.model.Score
import com.example.gramakalyanasports.data.model.ScheduledMatch
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.example.gramakalyanasports.data.model.Result
import com.example.gramakalyanasports.data.model.PlayerStat

object FirebaseDatabaseManager {

    private val database = FirebaseDatabase.getInstance()

    private val tournamentRef =
        database.getReference("tournaments")

    private val teamRef =
        database.getReference("teams")


    private val playerRef =
        database.getReference("players")

    private val matchRef =
        database.getReference("scheduledMatch")

    private val scoreRef =
        database.getReference("liveScore")

    private val resultRef =
        database.getReference("matchResult")

    private val playerStatRef =
        database.getReference("playerStats")


    fun saveTournament(

        tournament: Tournament,

        onSuccess: () -> Unit,

        onFailure: (String) -> Unit

    ) {

        val id = tournamentRef.push().key ?: return


        tournamentRef.child(id)

            .setValue(tournament)

            .addOnSuccessListener {

                onSuccess()
            }

            .addOnFailureListener {

                onFailure(
                    it.message ?: "Save Failed"
                )
            }
    }

    fun saveTeam(

        team: Team,

        onSuccess: () -> Unit,

        onFailure: (String) -> Unit

    ) {

        val id = teamRef.push().key ?: return


        teamRef.child(id)

            .setValue(team)

            .addOnSuccessListener {

                onSuccess()
            }

            .addOnFailureListener {

                onFailure(
                    it.message ?: "Save Failed"
                )
            }
    }



    fun savePlayer(

        player: Player,

        onSuccess: () -> Unit,

        onFailure: (String) -> Unit

    ) {

        val id = playerRef.push().key ?: return


        playerRef.child(id)

            .setValue(player)

            .addOnSuccessListener {

                onSuccess()
            }

            .addOnFailureListener {

                onFailure(
                    it.message ?: "Save Failed"
                )
            }
    }

    fun saveScore(

        score: Score,

        onSuccess: () -> Unit,

        onFailure: (String) -> Unit

    ) {

        scoreRef

            .setValue(score)

            .addOnSuccessListener {

                onSuccess()
            }

            .addOnFailureListener {

                onFailure(
                    it.message ?: "Save Failed"
                )
            }
    }

    fun saveScheduledMatch(

        match: ScheduledMatch,

        onSuccess: () -> Unit,

        onFailure: (String) -> Unit

    ) {

        matchRef

            .setValue(match)

            .addOnSuccessListener {

                onSuccess()
            }

            .addOnFailureListener {

                onFailure(
                    it.message ?: "Save Failed"
                )
            }
    }

    fun observeLiveMatch(

        onDataChanged: (ScheduledMatch) -> Unit

    ) {

        matchRef.addValueEventListener(

            object : ValueEventListener {

                override fun onDataChange(

                    snapshot: DataSnapshot

                ) {

                    val match = snapshot.getValue(
                        ScheduledMatch::class.java
                    )

                    match?.let {

                        onDataChanged(it)
                    }
                }


                override fun onCancelled(

                    error: DatabaseError
                ) {

                }
            }
        )
    }

    fun saveResult(

        result: Result,

        onSuccess: () -> Unit,

        onFailure: (String) -> Unit

    ) {

        resultRef

            .setValue(result)

            .addOnSuccessListener {

                onSuccess()
            }

            .addOnFailureListener {

                onFailure(
                    it.message ?: "Save Failed"
                )
            }
    }

    fun observeMatchResult(

        onDataChanged: (Result) -> Unit

    ) {

        resultRef.addValueEventListener(

            object : ValueEventListener {

                override fun onDataChange(

                    snapshot: DataSnapshot

                ) {

                    val result = snapshot.getValue(
                        Result::class.java
                    )

                    result?.let {

                        onDataChanged(it)
                    }
                }


                override fun onCancelled(

                    error: DatabaseError
                ) {

                }
            }
        )
    }

    fun savePlayerStat(

        stat: PlayerStat,

        onSuccess: () -> Unit,

        onFailure: (String) -> Unit

    ) {

        playerStatRef

            .setValue(stat)

            .addOnSuccessListener {

                onSuccess()
            }

            .addOnFailureListener {

                onFailure(
                    it.message ?: "Save Failed"
                )
            }
    }

    fun observePlayerStat(

        onDataChanged: (PlayerStat) -> Unit

    ) {

        playerStatRef.addValueEventListener(

            object : ValueEventListener {

                override fun onDataChange(

                    snapshot: DataSnapshot

                ) {

                    val stat = snapshot.getValue(
                        PlayerStat::class.java
                    )

                    stat?.let {

                        onDataChanged(it)
                    }
                }


                override fun onCancelled(

                    error: DatabaseError
                ) {

                }
            }
        )
    }
    fun observeScore(

        onDataChanged: (Score) -> Unit

    ) {

        scoreRef.addValueEventListener(

            object : ValueEventListener {

                override fun onDataChange(

                    snapshot: DataSnapshot

                ) {

                    val score = snapshot.getValue(
                        Score::class.java
                    )

                    score?.let {

                        onDataChanged(it)
                    }
                }


                override fun onCancelled(

                    error: DatabaseError
                ) {

                }
            }
        )
    }
}