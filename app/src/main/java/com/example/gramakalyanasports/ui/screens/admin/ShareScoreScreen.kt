package com.example.gramakalyanasports.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramakalyanasports.data.model.Result


@Composable
fun ShareScoreScreen(

    result: Result,

    onShareClick: () -> Unit,

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

            text = result.winningText,

            fontSize = 24.sp
        )


        Spacer(
            modifier = Modifier.height(16.dp)
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


        Button(
            onClick = onShareClick
        ) {

            Text("Share on WhatsApp")
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