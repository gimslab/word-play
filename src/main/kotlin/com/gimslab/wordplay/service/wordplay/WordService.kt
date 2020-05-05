package com.gimslab.wordplay.service.wordplay

import com.gimslab.wordplay.service.wordbook.WordBookRepository
import org.springframework.stereotype.Service

@Service
class WordService(
		val wordRepository: WordRepository,
		val wordBookRepository: WordBookRepository,
		val userWordRepository: UserWordRepository
) {
	fun increaseProficiency(userId: Long, wordBookId: Long, wordId: Long) {
		var userWord = userWordRepository.findByUserIdAndWordBookIdAndWordId(userId, wordBookId, wordId)
		if (userWord != null)
			userWord.increaseProficiency()
		else
			userWord = UserWord(userId, wordBookId, wordId)
		userWordRepository.save(userWord)
	}

	fun findWordById(wordId: Long) =
			wordRepository.getOne(wordId)
}
