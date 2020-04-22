package com.gimslab.wordplay.service.wordplay

interface UserWordRepository {
//	fun findUserWordBy(currentUserId: String, eng: String): UserWord?
	fun findByUserIdAndWordEng(userId: String, wordEng: String): UserWord?
	fun save(userWord: UserWord)
}

