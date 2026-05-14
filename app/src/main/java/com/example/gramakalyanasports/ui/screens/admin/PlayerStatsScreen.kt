package com.example.gramakalyanasports.ui.screens.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PlayerStatsScreen(

playerName: String,

matches: Int,

runs: Int,

wickets: Int,

awards: Int,

onBackClick: () -> Unit

) {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {

        OutlinedTextField(

            value = playerName,

            onValueChange = { },

            readOnly = true,

            modifier = Modifier.fillMaxWidth(),

            label = {
                Text("Player Name")
            }
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        OutlinedTextField(

            value = matches.toString(),

            onValueChange = { },

            readOnly = true,

            modifier = Modifier.fillMaxWidth(),

            label = {
                Text("Matches")
            }
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        OutlinedTextField(

            value = runs.toString(),

            onValueChange = { },

            readOnly = true,

            modifier = Modifier.fillMaxWidth(),

            label = {
                Text("Runs")
            }
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        OutlinedTextField(

            value = wickets.toString(),

            onValueChange = { },

            readOnly = true,

            modifier = Modifier.fillMaxWidth(),

            label = {
                Text("Wickets")
            }
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        OutlinedTextField(

            value = awards.toString(),

            onValueChange = { },

            readOnly = true,

            modifier = Modifier.fillMaxWidth(),

            label = {
                Text("Awards")
            }
        )


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
