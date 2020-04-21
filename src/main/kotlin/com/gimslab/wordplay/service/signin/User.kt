package com.gimslab.wordplay.service.signin

import java.time.ZonedDateTime

data class User(
		val userId: String,
		val registeredAt: ZonedDateTime
) {
	companion object {
		fun of(userId: String): User {
			return User(userId, ZonedDateTime.now())
		}
	}
}

