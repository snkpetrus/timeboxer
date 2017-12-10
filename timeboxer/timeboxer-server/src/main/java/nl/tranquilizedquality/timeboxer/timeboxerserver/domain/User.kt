package nl.tranquilizedquality.timeboxer.timeboxerserver.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User {

	@Id
	val id: Long
	val name: String
	val email: String

	constructor() {
		this.id = 0L
		this.name = ""
		this.email = ""
	}

	constructor(id: Long,
				name: String,
				email: String) {

		this.id = id
		this.name = name
		this.email = email
	}
	
}