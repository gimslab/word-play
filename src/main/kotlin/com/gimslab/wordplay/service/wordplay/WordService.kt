package com.gimslab.wordplay.service.wordplay

import com.gimslab.wordplay.service.wordbook.WordBookRepository
import org.springframework.stereotype.Service

@Service
class WordService(
		val wordRepository: WordRepository,
		val wordBookRepository: WordBookRepository,
		val userWordRepository: UserWordRepository
) {

	fun findNextWord(wordBookId: Long): Word {
		val wordBook = wordBookRepository.getOne(wordBookId)
		return wordRepository.findRandomWord(wordBook.filename)
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
