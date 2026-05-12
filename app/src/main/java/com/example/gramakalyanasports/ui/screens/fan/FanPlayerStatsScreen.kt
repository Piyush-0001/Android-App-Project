package com.example.gramakalyanasports.ui.screens.fan

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gramakalyanasports.data.model.PlayerStat

@Composable
fun FanPlayerStatsScreen(

    stat: PlayerStat,

    onBackClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                if (

                    stat.name.isBlank()

                ) {

                    Text(

                        "⏳ Waiting for stats..."
                    )

                    return@Column
                }

                Text(

                    if (

                        stat.name.isBlank()

                    )

                        "📈 No Player Stats Yet"
                    else

                        "👤 ${stat.name}"
                )


                if (

                    stat.name.isNotBlank()

                ) {

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )


                    Text(

                        "🎮 Matches: ${stat.matches}"
                    )


                    Text(

                        "🔥 Points: ${stat.points}"
                    )


                    Text(

                        "🏅 Awards: ${stat.awards}"
                    )
                }
            }
        }


        Spacer(
            modifier = Modifier.weight(1f)
        )


        TextButton(
            onClick = onBackClick
        ) {

            Text("Back")
        }
    }
}