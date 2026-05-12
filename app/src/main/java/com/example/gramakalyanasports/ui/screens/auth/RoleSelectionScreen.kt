package com.example.gramakalyanasports.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RoleSelectionScreen(
    onAdminClick: () -> Unit,
    onFanClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = onAdminClick,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Admin / Scorer")
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            onClick = onFanClick,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Fan / Spectator")
        }
    }
}