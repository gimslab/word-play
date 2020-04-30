package com.gimslab.wordplay.service.signin

interface UsersRepository {
	fun getOne(userId: Long): User
	fun save(user: User): User
	fun findBySignId(signId: String): User?
}
