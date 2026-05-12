package com.example.gramakalyanasports.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MatchSetupScreen(

    onStartMatch: (
        Int

    ) -> Unit,

    onBackClick: () -> Unit
) {

    var overs by remember {

        mutableStateOf("2")
    }


    Column(

        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(16.dp)

    ) {


        OutlinedTextField(

            value = overs,

            onValueChange = {

                overs = it
            },

            label = {

                Text("Overs")
            },

            modifier = Modifier.fillMaxWidth()
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Button(

            onClick = {

                onStartMatch(

                    overs.toIntOrNull() ?: 2
                )
            },

            modifier = Modifier.fillMaxWidth()

        ) {

            Text(
                "Start Match"
            )
        }


        TextButton(
            onClick = onBackClick
        ) {

            Text("Back")
        }
    }
}