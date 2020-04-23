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

	fun increaseProficiency(userId: String, word: String) {
		var userWord = userWordRepository.findByUserIdAndWord(userId, word)
		if (userWord != null)
			userWord.increaseProficiency()
		else
			userWord = UserWord(userId, word)
		userWordRepository.save(userWord)
	}

	fun findBy(userId: String, word: String) = userWordRepository.findByUserIdAndWord(userId, word)
}
