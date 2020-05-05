package com.gimslab.wordplay.service.wordplay

interface UserWordRepository {
	fun getOne(id: Long): UserWord
	fun findByUserIdAndWordBookId(userId: Long, wordBookId: Long): List<UserWord>
	fun save(userWord: UserWord): UserWord
	fun findTop8ByUserIdAndWordBookIdOrderByProficiencyAscModifiedAtAsc(userId: Long, wordBookId: Long): List<UserWord>
}

