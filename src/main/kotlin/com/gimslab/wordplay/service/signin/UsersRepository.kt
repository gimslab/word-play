package com.gimslab.wordplay.service.signin

interface UsersRepository {
	fun findById(userId: String): User?
	fun addUser(user: User)
}
