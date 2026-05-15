# Grama-Kalyana Sports 🏏

An Android-based digital cricket scoring application designed to modernize village-level cricket tournaments by replacing traditional paper-based scoring with a real-time digital scoreboard.

## Project Overview

Village cricket tournaments are often managed manually using paper scorebooks, making live score sharing, player performance tracking, and match history management difficult.

Grama-Kalyana Sports solves this problem by providing a digital platform where scorers can update live match scores in real time, while fans can follow matches remotely from their mobile devices.

This project was developed as part of Android App Development using Generative AI.

---

## Problem Statement

Village-level cricket tournaments lack:

- Real-time live score visibility
- Digital player performance records
- Match history storage
- Remote fan engagement

This project provides a digital solution for managing and broadcasting live cricket matches.

---

## Features

### Admin / Scorer Module

- Admin authentication
- Create tournament
- Add teams
- Add players
- Schedule matches
- Toss selection
- Live ball-by-ball scoring
- Wide and no-ball handling
- Wicket handling
- Innings switching
- Match result generation
- Share scorecard via WhatsApp

### Fan Module

- View live matches
- Live score updates
- View match history
- View player statistics

### Player Statistics

- Runs scored
- Wickets taken
- Matches played
- Awards and achievements

---

## Technology Stack

- Kotlin
- Jetpack Compose
- Firebase Authentication
- Firebase Realtime Database
- Android Studio
- Git & GitHub

---

## Project Structure

```text
app/
├── data/model/
├── firebase/
├── navigation/
├── ui/screens/auth/
├── ui/screens/admin/
├── ui/screens/fan/
└── MainActivity.kt
```

---

## Installation and Setup

### Prerequisites

Install:

- Android Studio
- JDK 17 or above
- Android SDK
- Firebase project setup

### Clone Repository

```bash
git clone <your-repository-link>
```

### Open Project

```bash
Open project in Android Studio
```

### Build and Run

```bash
Sync Gradle
Run app on emulator or Android device
```

---

## Firebase Configuration

1. Create Firebase project
2. Enable Authentication
3. Enable Realtime Database
4. Download `google-services.json`
5. Place it inside:

```text
app/google-services.json
```

---

## How to Use

### Admin

1. Login as scorer
2. Create tournament
3. Add teams and players
4. Schedule match
5. Start live scoring
6. Publish result

### Fan

1. Open fan dashboard
2. View live scores
3. Check player stats
4. View match history

---

## Screenshots

Add screenshots here.

Example:

- Splash Screen
- Admin Dashboard
- Live Score Screen
- Fan View
- Match Result Screen

---

## Future Enhancements

- Kabaddi scoring
- Volleyball scoring
- Player rankings
- Tournament analytics
- Offline support

---

## Author

Piyush Priy  
Sri Krishna Institute of Technology
