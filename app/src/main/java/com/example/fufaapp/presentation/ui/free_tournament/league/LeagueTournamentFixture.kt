package com.example.fufaapp.presentation.ui.free_tournament.league

import com.example.fufaapp.util.Fixture

class LeagueTournamentFixture: Fixture {

     override fun createGenericTeams(numberOfTeams: Int): MutableList<String> {
        val genericTeams = mutableListOf<String>()
        for (i in 0 until numberOfTeams){
            genericTeams.add(Fixture.LETTERS[i].toString())
        }
        return genericTeams
    }

    override fun createTotalGenericMatches(numberOfTeams: Int): MutableList<String> {
        val totalGenericMatches = mutableListOf<String>()
        val genericTeamsList = createGenericTeams(numberOfTeams)
        for(i in 0..genericTeamsList.size - 2){
            for(j in i + 1 until genericTeamsList.size){
                totalGenericMatches.add(genericTeamsList[i] + genericTeamsList[j])
            }
        }
        return totalGenericMatches
    }

    override fun createFixture(teamsList: List<String>): List<List<String>> {
        val totalGenericMatches = createTotalGenericMatches(teamsList.size)
        val totalGenericTeams = createGenericTeams(teamsList.size)
        val roundList = mutableListOf<String>()
        val matchCantPlay = mutableListOf<String>()
        val matchesPerRound = totalGenericTeams.size / 2
        val totalMatches = totalGenericMatches.size
        val rounds = totalMatches / matchesPerRound
        val matches = mutableListOf<String>()
        val teamRestCandidates = totalGenericTeams.asReversed()
        for (round in 1..rounds){
            val teamThatRest = teamRestCandidates.removeFirst()
            while (roundList.size < matchesPerRound){
                if(roundList.isEmpty()){
                    if (matchCantPlay.isEmpty()){
                        val candidate = totalGenericMatches.find {
                            !it.contains(teamThatRest)
                        }
                        roundList.add(candidate!!)
                    }
                    else{
                        val candidate = totalGenericMatches.find {
                            it !in matchCantPlay && !it.contains(teamThatRest)
                        }
                        roundList.add(candidate!!)
                    }
                }
                else{
                    var teamCantPlay = "["
                    roundList.map {
                        teamCantPlay += it
                    }
                    teamCantPlay += teamThatRest
                    teamCantPlay += "]"
                    val m = totalGenericMatches.find {
                        !it.contains(teamCantPlay.toRegex())
                    }
                    if(m == null ){
                        matchCantPlay.addAll(roundList.map{it})
                        roundList.clear()
                    }
                    else{
                        roundList.add(m)
                    }
                }
            }
            matches += roundList
            matchCantPlay.clear()
            totalGenericMatches.removeAll(roundList.map{it})
            roundList.clear()
            println("fixture $matches matchCantPlay $matchCantPlay fl $roundList mp $totalGenericMatches" )
        }
        val fixture = matches.map{
            it.map{teamsList[Fixture.LETTERS.indexOf(it)]}
        }
        return fixture
    }


}