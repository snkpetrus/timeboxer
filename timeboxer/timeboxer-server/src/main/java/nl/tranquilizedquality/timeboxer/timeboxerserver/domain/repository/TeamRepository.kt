package nl.tranquilizedquality.timeboxer.timeboxerserver.domain.repository

import nl.tranquilizedquality.timeboxer.timeboxerserver.domain.Team
import org.springframework.data.repository.CrudRepository

interface TeamRepository : CrudRepository<Team, Long>