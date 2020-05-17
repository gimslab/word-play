package com.gimslab.wordplay.service.wordbook

interface WordBookRepository {
	fun getOne(id: Long): WordBook
	fun findAll(): List<WordBook>
	fun save(wordBook: WordBook): WordBook
}

