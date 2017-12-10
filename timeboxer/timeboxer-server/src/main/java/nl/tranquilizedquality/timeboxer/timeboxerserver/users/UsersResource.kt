package nl.tranquilizedquality.timeboxer.timeboxerserver.users

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersResource {

    @GetMapping("/user")
    fun getUser() {

        print("Works!")
    }
}