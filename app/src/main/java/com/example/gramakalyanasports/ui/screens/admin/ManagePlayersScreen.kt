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


@Composable
fun ManagePlayersScreen(

    onAddPlayerClick: (
        String,
        String,
        String
    ) -> Unit,

    onBackClick: () -> Unit
) {

    var playerName by remember {
        mutableStateOf("")
    }

    var jerseyNumber by remember {
        mutableStateOf("")
    }

    var teamName by remember {
        mutableStateOf("")
    }

    val playerList = remember {
        mutableStateListOf<Triple<String,String,String>>()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = playerName,
            onValueChange = {
                playerName = it
            },
            label = {
                Text("Player Name")
            },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        OutlinedTextField(
            value = jerseyNumber,
            onValueChange = {
                jerseyNumber = it
            },
            label = {
                Text("Jersey Number")
            },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        OutlinedTextField(
            value = teamName,
            onValueChange = {
                teamName = it
            },
            label = {
                Text("Team Name")
            },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        Button(
            onClick = {

                if (

                    playerName.isNotBlank() &&

                    teamName.isNotBlank() &&

                    playerList.count {

                        it.third == teamName

                    } < 11

                ) {

                    onAddPlayerClick(

                        playerName,

                        jerseyNumber,

                        teamName
                    )

                    playerList.add(

                        Triple(

                            playerName,

                            jerseyNumber,

                            teamName
                        )
                    )

                    playerName = ""
                    jerseyNumber = ""
                    teamName = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Add Player")
        }


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(playerList) { player ->

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

                            Text(player.first)

                            Text("Jersey: ${player.second}")

                            Text("Team: ${player.third}")

                            Text(
                                "Player #${playerList.indexOf(player) + 1}"
                            )
                        }


                        TextButton(

                            onClick = {

                                playerList.remove(player)
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