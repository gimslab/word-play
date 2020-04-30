package com.gimslab.wordplay.service.userwordbook

import com.gimslab.wordplay.service.wordplay.UserWord
import com.gimslab.wordplay.service.wordplay.UserWordRepository
import com.gimslab.wordplay.service.wordplay.Word
import com.gimslab.wordplay.service.wordplay.WordRepository
import com.gimslab.wordplay.util.ReadabilityHelper.Companion.not
import org.springframework.stereotype.Service

@Service
class UserWordBookService(
		private val wordRepository: WordRepository,
		private val userWordRepository: UserWordRepository
) {
	fun prepareUserWordbook(userId: Long, wordBookId: Long) {
		val words = findWordsFromWordBook(wordBookId)
		val userWords = findUserWordsBy(userId, wordBookId)
		val userWordNos = userWords.map { it.wordId }.toSet()
		val newUserWords = words
				.filter { not(userWordNos.contains(it.id)) }
				.map { UserWord(userId, wordBookId, it.id!!) }
		newUserWords.forEach { userWordRepository.save(it) }
	}

	private fun findWordsFromWordBook(wordBookId: Long): List<Word> {
		return wordRepository.findByWordBookId(wordBookId)
	}

	private fun findUserWordsBy(userId: Long, wordBookId: Long): List<UserWord> {
		return userWordRepository.findByUserIdAndWordBookId(userId, wordBookId)
	}
}
