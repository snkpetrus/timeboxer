package nl.tranquilizedquality.timeboxer.timeboxerserver.users

import org.springframework.stereotype.Component
import java.util.*

@Component
class UserService(private val userRepository: UserRepository) {

    fun getUser(userId: Long) : Optional<User>? {
        return userRepository.findById(userId)
    }

    fun createUser(user: User) {
        userRepository.save(user)
    }

    fun updateUser(user: User) {
        userRepository.save(user)
    }
}