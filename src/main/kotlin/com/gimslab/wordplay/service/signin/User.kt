package com.gimslab.wordplay.service.signin

import java.time.ZonedDateTime
import javax.persistence.*

@Table(name = "user")
@Entity
class User(
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long?,
		val signId: String,
		val createdTime: ZonedDateTime
) {
	companion object {
		fun of(signId: String): User {
			return User(null, signId, ZonedDateTime.now())
		}
	}
}

