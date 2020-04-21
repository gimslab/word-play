package com.gimslab.wordplay.service.wordplay

import com.gimslab.wordplay.service.wordplay.Word
import com.gimslab.wordplay.service.wordplay.WordRepository
import org.springframework.stereotype.Service

@Service
class WordService(
		val wordRepository: WordRepository
) {

	fun findNextWord(): Word {
		return wordRepository.findRandomWord()
	}

}
