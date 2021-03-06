package nl.tranquilizedquality.timeboxer.timeboxerserver.service

import nl.tranquilizedquality.timeboxer.timeboxerserver.domain.User
import nl.tranquilizedquality.timeboxer.timeboxerserver.domain.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserService(private val userRepository: UserRepository) {

	fun getUser(userId: Long) = userRepository.findById(userId)
    fun createUser(user: User) = userRepository.save(user)
    fun updateUser(user: User) = userRepository.save(user)
}