package com.example.gramakalyanasports.ui.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdminDashboardScreen(

    onTournamentClick: () -> Unit,

    onTeamClick: () -> Unit,

    onPlayerClick: () -> Unit,

    onScheduleClick: () -> Unit,

    onLiveScoreClick: () -> Unit,

    onResultClick: () -> Unit,

    onStatsClick: () -> Unit,

    onShareClick: () -> Unit

) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.Center
    ) {

        DashboardButton(
            text = "Create Tournament",
            onClick = onTournamentClick
        )

        DashboardButton(
            text = "Manage Teams",
            onClick = onTeamClick
        )

        DashboardButton(
            text = "Manage Players",
            onClick = onPlayerClick
        )

        DashboardButton(
            text = "Match Schedule",
            onClick = onScheduleClick
        )

        DashboardButton(
            text = "Live Score",
            onClick = onLiveScoreClick
        )

        DashboardButton(
            text = "Match Result",
            onClick = onResultClick
        )

        DashboardButton(
            text = "Player Stats",
            onClick = onStatsClick
        )

        DashboardButton(
            text = "Share Score",
            onClick = onShareClick
        )
    }
}


@Composable
private fun DashboardButton(
    text: String,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(text)
    }

    Spacer(
        modifier = Modifier.height(12.dp)
    )
}