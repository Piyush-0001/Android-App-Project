package com.example.gramakalyanasports.ui.screens.fan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

                } else {

                    Text(
                        "👤 ${stat.name}"
                    )


                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )


                    Text(
                        "🎮 Matches: ${stat.matches}"
                    )


                    Text(
                        "🔥 Runs: ${stat.points}"
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
