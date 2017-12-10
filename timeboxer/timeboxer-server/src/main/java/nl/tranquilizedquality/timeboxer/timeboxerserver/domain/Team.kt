package nl.tranquilizedquality.timeboxer.timeboxerserver.domain

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
data class Team(@Id val teamId: Long? = null,
                val name: String? = null,
                @ManyToMany(targetEntity = User::class, fetch = FetchType.EAGER) val users: List<User> = mutableListOf())