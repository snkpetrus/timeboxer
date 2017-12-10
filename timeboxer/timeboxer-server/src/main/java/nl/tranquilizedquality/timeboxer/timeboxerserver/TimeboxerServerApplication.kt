package nl.tranquilizedquality.timeboxer.timeboxerserver;

import nl.tranquilizedquality.timeboxer.timeboxerserver.users.User
import nl.tranquilizedquality.timeboxer.timeboxerserver.users.UserRepository
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean

@SpringBootApplication
open class TimeboxerServerApplication : SpringBootServletInitializer() {

    @Bean
    open fun init(repository: UserRepository) = CommandLineRunner {
        repository.save(User(1L, "Ballo", "ballo@gmail.com"))
        repository.save(User(2L, "Ballo2", "ballo@gmail.com"))

        repository.findAll().forEach { println(it) }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(TimeboxerServerApplication::class.java, *args)
        }
    }
}
