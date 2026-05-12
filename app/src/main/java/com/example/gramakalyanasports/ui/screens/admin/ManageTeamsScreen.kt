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
fun ManageTeamsScreen(

    onAddTeamClick: (String) -> Unit,

    onBackClick: () -> Unit
) {

    var teamName by remember {
        mutableStateOf("")
    }

    val teamList = remember {
        mutableStateListOf<String>()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

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

                if (teamName.isNotBlank()) {

                    onAddTeamClick(
                        teamName
                    )

                    teamList.add(teamName)

                    teamName = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Add Team")
        }


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(teamList) { team ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {

                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(team)

                        TextButton(

                            onClick = {

                                teamList.remove(team)
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