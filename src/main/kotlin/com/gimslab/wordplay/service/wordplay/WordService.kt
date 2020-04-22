package com.gimslab.wordplay.service.wordplay

import org.springframework.stereotype.Service

@Service
class WordService(
		val wordRepository: WordRepository,
		val userWordRepository: UserWordRepository
) {

	fun findNextWord(): Word {
		return wordRepository.findRandomWord()
	}
}
