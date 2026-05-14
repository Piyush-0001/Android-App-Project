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
import androidx.compose.material3.DropdownMenu


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

    var strikerExpanded by remember {

        mutableStateOf(
            false
        )
    }


    var nonStrikerExpanded by remember {

        mutableStateOf(
            false
        )
    }

    var wicketBatsmanExpanded by remember {

        mutableStateOf(
            false
        )
    }


    var wicketBatsmanName by remember {

        mutableStateOf("")
    }

    var playersSelected by remember {

        mutableStateOf(
            striker.name.isNotBlank()
        )
    }

    var waitingForWicketBatsman by remember {

        mutableStateOf(
            false
        )
    }

    var waitingForNextOverBowler by remember {

        mutableStateOf(false)

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

    var lastBallType by remember {

        mutableStateOf("")

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

    fun updateBall() {
        balls++

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

            waitingForNextOverBowler = true
        }

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
            if (

                waitingForWicketBatsman

            ) {

                Button(

                    onClick = {

                        wicketBatsmanExpanded = true
                    },

                    modifier = Modifier.fillMaxWidth()

                ) {

                    Text(

                        if (

                            wicketBatsmanName.isBlank()

                        )

                            "Select Next Batsman ▼"
                        else

                            "$wicketBatsmanName ▼"
                    )
                }


                DropdownMenu(

                    expanded = wicketBatsmanExpanded,

                    onDismissRequest = {

                        wicketBatsmanExpanded = false
                    }

                ) {

                    battingPlayers
                        .filter {

                            it != strikerName
                        }
                        .forEach {

                            playerName ->

                        DropdownMenuItem(

                            text = {

                                Text(playerName)
                            },

                            onClick = {

                                wicketBatsmanName = playerName

                                wicketBatsmanExpanded = false
                            }
                        )
                    }
                }


                Spacer(
                    modifier = Modifier.height(12.dp)
                )


                Button(

                    onClick = {

                        if (

                            wicketBatsmanName.isBlank()

                        ) {

                            return@Button
                        }


                        if (

                            waitingForNextOverBowler

                        ){

                            nonStriker = Player(

                                name = wicketBatsmanName
                            )

                        } else {

                            striker = Player(

                                name = wicketBatsmanName
                            )

                        }


                        waitingForWicketBatsman = false

                        if (

                        waitingForNextOverBowler

                        ) {

                        playersSelected = false

                    } else {
                        playersSelected = true

                    }


                        wicketBatsmanName = ""


                        onScoreUpdate(

                        score.copy(

                            striker = striker,

                            nonStriker = nonStriker,

                            bowler = bowler,

                            runs = runs,

                            wickets = wickets,

                            overs = overs,

                            balls = balls,

                            innings = innings,

                            target = target,

                            battingTeam = battingTeam,

                            bowlingTeam = bowlingTeam
                        )

                        )

                    }

                ) {

                    Text("Continue")
                }
            }else {



                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Button(

                    onClick = {

                        strikerExpanded = true
                    },

                    modifier = Modifier.fillMaxWidth()

                ) {

                    Text(


                        if (

                            strikerName.isBlank()

                        )

                            "Select Striker ▼"
                        else

                            "$strikerName ▼"
                    )
                }


                DropdownMenu(

                    expanded = strikerExpanded,

                    onDismissRequest = {

                        strikerExpanded = false
                    }

                ) {

                    battingPlayers.forEach {

                            playerName ->

                        DropdownMenuItem(

                            text = {

                                Text(playerName)
                            },

                            onClick = {

                                strikerName = playerName

                                strikerExpanded = false
                            }
                        )
                    }
                }


                Spacer(
                    modifier = Modifier.height(12.dp)
                )


                Button(

                    onClick = {

                        nonStrikerExpanded = true
                    },

                    modifier = Modifier.fillMaxWidth()

                ) {

                    Text(

                        if (

                            nonStrikerName.isBlank()

                        )

                            "Select Non-Striker ▼"
                        else

                            "$nonStrikerName ▼"
                    )
                }


                DropdownMenu(

                    expanded = nonStrikerExpanded,

                    onDismissRequest = {

                        nonStrikerExpanded = false
                    }

                ) {

                    battingPlayers
                        .filter {

                            it != strikerName
                        }
                        .forEach {

                                playerName ->

                        DropdownMenuItem(

                            text = {

                                Text(playerName)
                            },

                            onClick = {

                                nonStrikerName = playerName

                                nonStrikerExpanded = false
                            }
                        )
                    }
                }


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

                                strikerName.isBlank() ||

                                nonStrikerName.isBlank() ||

                                bowlerName.isBlank() ||

                                strikerName == nonStrikerName

                            ) {

                                return@Button
                            }


                            striker = Player(

                                name = strikerName
                            )


                            nonStriker = Player(

                                name = nonStrikerName
                            )

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

                        !playersSelected &&

                        !waitingForWicketBatsman


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
                            waitingForNextOverBowler = false


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
                        lastBallType = "1"
                        updateBall()
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
                        lastBallType = "2"
                        updateBall()
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
                        lastBallType = "3"
                        updateBall()
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

                        val updatedWickets = wickets + 1
                        lastBallType = "W"
                        updateBall()

                        wickets = updatedWickets
                        partnershipRuns = 0

                        partnershipBalls = 0
                        wicketBatsmanExpanded = false

                        wicketBatsmanName = ""

                        waitingForWicketBatsman = true

                        playersSelected = false

                        bowler = bowler.copy(

                            wickets = bowler.wickets + 1
                        )


                        if (

                            updatedWickets >= battingPlayers.size - 1

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

                                waitingForWicketBatsman = false

                                wicketBatsmanName = ""

                                strikerName = ""

                                nonStrikerName = ""

                                bowlerName = ""

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

                                runs = runs,

                                wickets = wickets,

                                overs = overs,

                                balls = balls,

                                innings = innings,

                                target = target,

                                battingTeam = battingTeam,

                                bowlingTeam = bowlingTeam
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
                        lastBallType = "4"
                        updateBall()
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
                        lastBallType = "6"
                        updateBall()
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

                        partnershipBalls++
                        lastBallType = "DOT"

                        striker = striker.copy(

                            balls = striker.balls + 1

                        )

                        updateBall()


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

                                waitingForWicketBatsman = false

                                wicketBatsmanName = ""

                                strikerName = ""

                                nonStrikerName = ""

                                bowlerName = ""

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
                        lastBallType = "WD"
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
                        lastBallType = "NB"
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

                        when (

                            lastBallType

                        ) {

                            "1" -> {

                                runs -= 1

                                partnershipRuns -= 1

                                partnershipBalls -= 1

                                if (

                                    balls == 0

                                ) {

                                    overs--

                                    balls = 5

                                } else {

                                    balls--

                                }

                                striker = striker.copy(

                                    runs = striker.runs - 1,

                                    balls = striker.balls - 1
                                )

                                swapStrike()
                            }

                            "2" -> {

                                runs -= 2

                                partnershipRuns -= 2

                                partnershipBalls -= 1

                                if (

                                    balls == 0

                                ) {

                                    overs--

                                    balls = 5

                                } else {

                                    balls--

                                }

                                striker = striker.copy(

                                    runs = striker.runs - 2,

                                    balls = striker.balls - 1
                                )
                            }

                            "3" -> {

                                runs -= 3

                                partnershipRuns -= 3

                                partnershipBalls -= 1

                                if (

                                    balls == 0

                                ) {

                                    overs--

                                    balls = 5

                                } else {

                                    balls--

                                }

                                striker = striker.copy(

                                    runs = striker.runs - 3,

                                    balls = striker.balls - 1
                                )

                                swapStrike()
                            }

                            "4" -> {

                                runs -= 4

                                fours--

                                partnershipRuns -= 4

                                partnershipBalls -= 1

                                if (

                                    balls == 0

                                ) {

                                    overs--

                                    balls = 5

                                } else {

                                    balls--

                                }

                                striker = striker.copy(

                                    runs = striker.runs - 4,

                                    balls = striker.balls - 1
                                )
                            }

                            "6" -> {

                                runs -= 6

                                sixes--

                                partnershipRuns -= 6

                                partnershipBalls -= 1

                                if (

                                    balls == 0

                                ) {

                                    overs--

                                    balls = 5

                                } else {

                                    balls--

                                }

                                striker = striker.copy(

                                    runs = striker.runs - 6,

                                    balls = striker.balls - 1
                                )
                            }

                            "DOT" -> {

                                partnershipBalls--

                                if (

                                    balls == 0

                                ) {

                                    overs--

                                    balls = 5

                                } else {

                                    balls--

                                }

                                striker = striker.copy(

                                    balls = striker.balls - 1
                                )
                            }

                            "WD" -> {

                                runs--

                                wides--
                            }

                            "NB" -> {

                                runs--

                                noBalls--
                            }

                            "W" -> {

                                wickets--

                                partnershipBalls--

                                if (

                                    balls == 0

                                ) {

                                    overs--

                                    balls = 5

                                } else {

                                    balls--

                                }
                            }
                        }

                        lastBallType = ""

                        onScoreUpdate(

                            score.copy(

                                striker = striker,

                                nonStriker = nonStriker,

                                bowler = bowler,

                                runs = runs,

                                wickets = wickets,

                                overs = overs,

                                balls = balls
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

                        waitingForWicketBatsman = false

                        wicketBatsmanName = ""

                        strikerName = ""

                        nonStrikerName = ""

                        bowlerName = ""

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
