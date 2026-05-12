package com.example.gramakalyanasports.ui.screens.fan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramakalyanasports.data.model.Result


@Composable
fun MatchHistoryScreen(

    result: Result,

    onBackClick: () -> Unit

) {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment =
            Alignment.CenterHorizontally,

        verticalArrangement =
            Arrangement.Center

    ) {

        Text(

            text = "🏆 Match Result",

            fontSize = 24.sp
        )


        Spacer(
            modifier = Modifier.height(24.dp)
        )


        Text(

            text = result.winningText
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(

            text =

                "${result.teamA} vs ${result.teamB}",

            fontSize = 20.sp
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        Text(

            text =

                "${result.finalRuns}/" +

                        "${result.finalWickets}"
        )


        Text(

            text =

                "${result.finalOvers}." +

                        "${result.finalBalls} Overs"
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