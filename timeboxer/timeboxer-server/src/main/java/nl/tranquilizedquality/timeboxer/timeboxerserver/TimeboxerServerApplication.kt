package nl.tranquilizedquality.timeboxer.timeboxerserver

import nl.tranquilizedquality.timeboxer.timeboxerserver.domain.Team
import nl.tranquilizedquality.timeboxer.timeboxerserver.domain.User
import nl.tranquilizedquality.timeboxer.timeboxerserver.domain.repository.TeamRepository
import nl.tranquilizedquality.timeboxer.timeboxerserver.domain.repository.UserRepository
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
open class TimeboxerServerApplication : SpringBootServletInitializer() {

    @Bean
    open fun init(userRepository: UserRepository, teamRepository: TeamRepository) = CommandLineRunner {
        val ballo = User(1L, "Ballo", "ballo@gmail.com")
        val johnSnow = User(2L, "John Snow", "jonSnow@gmail.com")

        userRepository.save(ballo)
        userRepository.save(johnSnow)
        userRepository.findAll().forEach { println(it) }

        teamRepository.save(Team(1L, "HackatonX", listOf(ballo)))
        teamRepository.save(Team(2L, "HackatonY", listOf(ballo, johnSnow)))
        teamRepository.findAll().forEach { println(it) }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(TimeboxerServerApplication::class.java, *args)
        }
    }
}
