package dev.negativekb.kitpvp.core.structure

class Profile {
    private var kills = 0
    private var deaths = 0
    private var tokens: Long = 0

    fun addKills(amount: Int) {
        kills += amount
    }

    fun addDeaths(amount: Int) {
        deaths += amount
    }

    fun addTokens(amount: Long) {
        tokens += amount
    }
}