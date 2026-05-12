package com.example.gramakalyanasports.ui.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MatchResultScreen(

    onSaveClick: (
        String,
        String,
        String
    ) -> Unit,

    onBackClick: () -> Unit
) {

    var winner by remember {
        mutableStateOf("")
    }

    var finalScore by remember {
        mutableStateOf("")
    }

    var manOfTheMatch by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Match Result"
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )


        OutlinedTextField(
            value = winner,
            onValueChange = {
                winner = it
            },
            label = {
                Text("Winner Team")
            },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        OutlinedTextField(
            value = finalScore,
            onValueChange = {
                finalScore = it
            },
            label = {
                Text("Final Score")
            },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        OutlinedTextField(
            value = manOfTheMatch,
            onValueChange = {
                manOfTheMatch = it
            },
            label = {
                Text("Man of the Match")
            },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(
            modifier = Modifier.height(24.dp)
        )


        Button(
            onClick = {

                if (

                    winner.isBlank() ||

                    finalScore.isBlank() ||

                    manOfTheMatch.isBlank()

                ) {

                    return@Button
                }


                onSaveClick(

                    winner,

                    finalScore,

                    manOfTheMatch
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Save Result")
        }


        TextButton(
            onClick = onBackClick
        ) {

            Text("Back")
        }
    }
}