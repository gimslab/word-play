package com.gimslab.wordplay.service.wordplay

import org.springframework.stereotype.Service

@Service
class WordService(
		val wordRepository: WordRepository,
		val userWordRepository: UserWordRepository
) {
	fun increaseProficiency(userId: Long, wordBookId: Long, wordId: Long, userWordId: Long) {
		val userWordFound = userWordRepository.getOne(userWordId)
		val userWord =
				if (userWordFound.userId == userId)
					userWordFound
				else
					UserWord(userId, wordBookId, wordId)
		userWord.increaseProficiency()
		userWordRepository.save(userWord)
	}

	fun findWordById(wordId: Long) =
			wordRepository.getOne(wordId)
}
