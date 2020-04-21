package com.gimslab.wordplay.service.signin

import org.springframework.stereotype.Service

@Service
class SignInService(
		private val usersRepository: UsersRepository
) {
	fun signIn(userId: String) {
		val user = usersRepository.findById(userId)
		if (user == null)
			usersRepository.addUser(User.of(userId))

	}
}
