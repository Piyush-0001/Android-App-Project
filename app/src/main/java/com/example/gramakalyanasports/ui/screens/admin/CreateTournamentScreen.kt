package com.example.gramakalyanasports.ui.screens.admin

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
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
fun CreateTournamentScreen(

    onCreateClick: (
        String,
        String,
        String,
        String
    ) -> Unit,

    onBackClick: () -> Unit
) {

    var tournamentName by remember {
        mutableStateOf("")
    }

    var sportType by remember {
        mutableStateOf("")
    }

    var date by remember {
        mutableStateOf("")
    }

    var location by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Create Tournament"
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )


        OutlinedTextField(
            value = tournamentName,
            onValueChange = {
                tournamentName = it
            },
            label = {
                Text("Tournament Name")
            },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        OutlinedTextField(
            value = sportType,
            onValueChange = {
                sportType = it
            },
            label = {
                Text("Sport Type")
            },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        OutlinedTextField(
            value = date,
            onValueChange = {
                date = it
            },
            label = {
                Text("Date")
            },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        OutlinedTextField(
            value = location,
            onValueChange = {
                location = it
            },
            label = {
                Text("Location")
            },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(
            modifier = Modifier.height(24.dp)
        )


        Button(

            onClick = {

                if (

                    tournamentName.isBlank() ||

                    sportType.isBlank() ||

                    date.isBlank() ||

                    location.isBlank()

                ) {

                    return@Button
                }


                onCreateClick(

                    tournamentName,

                    sportType,

                    date,

                    location
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Create")
        }


        TextButton(
            onClick = onBackClick
        ) {

            Text("Back")
        }
    }
}