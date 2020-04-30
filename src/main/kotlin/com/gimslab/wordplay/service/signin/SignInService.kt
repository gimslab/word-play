package com.gimslab.wordplay.service.signin

import org.springframework.stereotype.Service

@Service
class SignInService(
		private val usersRepository: UsersRepository
) {
	fun signIn(signId: String): User? {
		val user = usersRepository.findBySignId(signId)
		if (user != null)
			return user
		return usersRepository.save(User.of(signId))

	}
}
