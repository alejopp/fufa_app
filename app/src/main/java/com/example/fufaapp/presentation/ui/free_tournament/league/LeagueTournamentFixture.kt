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
                    var candidate: String?
                    if (matchCantPlay.isEmpty()){
                        candidate = if(teamsList.size % 2 == 0 ){ // Is teamList even?
                            totalGenericMatches.first()
                        } else{
                            totalGenericMatches.find {
                                !it.contains(teamThatRest)
                            }
                        }
                        roundList.add(candidate!!)
                    }
                    else{
                        candidate = if(teamsList.size % 2 == 0 ){ // Is teamList even?
                            totalGenericMatches.find {
                                it !in matchCantPlay
                            }
                        } else{
                            totalGenericMatches.find {
                                it !in matchCantPlay && !it.contains(teamThatRest)
                            }
                        }
                        roundList.add(candidate!!)
                    }
                }
                else{
                    var teamCantPlay = "["
                    roundList.map {
                        teamCantPlay += it
                    }
                    if(teamsList.size % 2 != 0) teamCantPlay += teamThatRest
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
            println("Round list $roundList")
            matches += roundList
            matchCantPlay.clear()
            totalGenericMatches.removeAll(roundList.map{it})
            roundList.clear()
        }
        println("Matches $matches")
        val fixture = matches.map{ genericMatch ->
            genericMatch.map{ genericTeam ->
                teamsList[Fixture.LETTERS.indexOf(genericTeam)]
            }
        }

        println("fixture $fixture")
        return fixture
    }


}