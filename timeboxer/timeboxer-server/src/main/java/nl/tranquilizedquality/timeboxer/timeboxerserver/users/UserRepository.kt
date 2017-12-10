package nl.tranquilizedquality.timeboxer.timeboxerserver.users

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>