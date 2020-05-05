package com.gimslab.wordplay.service.wordplay

import org.springframework.stereotype.Service

@Service
class WordService(
		val wordRepository: WordRepository,
		val userWordRepository: UserWordRepository
) {
	fun increaseProficiency(userId: Long, wordBookId: Long, wordId: Long) {
		val userWords = userWordRepository.findByUserIdAndWordId(userId, wordId)
		val userWord = if (userWords.isNotEmpty()) {
			userWords[0].increaseProficiency()
			userWords[0]
		} else {
			UserWord(userId, wordBookId, wordId)
		}
		userWordRepository.save(userWord)
	}

	fun findWordById(wordId: Long) =
			wordRepository.getOne(wordId)
}
