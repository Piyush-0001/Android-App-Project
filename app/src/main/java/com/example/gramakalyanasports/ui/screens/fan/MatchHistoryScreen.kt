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

    val resultText =

        if (

            result.winningText.isBlank()

        )

            "⏳ No match completed yet"

        else

            "${result.winningText}\n\n" +

                    "${result.teamA} vs ${result.teamB}\n\n" +

                    "Score: " +

                    "${result.finalRuns}/" +

                    "${result.finalWickets}\n" +

                    "Overs: " +

                    "${result.finalOvers}." +

                    "${result.finalBalls}"


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
            text = resultText
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
