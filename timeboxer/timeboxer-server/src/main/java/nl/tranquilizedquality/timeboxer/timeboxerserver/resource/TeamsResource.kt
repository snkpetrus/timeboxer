package nl.tranquilizedquality.timeboxer.timeboxerserver.resource

import nl.tranquilizedquality.timeboxer.timeboxerserver.service.TeamsService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TeamsResource(private val teamsService: TeamsService) {
    companion object {
        private val logger = LoggerFactory.getLogger(UsersResource::class.java)
    }

    @GetMapping(value = "/{team_id}")
    fun getTeam(@PathVariable(value = "team_id") teamId: Long) {
        logger.debug("Getting team: $teamId")
        teamsService.getTeam(teamId)
    }
}