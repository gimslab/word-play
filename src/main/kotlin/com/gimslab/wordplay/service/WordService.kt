package com.gimslab.wordplay.service

import org.springframework.stereotype.Service

@Service
class WordService(
		val wordRepository: WordRepository
) {

	fun findNextWord(): Word {
		return wordRepository.findRandomWord()
	}

}
