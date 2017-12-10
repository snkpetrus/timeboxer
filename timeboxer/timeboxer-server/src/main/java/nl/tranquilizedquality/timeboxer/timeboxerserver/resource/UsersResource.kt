package nl.tranquilizedquality.timeboxer.timeboxerserver.resource

import nl.tranquilizedquality.timeboxer.timeboxerserver.domain.User
import nl.tranquilizedquality.timeboxer.timeboxerserver.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping(value = "/users",
        consumes = [(MediaType.APPLICATION_JSON_VALUE)],
        produces = [(MediaType.APPLICATION_JSON_VALUE)])
class UsersResource(private val userService: UserService) {
    companion object {
        private val logger = LoggerFactory.getLogger(UsersResource::class.java)
    }

    @GetMapping(value = ["/{user_id}"])
    fun getUser(@PathVariable(value = "user_id") userId: Long): ResponseEntity<User> {
        logger.debug("Getting: $userId")
        val user = userService.getUser(userId)
        return when {
            user.isPresent -> ResponseEntity(user.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createUser(@RequestBody user: User) {
        logger.debug("Creating: $user")
        userService.createUser(user)
    }

    @PutMapping
    fun updateUser(@RequestBody user: User) {
        logger.debug("Updating: $user")
        userService.updateUser(user)
    }
}