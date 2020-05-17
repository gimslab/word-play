package com.gimslab.wordplay.service.userwordbook

interface UserWordBookRepository {
	fun getOne(wordBookId: Long): UserWordBook
	fun findByUserIdAndWordBookId(userId: Long, wordBookId: Long): UserWordBook?
	fun findByUserId(userId: Long): List<UserWordBook>
	fun save(userWordBook: UserWordBook)
}

