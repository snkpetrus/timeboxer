package nl.tranquilizedquality.timeboxer.timeboxerserver.domain

data class Team(val teamId: Long? = null,
                val name: String? = null,
                val users: List<User> = mutableListOf())