package com.example.gramakalyanasports.ui.screens.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PlayerStatsScreen(

    onSaveClick: (
        String,
        String,
        String,
        String
    ) -> Unit,

    onBackClick: () -> Unit

) {

    var name by remember { mutableStateOf("") }

    var matches by remember { mutableStateOf("") }

    var points by remember { mutableStateOf("") }

    var awards by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("Player Name")
            }
        )


        OutlinedTextField(
            value = matches,
            onValueChange = {
                matches = it
            },
            label = {
                Text("Matches")
            }
        )


        OutlinedTextField(
            value = points,
            onValueChange = {
                points = it
            },
            label = {
                Text("Points")
            }
        )


        OutlinedTextField(
            value = awards,
            onValueChange = {
                awards = it
            },
            label = {
                Text("Awards")
            }
        )


        Button(

            onClick = {

                if (

                    name.isBlank() ||

                    matches.isBlank() ||

                    points.isBlank() ||

                    awards.isBlank()

                ) {

                    return@Button
                }


                onSaveClick(

                    name,

                    matches,

                    points,

                    awards
                )
            },
        ) {

            Text("Save Stats")
        }


        TextButton(
            onClick = onBackClick
        ) {

            Text("Back")
        }
    }
}