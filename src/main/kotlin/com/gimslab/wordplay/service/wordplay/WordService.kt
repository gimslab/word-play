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
//		return wordsRepository.findRandomWord(wordBook.filename)
		return Word(0, 0, "abc", "가나다")
	}

	fun increaseProficiency(userId: Long, wordBookId: Long, wordId: Long) {
		var userWord = userWordRepository.findByUserIdAndWordId(userId, wordId)
		if (userWord != null)
			userWord.increaseProficiency()
		else
			userWord = UserWord(userId, wordBookId, wordId)
		userWordRepository.save(userWord)
	}

	fun findBy(userId: Long, wordId: Long) = userWordRepository.findByUserIdAndWordId(userId, wordId)
}
