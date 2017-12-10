package nl.tranquilizedquality.timeboxer.timeboxerserver.resource

import nl.tranquilizedquality.timeboxer.timeboxerserver.domain.Team
import nl.tranquilizedquality.timeboxer.timeboxerserver.domain.User
import nl.tranquilizedquality.timeboxer.timeboxerserver.service.TeamsService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
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
        private val logger = LoggerFactory.getLogger(TeamsResource::class.java)
    }

    @GetMapping(value = "/{team_id}")
    fun getTeam(@PathVariable(value = "team_id") teamId: Long): ResponseEntity<Team> {
        logger.debug("Getting team: $teamId")
        val team = teamsService.getTeam(teamId)
        return when {
            team.isPresent -> ResponseEntity(team.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping(value = "/{team_id}/users")
    fun getUsersForTeam(@PathVariable(value = "team_id") teamId: Long): ResponseEntity<List<User>> {
        logger.debug("Getting members of team: $teamId")
        val team = teamsService.getTeam(teamId)
        return when {
            team.isPresent -> ResponseEntity(team.get().users, HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}