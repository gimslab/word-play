package com.gimslab.wordplay.service.wordplay

interface UserWordRepository {
	fun findByUserIdAndWordBookId(userId: Long, wordBookId: Long): List<UserWord>
	fun findByUserIdAndWordId(userId: Long, wordId: Long): UserWord?
	fun save(userWord: UserWord): UserWord
	fun findByUserIdAndWordBookIdAndWordId(userId: Long, wordBookId: Long, wordId: Long): UserWord?
	fun findTop8ByUserIdAndWordBookIdOrderByProficiencyAsc(userId: Long, wordBookId: Long): List<UserWord>
}

