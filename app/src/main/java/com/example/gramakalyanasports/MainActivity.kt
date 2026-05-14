package com.example.gramakalyanasports

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gramakalyanasports.navigation.AppRoutes
import com.example.gramakalyanasports.ui.screens.admin.AdminDashboardScreen
import com.example.gramakalyanasports.ui.screens.auth.LoginScreen
import com.example.gramakalyanasports.ui.screens.auth.RoleSelectionScreen
import com.example.gramakalyanasports.ui.screens.auth.SplashScreen
import com.example.gramakalyanasports.ui.screens.admin.CreateTournamentScreen
import com.example.gramakalyanasports.ui.screens.admin.ManageTeamsScreen
import com.example.gramakalyanasports.ui.screens.admin.ManagePlayersScreen
import com.example.gramakalyanasports.ui.screens.admin.MatchScheduleScreen
import com.example.gramakalyanasports.ui.screens.admin.LiveScoreScreen
import com.example.gramakalyanasports.ui.screens.admin.PlayerStatsScreen
import com.example.gramakalyanasports.ui.screens.admin.ShareScoreScreen
import com.example.gramakalyanasports.ui.screens.fan.FanDashboardScreen
import com.example.gramakalyanasports.ui.screens.fan.LiveMatchesScreen
import com.example.gramakalyanasports.ui.screens.fan.MatchHistoryScreen
import com.example.gramakalyanasports.ui.screens.fan.FanPlayerStatsScreen
import com.example.gramakalyanasports.firebase.FirebaseAuthManager
import com.example.gramakalyanasports.data.model.Tournament
import com.example.gramakalyanasports.firebase.FirebaseDatabaseManager
import com.example.gramakalyanasports.data.model.Team
import com.example.gramakalyanasports.data.model.Player
import com.example.gramakalyanasports.data.model.ScheduledMatch
import androidx.compose.runtime.*
import com.example.gramakalyanasports.data.model.Result
import com.example.gramakalyanasports.data.model.PlayerStat
import android.content.Intent
import androidx.core.net.toUri
import java.net.URLEncoder
import com.example.gramakalyanasports.data.model.Score
import com.example.gramakalyanasports.ui.screens.admin.MatchSetupScreen
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()
            var isLoading by remember {

                mutableStateOf(
                    false
                )
            }
            var liveMatch by remember {

                mutableStateOf(
                    ScheduledMatch()
                )
            }
            var liveScore by remember {

                mutableStateOf(
                    Score()
                )
            }
            var matchResult by remember {

                mutableStateOf(
                    Result()
                )
            }
            var playerStat by remember {

                mutableStateOf(
                    PlayerStat()
                )
            }
            var squadMap by remember {

                mutableStateOf(

                    mapOf<String, List<String>>()
                )
            }
            LaunchedEffect(Unit) {
                isLoading = true
                FirebaseDatabaseManager.observeScore {

                    liveScore = it
                }

                FirebaseDatabaseManager.observeLiveMatch {

                    liveMatch = it
                }
                FirebaseDatabaseManager.observeMatchResult {

                    matchResult = it
                }
                FirebaseDatabaseManager.observePlayerStat {

                    playerStat = it
                }
                isLoading = false
            }

            NavHost(
                navController = navController,
                startDestination = AppRoutes.SPLASH
            ) {

                // Splash
                composable(AppRoutes.SPLASH) {

                    SplashScreen(
                        onNavigate = {
                            navController.navigate(
                                AppRoutes.ROLE_SELECTION
                            )
                        }
                    )
                }


                // Role Selection
                composable(AppRoutes.ROLE_SELECTION) {

                    RoleSelectionScreen(

                        onAdminClick = {

                            navController.navigate(
                                AppRoutes.ADMIN_LOGIN
                            )
                        },

                        onFanClick = {

                            navController.navigate(
                                AppRoutes.FAN_LOGIN
                            )
                        }
                    )
                }


                // Admin Login
                composable(AppRoutes.ADMIN_LOGIN) {

                    LoginScreen(

                        role = "Admin",

                        onLoginClick = { email, password ->

                            FirebaseAuthManager.login(

                                email = email,

                                password = password,

                                onSuccess = {

                                    navController.navigate(
                                        AppRoutes.ADMIN_DASHBOARD
                                    )
                                },

                                onFailure = {

                                    android.util.Log.e(

                                        "Firebase",

                                        it
                                    )
                                }
                            )
                        },
                        onGuestClick = {

                        },

                        onBackClick = {

                            navController.popBackStack()
                        }
                    )
                }


                // Fan Login
                composable(AppRoutes.FAN_LOGIN) {

                    LoginScreen(

                        role = "Fan",

                        onLoginClick = {
                                email,
                                password ->

                            FirebaseAuthManager.login(

                                email = email,

                                password = password,

                                onSuccess = {

                                    navController.navigate(

                                        AppRoutes.FAN_DASHBOARD
                                    )
                                },

                                onFailure = {

                                    android.util.Log.e(

                                        "Firebase",

                                        it
                                    )
                                }
                            )
                        },

                        onGuestClick = {

                            navController.navigate(

                                AppRoutes.FAN_DASHBOARD
                            )
                        },

                        onBackClick = {

                            navController.popBackStack()
                        }
                    )
                }


                // Admin Dashboard
                composable(AppRoutes.ADMIN_DASHBOARD) {

                    AdminDashboardScreen(

                        onTournamentClick = {

                            navController.navigate(
                                AppRoutes.CREATE_TOURNAMENT
                            )
                        },

                        onTeamClick = {

                            navController.navigate(
                                AppRoutes.MANAGE_TEAMS
                            )
                        },

                        onPlayerClick = {

                            navController.navigate(
                                AppRoutes.MANAGE_PLAYERS
                            )
                        },

                        onScheduleClick = {

                            navController.navigate(

                                AppRoutes.MATCH_SCHEDULE
                            )
                        },

                        onLiveScoreClick = {

                            navController.navigate(
                                AppRoutes.LIVE_SCORE
                            )
                        },

                        onResultClick = {

                            navController.navigate(
                                AppRoutes.MATCH_RESULT
                            )
                        },

                        onStatsClick = {

                            navController.navigate(
                                AppRoutes.PLAYER_STATS
                            )
                        },

                        onShareClick = {

                            navController.navigate(
                                AppRoutes.SHARE_SCORE
                            )
                        }
                    )
                }

                composable(
                    AppRoutes.CREATE_TOURNAMENT
                ) {

                    CreateTournamentScreen(

                        onCreateClick = {

                                name,
                                sport,
                                date,
                                location ->

                            isLoading = true

                            FirebaseDatabaseManager.saveTournament(

                                tournament = Tournament(

                                    name = name,

                                    sportType = sport,

                                    date = date,

                                    location = location
                                ),

                                onSuccess = {

                                    isLoading = false

                                    navController.popBackStack()
                                },

                                onFailure = {

                                    android.util.Log.e(

                                        "Firebase",

                                        it
                                    )
                                }
                            )
                        },

                        onBackClick = {

                            navController.popBackStack()
                        }
                    )
                }

                composable(
                    AppRoutes.MANAGE_TEAMS
                ) {

                    ManageTeamsScreen(

                        onAddTeamClick = { teamName ->

                            FirebaseDatabaseManager.saveTeam(

                                team = Team(
                                    teamName
                                ),

                                onSuccess = { },

                                onFailure = {

                                    android.util.Log.e(

                                        "Firebase",

                                        it
                                    )
                                }
                            )
                        },

                        onBackClick = {

                            navController.popBackStack()
                        }
                    )
                }
                composable(
                    AppRoutes.MANAGE_PLAYERS
                ) {

                    ManagePlayersScreen(

                        onAddPlayerClick = {
                                name,
                                jersey,
                                team ->
                            val existingPlayers =

                                squadMap[team]

                                    ?: emptyList()


                            squadMap =

                                squadMap +

                                        (

                                                team to

                                                        (

                                                                existingPlayers + name
                                                                )
                                                )

                            FirebaseDatabaseManager.savePlayer(

                                player = Player(

                                    name = name
                                ),

                                onSuccess = { },

                                onFailure = {

                                    android.util.Log.e(

                                        "Firebase",

                                        it
                                    )
                                }
                            )
                        },

                        onBackClick = {

                            navController.popBackStack()
                        }
                    )
                }

                composable(

                    AppRoutes.MATCH_SCHEDULE

                ) {

                    MatchScheduleScreen(

                        onScheduleClick = {

                                teamA,
                                teamB,
                                date,
                                venue ->


                            liveMatch = ScheduledMatch(

                                teamA = teamA,

                                teamB = teamB,

                                date = date,

                                venue = venue,

                                status = "LIVE"
                            )


                            FirebaseDatabaseManager
                                .saveScheduledMatch(

                                    match = liveMatch,

                                    onSuccess = {

                                        navController.navigate(

                                            AppRoutes.MATCH_SETUP
                                        )
                                    },

                                    onFailure = {

                                        println(it)
                                    }
                                )
                        },

                        onBackClick = {

                            navController.popBackStack()
                        }
                    )
                }

                composable(

                    AppRoutes.MATCH_SETUP

                ) {

                    MatchSetupScreen(

                        onStartMatch = {
                                overs ->

                            val teamAPlayers =

                                squadMap[
                                    liveMatch.teamA
                                ] ?: emptyList()


                            val teamBPlayers =

                                squadMap[
                                    liveMatch.teamB
                                ] ?: emptyList()


                            if (

                                teamAPlayers.size !in 5..11 ||

                                teamBPlayers.size !in 5..11 ||

                                teamAPlayers.size != teamBPlayers.size

                            ) {

                                return@MatchSetupScreen
                            }


                            liveScore = Score(

                                teamA = liveMatch.teamA,

                                teamB = liveMatch.teamB,

                                teamAPlayers = teamAPlayers,

                                teamBPlayers = teamBPlayers,

                                venue = liveMatch.venue,

                                matchDate = liveMatch.date,

                                maxOvers = overs
                            )


                            FirebaseDatabaseManager.saveScore(

                                score = liveScore,

                                onSuccess = {

                                    navController.navigate(

                                        AppRoutes.LIVE_SCORE
                                    )
                                },

                                onFailure = {

                                    println(it)
                                }
                            )
                        },

                        onBackClick = {

                            navController.popBackStack()
                        }
                    )
                }

                composable(
                    AppRoutes.LIVE_SCORE
                ) {

                    LiveScoreScreen(

                        match = liveMatch,

                        score = liveScore,

                        onScoreUpdate = { updatedScore ->

                            liveScore = updatedScore


                            FirebaseDatabaseManager.saveScore(

                                score = updatedScore,

                                onSuccess = { },

                                onFailure = {

                                    android.util.Log.e(

                                        "Firebase",

                                        it
                                    )
                                }
                            )
                        },

                        onEndMatchClick = {

                            val chasingWon =

                                liveScore.innings == 2 &&

                                        liveScore.runs >=
                                        liveScore.target


                            val winner =

                                if (

                                    chasingWon

                                )

                                    liveScore.battingTeam

                                else

                                    liveScore.bowlingTeam


                            val winMargin =

                                if (

                                    chasingWon

                                ) {

                                    "${liveScore.teamAPlayers.size - 1 - liveScore.wickets} wickets"

                                } else {

                                    "${liveScore.target - liveScore.runs - 1} runs"
                                }


                            val winningText =

                                "$winner won by $winMargin"


                            val finalResult = Result(

                                teamA = liveMatch.teamA,

                                teamB = liveMatch.teamB,


                                winner = winner,

                                winningText = winningText,

                                winMargin = winMargin,


                                finalRuns =
                                    liveScore.runs,

                                finalWickets =
                                    liveScore.wickets,

                                finalOvers =
                                    liveScore.overs,

                                finalBalls =
                                    liveScore.balls
                            )


                            matchResult =
                                finalResult
                            playerStat = PlayerStat(

                            name = liveScore.striker.name,

                            matches = 1,

                            points = liveScore.striker.runs,

                            awards = 1

                            )

                            FirebaseDatabaseManager.savePlayerStat(


                            stat = playerStat,

                            onSuccess = { },

                            onFailure = {

                                println(it)
                            }

                            )



                            FirebaseDatabaseManager.saveResult(

                                result = finalResult,

                                onSuccess = {

                                    FirebaseDatabaseManager
                                        .saveScheduledMatch(

                                            match = liveMatch.copy(

                                                status =
                                                    "COMPLETED"
                                            ),

                                            onSuccess = {

                                                navController
                                                    .popBackStack()


                                                liveScore =
                                                    Score()
                                            },

                                            onFailure = {

                                                println(it)
                                            }
                                        )
                                },

                                onFailure = {

                                    println(it)
                                }
                            )
                        },

                        onBackClick = {

                            navController.popBackStack()
                        }
                    )
                }

                composable(
                    AppRoutes.MATCH_RESULT
                ) {

                    MatchHistoryScreen(

                        result = matchResult,

                        onBackClick = {

                            navController.popBackStack()
                        }
                    )
                }
                composable(
                    AppRoutes.PLAYER_STATS
                ) {

                    PlayerStatsScreen(

                        playerName = playerStat.name,

                        matches = playerStat.matches,

                        runs = playerStat.points,

                        wickets = 0,

                        awards = playerStat.awards,

                        onBackClick = {

                            navController.popBackStack()
                        }
                    )

                }

                composable(
                    AppRoutes.SHARE_SCORE
                ) {

                    ShareScoreScreen(

                        result = matchResult,

                        onShareClick = {

                            val text =

                                "🏆 ${matchResult.winningText}\n" +

                                        "Score: " +

                                        "${matchResult.finalRuns}/" +

                                        "${matchResult.finalWickets}\n" +

                                        "Overs: " +

                                        "${matchResult.finalOvers}." +

                                        "${matchResult.finalBalls}"


                            val intent = Intent(

                                Intent.ACTION_VIEW,

                                "https://wa.me/?text=${URLEncoder.encode(text, "UTF-8")}".toUri()
                            )

                            startActivity(intent)
                        },

                        onBackClick = {

                            navController.popBackStack()
                        }
                    )
                }

                composable(
                    AppRoutes.FAN_DASHBOARD
                ) {

                    FanDashboardScreen(

                        onLiveMatchesClick = {

                            navController.navigate(
                                AppRoutes.LIVE_MATCHES
                            )
                        },

                        onHistoryClick = {

                            navController.navigate(
                                AppRoutes.MATCH_HISTORY
                            )
                        },

                        onStatsClick = {

                            navController.navigate(
                                AppRoutes.FAN_PLAYER_STATS
                            )
                        }
                    )
                }

                composable(
                    AppRoutes.LIVE_MATCHES
                ) {

                    LiveMatchesScreen(

                        match = liveMatch,

                        score = liveScore,

                        onBackClick = {

                            navController.popBackStack()
                        }
                    )
                }

                composable(
                    AppRoutes.MATCH_HISTORY
                ) {

                    MatchHistoryScreen(

                        result = matchResult,

                        onBackClick = {

                            navController.popBackStack()
                        }
                    )
                }

                composable(
                    AppRoutes.FAN_PLAYER_STATS
                ) {

                    FanPlayerStatsScreen(

                        stat = playerStat,

                        onBackClick = {

                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}