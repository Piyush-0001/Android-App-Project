package com.example.gramakalyanasports.ui.screens.fan

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun FanDashboardScreen(

    onLiveMatchesClick: () -> Unit,

    onHistoryClick: () -> Unit,

    onStatsClick: () -> Unit

) {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Text(

            text = "🏟",

            fontSize = 34.sp
        )


        Spacer(
            modifier = Modifier.height(8.dp)
        )


        Text(

            text = "Grama Kalyana Sports",

            fontSize = 26.sp,

            fontWeight = FontWeight.Bold
        )


        Spacer(
            modifier = Modifier.height(4.dp)
        )


        Text(

            text = "Live village sports updates",

            fontSize = 15.sp
        )


        Spacer(
            modifier = Modifier.height(40.dp)
        )


        DashboardButton(

            text = "🔴 Live Matches",

            onClick = onLiveMatchesClick
        )


        DashboardButton(

            text = "🏆 Match History",

            onClick = onHistoryClick
        )


        DashboardButton(

            text = "📈 Player Stats",

            onClick = onStatsClick
        )

        DashboardButton(

            text = "🏆 Match Result",

            onClick = onHistoryClick
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

        Text(

            text = text,

            fontSize = 20.sp,

            fontWeight = FontWeight.Bold
        )
    }


    Spacer(
        modifier = Modifier.height(20.dp)
    )
}