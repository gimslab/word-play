package com.gimslab.wordplay.service.wordplay

interface UserWordRepository {
	fun findByUserIdAndWord(userId: String, word: String): UserWord?
	fun save(userWord: UserWord)
}

