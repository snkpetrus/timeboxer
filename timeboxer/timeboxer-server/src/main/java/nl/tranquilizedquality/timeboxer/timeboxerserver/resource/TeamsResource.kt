package nl.tranquilizedquality.timeboxer.timeboxerserver.resource

import nl.tranquilizedquality.timeboxer.timeboxerserver.service.TeamsService
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/teams",
        consumes = [(MediaType.APPLICATION_JSON_VALUE)],
        produces = [(MediaType.APPLICATION_JSON_VALUE)])
class TeamsResource(private val teamsService: TeamsService) {
    companion object {
        private val logger = LoggerFactory.getLogger(UsersResource::class.java)
    }

    @GetMapping(value = "/{team_id}")
    fun getTeam(@PathVariable(value = "team_id") teamId: Long) {
        logger.debug("Getting team: $teamId")
        teamsService.getTeam(teamId)
    }

    @GetMapping(value = "/{team_id}/users")
    fun getUsersForTeam(@PathVariable(value = "team_id") teamId: Long) {
        logger.debug("Getting members of team: $teamId")
        teamsService.getUsersForTeam(teamId)
    }
}