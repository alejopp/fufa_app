package com.example.fufaapp.util

interface Fixture {
    companion object{
        const val LETTERS = "abcdefghijklmnopqrstuvxyz"
    }
    fun createGenericTeams(numberOfTeams: Int): MutableList<String>
    fun createTotalGenericMatches(numberOfTeams: Int): MutableList<String>
    fun createFixture(numberOfTeams: List<String>): List<List<String>>

}