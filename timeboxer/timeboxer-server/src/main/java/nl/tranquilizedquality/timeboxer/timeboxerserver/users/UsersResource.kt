package nl.tranquilizedquality.timeboxer.timeboxerserver.users

import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/users"],
        consumes = [(MediaType.APPLICATION_JSON_VALUE)],
        produces = [(MediaType.APPLICATION_JSON_VALUE)])
class UsersResource(private val userService: UserService) {
    companion object {
        private val logger = LoggerFactory.getLogger(UsersResource::class.java)
    }

    @GetMapping(value = ["/{user_id}"])
    fun getUser(@PathVariable(value = "user_id") userId: Long): User {
        logger.debug("Getting: $userId")
        return userService.getUser(userId)
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