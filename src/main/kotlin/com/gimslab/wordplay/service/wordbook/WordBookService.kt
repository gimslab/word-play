package com.gimslab.wordplay.service.wordbook

import org.springframework.stereotype.Service

@Service
class WordBookService(
		val wordBookRepository: WordBookRepository
) {
	fun list(): List<WordBook> {
		return wordBookRepository.findAll()
	}

}
