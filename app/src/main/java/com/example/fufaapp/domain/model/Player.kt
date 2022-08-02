package com.example.fufaapp.domain.model

data class Player(
    val position: Int = 0,
    val name: String,
    val matchesPlayed: Int = 0,
    val goalsFor: Int = 0,
    val goalsAgainst: Int = 0,
    val goalDifference: Int = 0,
    val team: Team
)
