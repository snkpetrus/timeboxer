package nl.tranquilizedquality.timeboxer.timeboxerserver.domain

data class Team(val teamId: Long? = null,
                val users: List<User> = mutableListOf())