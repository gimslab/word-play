package com.gimslab.wordplay.service.wordplay

interface WordRepository {
	fun findByWordBookId(wordBookId: Long): List<Word>
	fun save(word: Word): Word
	fun getOne(wordId: Long): Word?
}

