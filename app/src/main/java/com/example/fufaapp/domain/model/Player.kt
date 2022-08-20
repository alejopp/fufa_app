package com.example.fufaapp.domain.model

data class Player(
    var position: Int = 0,
    var name: String,
    var goalsFor: Int = 0,
    var goalsAgainst: Int = 0,
    var goalDifference: Int = 0,
    var points: Int = 0,
    var team: Team
)
