package com.example.gramakalyanasports.ui.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gramakalyanasports.data.model.ScheduledMatch

@Composable
fun MatchScheduleScreen(

    onScheduleClick: (
        String,
        String,
        String,
        String
    ) -> Unit,

    onBackClick: () -> Unit
){

    var teamA by remember { mutableStateOf("") }
    var teamB by remember { mutableStateOf("") }
    var matchDate by remember { mutableStateOf("") }
    var venue by remember { mutableStateOf("") }


    val matchList = remember {
        mutableStateListOf<ScheduledMatch>()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = teamA,
            onValueChange = { teamA = it },
            label = { Text("Team A") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))


        OutlinedTextField(
            value = teamB,
            onValueChange = { teamB = it },
            label = { Text("Team B") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))


        OutlinedTextField(
            value = matchDate,
            onValueChange = { matchDate = it },
            label = { Text("Match Date") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))


        OutlinedTextField(
            value = venue,
            onValueChange = { venue = it },
            label = { Text("Venue") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))


        Button(
            onClick = {

                if (

                    teamA.isBlank() ||

                    teamB.isBlank() ||

                    matchDate.isBlank() ||

                    venue.isBlank()

                ) {

                    return@Button
                }


                onScheduleClick(

                    teamA,

                    teamB,

                    matchDate,

                    venue
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Schedule Match")
        }


        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(matchList) { match ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {

                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column {

                            Text("${match.teamA} vs ${match.teamB}")

                            Text("Date: ${match.date}")

                            Text("Venue: ${match.venue}")


                        }


                        TextButton(

                            onClick = {

                                matchList.remove(match)
                            }
                        ) {

                            Text("Delete")
                        }
                    }
                }
            }
        }


        TextButton(
            onClick = onBackClick
        ) {

            Text("Back")
        }
    }
}