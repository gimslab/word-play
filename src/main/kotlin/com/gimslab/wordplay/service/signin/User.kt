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
		val createdAt: ZonedDateTime,
		val modifiedAt: ZonedDateTime,
		val deleted: Boolean
) {
	companion object {
		fun of(signId: String): User {
			return User(null, signId, ZonedDateTime.now(), ZonedDateTime.now(), false)
		}
	}
}

