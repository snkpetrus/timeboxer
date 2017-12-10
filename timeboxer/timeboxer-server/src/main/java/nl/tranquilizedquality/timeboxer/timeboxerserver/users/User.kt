package nl.tranquilizedquality.timeboxer.timeboxerserver.users
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class User(@Id val id: Long? = null,
                val name: String? = null,
                val email: String? = null)