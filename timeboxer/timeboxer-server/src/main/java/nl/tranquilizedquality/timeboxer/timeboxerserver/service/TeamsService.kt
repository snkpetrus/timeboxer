package nl.tranquilizedquality.timeboxer.timeboxerserver.service

import nl.tranquilizedquality.timeboxer.timeboxerserver.domain.Team
import nl.tranquilizedquality.timeboxer.timeboxerserver.domain.repository.TeamRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class TeamsService(private val teamRepository: TeamRepository) {

    fun getTeam(teamId: Long): Optional<Team> {
        return teamRepository.findById(teamId)
    }
}