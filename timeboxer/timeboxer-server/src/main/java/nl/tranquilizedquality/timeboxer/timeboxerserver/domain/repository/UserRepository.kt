package nl.tranquilizedquality.timeboxer.timeboxerserver.domain.repository

import nl.tranquilizedquality.timeboxer.timeboxerserver.domain.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>