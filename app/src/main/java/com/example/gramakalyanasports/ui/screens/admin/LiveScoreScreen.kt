package com.example.gramakalyanasports.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramakalyanasports.data.model.ScheduledMatch
import com.example.gramakalyanasports.data.model.Score
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.gramakalyanasports.data.model.Player
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField


@Composable
fun LiveScoreScreen(

    match: ScheduledMatch,

    score: Score,

    onScoreUpdate: (
        Score
    ) -> Unit,

    onEndMatchClick: () -> Unit,

    onBackClick: () -> Unit

) {

    var runs by remember { mutableStateOf(score.runs) }

    var wickets by remember { mutableStateOf(score.wickets) }

    var overs by remember { mutableStateOf(score.overs) }

    var balls by remember { mutableStateOf(score.balls) }

    var fours by remember { mutableStateOf(score.fours) }

    var sixes by remember { mutableStateOf(score.sixes) }

    var innings by remember {

        mutableStateOf(
            score.innings
        )
    }


    var target by remember {

        mutableStateOf(
            score.target
        )
    }

    var maxOvers by remember {

        mutableStateOf(
            score.maxOvers
        )
    }

    var striker by remember {

        mutableStateOf(
            score.striker
        )
    }


    var nonStriker by remember {

        mutableStateOf(
            score.nonStriker
        )
    }



    var bowler by remember {

        mutableStateOf(
            score.bowler
        )
    }

    var lastOverBowler by remember {

        mutableStateOf("")
    }

    var firstInningsRuns by remember {

        mutableStateOf(
            score.firstInningsRuns
        )
    }

    var wides by remember {

        mutableStateOf(
            score.wides
        )
    }


    var noBalls by remember {

        mutableStateOf(
            score.noBalls
        )
    }


    var tossWinner by remember {

        mutableStateOf(
            score.tossWinner
        )
    }
    var tossSelected by remember {

        mutableStateOf(
            score.tossWinner.isNotBlank()
        )
    }


    var battingTeam by remember {

        mutableStateOf(
            score.battingTeam
        )
    }


    var bowlingTeam by remember {

        mutableStateOf(
            score.bowlingTeam
        )
    }

    var strikerName by remember {

        mutableStateOf("")
    }

    var nonStrikerName by remember {

        mutableStateOf("")
    }

    var bowlerName by remember {

        mutableStateOf("")
    }

    var bowlerExpanded by remember {

        mutableStateOf(
            false
        )
    }

    var playersSelected by remember {

        mutableStateOf(
            striker.name.isNotBlank()
        )
    }

    var nextBatsmanIndex by remember {

        mutableStateOf(
            score.nextBatsmanIndex
        )
    }

    var partnershipRuns by remember {

        mutableStateOf(
            0
        )
    }


    var partnershipBalls by remember {

        mutableStateOf(
            0
        )
    }

    val battingPlayers =

        if (

            battingTeam == score.teamA

        )

            score.teamAPlayers

        else

            score.teamBPlayers

    val bowlingPlayers =

        if (

            bowlingTeam == score.teamA

        )

            score.teamAPlayers

        else

            score.teamBPlayers

    fun swapStrike() {

        val temp = striker

        striker = nonStriker

        nonStriker = temp
    }


    val runRate = remember(

        runs,
        overs,
        balls

    ) {

        val totalBalls = (overs * 6) + balls

        if (

            totalBalls == 0

        )

            0.0
        else

            (runs * 6.0) / totalBalls
    }

    val ballsBowled =

        (overs * 6) + balls


    val totalMatchBalls =

        maxOvers * 6


    val ballsLeft =

        totalMatchBalls - ballsBowled


    val runsNeeded =

        target - runs

    val requiredRunRate =

        if (

            innings == 2 &&

            ballsLeft > 0 &&

            runs < target

        )

            (runsNeeded * 6.0) /

                    ballsLeft

        else

            0.0


    Column(

        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Top

    ) {

        Text(

            text = "${match.teamA} vs ${match.teamB}",

            fontSize = 28.sp
        )

        if (

            !tossSelected

        ) {

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceEvenly

            ) {

                Button(

                    onClick = {

                        tossWinner = match.teamA

                        tossSelected = true
                    }

                ) {

                    Text(
                        "${match.teamA} Toss"
                    )
                }


                Button(

                    onClick = {

                        tossWinner = match.teamB

                        tossSelected = true
                    }

                ) {

                    Text(
                        "${match.teamB} Toss"
                    )
                }
            }
        }

        if (

            tossSelected &&

            battingTeam.isBlank()

        ) {

                Row(

                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceEvenly

                ) {

                    Button(

                        onClick = {

                            battingTeam = tossWinner


                            bowlingTeam =

                                if (

                                    tossWinner ==
                                    match.teamA

                                )

                                    match.teamB
                                else

                                    match.teamA


                            onScoreUpdate(

                                score.copy(

                                    tossWinner = tossWinner,

                                    battingTeam = battingTeam,

                                    bowlingTeam = bowlingTeam
                                )
                            )
                        }

                    ) {

                        Text("Bat")
                    }


                    Button(

                        onClick = {

                            bowlingTeam = tossWinner


                            battingTeam =

                                if (

                                    tossWinner ==
                                    match.teamA

                                )

                                    match.teamB
                                else

                                    match.teamA


                            onScoreUpdate(

                                score.copy(

                                    tossWinner = tossWinner,

                                    battingTeam = battingTeam,

                                    bowlingTeam = bowlingTeam
                                )
                            )
                        }

                    ) {

                        Text("Bowl")
                    }
                }
            }



        Spacer(
            modifier = Modifier.height(12.dp)
        )


        Text(
            text = "Toss: $tossWinner"
        )


        Text(
            text = "Batting: $battingTeam"
        )


        Text(
            text = "Bowling: $bowlingTeam"
        )

        if (

            battingTeam.isNotBlank() &&

            !playersSelected

        ) {

            Spacer(
                modifier = Modifier.height(12.dp)
            )



            Button(

                onClick = {

                    bowlerExpanded =
                        true
                },

                modifier = Modifier.fillMaxWidth()

            ) {

                Text(

                    if (

                        bowlerName.isBlank()

                    )

                        "Select Bowler ▼"

                    else

                        "$bowlerName ▼"
                )
            }


            androidx.compose.material3.DropdownMenu(

                expanded = bowlerExpanded,

                onDismissRequest = {

                    bowlerExpanded =
                        false
                }

            ) {

                bowlingPlayers.forEach {

                        playerName ->

                    DropdownMenuItem(

                        text = {

                            Text(
                                playerName
                            )
                        },

                        onClick = {

                            bowlerName =
                                playerName

                            bowlerExpanded =
                                false
                        }
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            if (

                striker.name.isBlank()

            ) {

                Button(

                    onClick = {

                        if (

                            bowlerName.isBlank()

                        ) {

                            return@Button
                        }

                        if (

                            battingPlayers.size >= 2

                        ) {

                            striker = Player(

                                name = battingPlayers[0]
                            )

                            nonStriker = Player(

                                name = battingPlayers[1]
                            )
                        }

                        bowler = Player(

                            name = bowlerName
                        )

                        playersSelected = true


                        onScoreUpdate(

                            score.copy(

                                striker = striker,

                                nonStriker = nonStriker,

                                bowler = bowler
                            )
                        )
                    }

                ) {

                    Text(
                        "Start Innings"
                    )
                }
            }

            if (

                striker.name.isNotBlank() &&

                !playersSelected

            ) {

                Button(

                    onClick = {

                        if (

                            bowlerName == lastOverBowler

                        ) {

                            return@Button
                        }

                        if (

                            bowlerName.isBlank()

                        ) {

                            return@Button
                        }

                        bowler = Player(

                            name = bowlerName
                        )

                        playersSelected = true


                        onScoreUpdate(

                            score.copy(

                                bowler = bowler
                            )
                        )
                    }

                ) {

                    Text(
                        "Start Next Over"
                    )
                }
            }
        }

        Text(

            text =

                "${striker.name}*  " +

                        "${striker.runs}" +

                        " (${striker.balls})"
        )


        Text(

            text =

                "${nonStriker.name}   " +

                        "${nonStriker.runs}" +

                        " (${nonStriker.balls})"
        )


        Spacer(

            modifier = Modifier.height(
                8.dp
            )
        )

        Text(

            text =

                "Partnership: " +

                        "$partnershipRuns " +

                        "($partnershipBalls)"
        )


        Spacer(

            modifier = Modifier.height(
                8.dp
            )
        )


        Text(
            text =
                "Bowler: ${bowler.name} " +
                        "(${bowler.wickets} wkts)"
        )


        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(

            text = "Innings: $innings"
        )

        if (

            innings == 2

        ) {

            Text(

                text = "Target: $target"
            )
        }


        Text(

            text = "$runs/$wickets",

            fontSize = 40.sp
        )
        if (

            innings == 2 &&

            runs < target

        ) {

            Text(

                text =

                    "$battingTeam need " +

                            "$runsNeeded runs " +

                            "from $ballsLeft balls"
            )
        }


        Text(
            text = "$overs.$balls Overs"
        )


        Text(
            text = "4s: $fours   6s: $sixes"
        )


        Text(

            text =

                "CRR: %.2f".format(
                    runRate
                )
        )

        if (

            innings == 2 &&

            runs < target

        ) {

            Text(

                text =

                    "RRR: %.2f".format(

                        requiredRunRate
                    )
            )
        }

        Text(

            text =

                "Wd: $wides   Nb: $noBalls"
        )


        Spacer(
            modifier = Modifier.height(24.dp)
        )

        if (

            playersSelected

        ) {


            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceEvenly

            ) {

                Button(

                    onClick = {

                        runs++
                        partnershipRuns++

                        partnershipBalls++

                        striker = striker.copy(

                            runs = striker.runs + 1,

                            balls = striker.balls + 1
                        )
                        swapStrike()

                        if (

                            innings == 2 &&

                            runs >= target

                        ) {

                            onEndMatchClick()
                        }

                        onScoreUpdate(

                            score.copy(
                                striker = striker,

                                nonStriker = nonStriker,

                                bowler = bowler,

                                runs = runs,

                                runRate = runRate
                            )
                        )
                    }

                ) {

                    Text("+1")
                }

                Button(

                    onClick = {

                        runs += 2
                        partnershipRuns += 2

                        partnershipBalls++

                        striker = striker.copy(

                            runs = striker.runs + 2,

                            balls = striker.balls + 1
                        )


                        if (

                            innings == 2 &&

                            runs >= target

                        ) {

                            onEndMatchClick()
                        }


                        onScoreUpdate(

                            score.copy(

                                striker = striker,

                                nonStriker = nonStriker,

                                runs = runs
                            )
                        )
                    }

                ) {

                    Text("+2")
                }

                Button(

                    onClick = {

                        runs += 3
                        partnershipRuns += 3

                        partnershipBalls++

                        striker = striker.copy(

                            runs = striker.runs + 3,

                            balls = striker.balls + 1
                        )

                        swapStrike()


                        if (

                            innings == 2 &&

                            runs >= target

                        ) {

                            onEndMatchClick()
                        }


                        onScoreUpdate(

                            score.copy(

                                striker = striker,

                                nonStriker = nonStriker,

                                runs = runs
                            )
                        )
                    }

                ) {

                    Text("+3")
                }


                Button(

                    onClick = {

                        wickets++
                        partnershipRuns = 0

                        partnershipBalls = 0
                        if (

                            nextBatsmanIndex <

                            battingPlayers.size

                        ) {

                            striker = Player(

                                name =

                                    battingPlayers[
                                        nextBatsmanIndex
                                    ]
                            )

                            nextBatsmanIndex++
                        }
                        bowler = bowler.copy(

                            wickets = bowler.wickets + 1
                        )


                        if (

                            wickets >=

                            battingPlayers.size - 1

                        ) {

                            if (

                                innings == 1

                            ) {

                                firstInningsRuns = runs

                                target = runs + 1

                                innings = 2

                                partnershipRuns = 0

                                partnershipBalls = 0

                                runs = 0

                                wickets = 0

                                overs = 0

                                balls = 0

                                fours = 0

                                sixes = 0


                                val nextBatting =
                                    bowlingTeam

                                val nextBowling =
                                    battingTeam


                                battingTeam =
                                    nextBatting

                                bowlingTeam =
                                    nextBowling

                                striker = Player()

                                nonStriker = Player()

                                bowler = Player()

                                playersSelected = false

                                nextBatsmanIndex = 2

                            } else {

                                onEndMatchClick()
                            }
                        }


                        onScoreUpdate(

                            score.copy(

                                nextBatsmanIndex =
                                    nextBatsmanIndex,

                                striker = striker,

                                nonStriker = nonStriker,

                                bowler = bowler,

                                innings = innings,

                                target = target,

                                runs = runs,

                                wickets = wickets,

                                overs = overs,

                                balls = balls,

                                battingTeam =
                                    battingTeam,

                                bowlingTeam =
                                    bowlingTeam
                            )
                        )
                    }

                ) {

                    Text("W")
                }
            }


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceEvenly

            ) {

                Button(

                    onClick = {

                        runs += 4
                        partnershipRuns += 4

                        partnershipBalls++

                        striker = striker.copy(

                            runs = striker.runs + 4,

                            balls = striker.balls + 1
                        )


                        if (

                            innings == 2 &&

                            runs >= target

                        ) {

                            onEndMatchClick()
                        }


                        fours++


                        onScoreUpdate(

                            score.copy(
                                striker = striker,

                                nonStriker = nonStriker,

                                bowler = bowler,

                                runs = runs,

                                fours = fours,

                                runRate = runRate
                            )
                        )
                    }

                ) {

                    Text("4")
                }


                Button(

                    onClick = {

                        runs += 6
                        partnershipRuns += 6

                        partnershipBalls++

                        striker = striker.copy(

                            runs = striker.runs + 6,

                            balls = striker.balls + 1
                        )


                        if (

                            innings == 2 &&

                            runs >= target

                        ) {

                            onEndMatchClick()
                        }


                        sixes++


                        onScoreUpdate(

                            score.copy(
                                striker = striker,

                                nonStriker = nonStriker,

                                bowler = bowler,

                                runs = runs,

                                sixes = sixes,

                                runRate = runRate
                            )
                        )
                    }

                ) {

                    Text("6")
                }


                Button(

                    onClick = {

                        balls++
                        partnershipBalls++

                        striker = striker.copy(

                            balls = striker.balls + 1
                        )


                        if (

                            balls == 6

                        ) {

                            balls = 0

                            overs++

                            swapStrike()
                            lastOverBowler = bowler.name
                            bowler = Player()

                            bowlerName = ""

                            playersSelected = false
                        }


                        if (

                            overs >= maxOvers

                        ) {

                            if (

                                innings == 1

                            ) {

                                firstInningsRuns = runs

                                target = runs + 1

                                innings = 2

                                partnershipRuns = 0

                                partnershipBalls = 0

                                runs = 0

                                wickets = 0

                                overs = 0

                                balls = 0

                                fours = 0

                                sixes = 0


                                val nextBatting =
                                    bowlingTeam

                                val nextBowling =
                                    battingTeam


                                battingTeam =
                                    nextBatting

                                bowlingTeam =
                                    nextBowling

                                striker = Player()

                                nonStriker = Player()

                                bowler = Player()

                                playersSelected = false

                                nextBatsmanIndex = 2

                            } else {

                                onEndMatchClick()
                            }
                        }


                        onScoreUpdate(

                            score.copy(
                                striker = striker,

                                nonStriker = nonStriker,

                                bowler = bowler,

                                innings = innings,

                                target = target,

                                runs = runs,

                                wickets = wickets,

                                overs = overs,

                                balls = balls,

                                battingTeam =
                                    battingTeam,

                                bowlingTeam =
                                    bowlingTeam
                            )
                        )
                    }

                ) {

                    Text("Dot")
                }
            }


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceEvenly

            ) {

                Button(

                    onClick = {

                        wides++

                        runs++


                        if (

                            innings == 2 &&

                            runs >= target

                        ) {

                            onEndMatchClick()
                        }


                        onScoreUpdate(

                            score.copy(

                                runs = runs,

                                wides = wides
                            )
                        )
                    }

                ) {

                    Text("Wd")
                }


                Button(

                    onClick = {

                        noBalls++

                        runs++


                        if (

                            innings == 2 &&

                            runs >= target

                        ) {

                            onEndMatchClick()
                        }


                        onScoreUpdate(

                            score.copy(

                                runs = runs,

                                noBalls = noBalls
                            )
                        )
                    }

                ) {

                    Text("Nb")
                }


                Button(

                    onClick = {

                        if (

                            runs > 0

                        ) {

                            runs--
                        }


                        onScoreUpdate(

                            score.copy(

                                runs = runs,

                                wides = wides,

                                noBalls = noBalls,

                                runRate = runRate
                            )
                        )
                    }

                ) {

                    Text("Undo")
                }
            }


            Spacer(
                modifier = Modifier.height(16.dp)
            )

            if (

                innings == 1

            ) {

                Button(

                    onClick = {

                        firstInningsRuns = runs

                        target = runs + 1

                        innings = 2
                        partnershipRuns = 0

                        partnershipBalls = 0

                        runs = 0

                        wickets = 0

                        overs = 0

                        balls = 0

                        fours = 0

                        sixes = 0


                        val nextBatting =
                            bowlingTeam

                        val nextBowling =
                            battingTeam


                        battingTeam =
                            nextBatting

                        bowlingTeam =
                            nextBowling

                        striker = Player()

                        nonStriker = Player()

                        bowler = Player()

                        playersSelected = false

                        nextBatsmanIndex = 2


                        onScoreUpdate(

                            score.copy(

                                innings = innings,

                                target = target,

                                firstInningsRuns =
                                    firstInningsRuns,


                                battingTeam =
                                    battingTeam,

                                bowlingTeam =
                                    bowlingTeam,


                                runs = runs,

                                wickets = wickets,

                                overs = overs,

                                balls = balls,

                                fours = fours,

                                sixes = sixes
                            )
                        )
                    }

                ) {

                    Text(
                        "Switch Innings"
                    )
                }


                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }
        }


        Button(
            onClick = onEndMatchClick
        ) {

            Text("End Match")
        }


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        TextButton(
            onClick = onBackClick
        ) {

            Text("Back")
        }
    }
}
