package com.example.gramakalyanasports.ui.screens.fan

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramakalyanasports.data.model.ScheduledMatch
import com.example.gramakalyanasports.data.model.Score


@Composable
fun LiveMatchesScreen(

    match: ScheduledMatch,

    score: Score,

    onBackClick: () -> Unit

) {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {

        Text(

            text = "🔴 LIVE",

            fontSize = 24.sp
        )


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        Text(

            text = "${score.teamA} vs ${score.teamB}",

            fontSize = 28.sp
        )


        Spacer(
            modifier = Modifier.height(24.dp)
        )


        Text(

            text = "${score.runs}/${score.wickets}",

            fontSize = 42.sp
        )


        Text(
            text = "${score.overs}.${score.balls} Overs"
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        Text(

            text =

                "${score.striker.name}* " +

                        "${score.striker.runs}" +

                        " (${score.striker.balls})"
        )


        Text(

            text =

                "${score.nonStriker.name} " +

                        "${score.nonStriker.runs}" +

                        " (${score.nonStriker.balls})"
        )


        Spacer(
            modifier = Modifier.height(8.dp)
        )


        Text(

            text =

                "${score.bowler.name} " +

                        "${score.bowler.wickets} wkts"
        )


        Text(
            text = "4s: ${score.fours}   6s: ${score.sixes}"
        )


        Text(
            text = "RR: %.2f".format(
                score.runRate
            )
        )


        Spacer(
            modifier = Modifier.height(24.dp)
        )


        TextButton(
            onClick = onBackClick
        ) {

            Text("Back")
        }
    }
}