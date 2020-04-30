package com.gimslab.wordplay.service.wordbook

interface WordBookRepository {
	fun findAll(): List<WordBook>
	fun getOne(id: Long): WordBook
	fun save(wordBook: WordBook): WordBook
}

