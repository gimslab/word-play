package com.gimslab.wordplay.service.userwordbook

import com.gimslab.wordplay.service.wordbook.WordBook
import com.gimslab.wordplay.service.wordbook.WordBookRepository
import com.gimslab.wordplay.service.wordplay.UserWord
import com.gimslab.wordplay.service.wordplay.UserWordRepository
import com.gimslab.wordplay.service.wordplay.Word
import com.gimslab.wordplay.service.wordplay.WordRepository
import com.gimslab.wordplay.util.ReadabilityHelper.Companion.not
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserWordBookService(
		private val wordRepository: WordRepository,
		private val userWordRepository: UserWordRepository,
		private val userWordBookRepository: UserWordBookRepository,
		private val wordBookRepository: WordBookRepository
) {
	@Transactional(readOnly = false)
	fun prepareUserWordbook(userId: Long, wordBookId: Long) {

		updateUserWordBook(userId, wordBookId)

		prepareUserWords(userId, wordBookId)
	}

	private fun updateUserWordBook(userId: Long, wordBookId: Long) {
		val userWordBook = findUserWordBookBy(userId, wordBookId)
		if (userWordBook != null) {
			userWordBook.updateModifiedAt()
		} else {
			val wordBook = findWordBookBy(wordBookId)
			val newUserWordBook = UserWordBook(userId, wordBook)
			userWordBookRepository.save(newUserWordBook)
		}
	}

	private fun findWordBookBy(wordBookId: Long) =
			wordBookRepository.getOne(wordBookId)

	private fun prepareUserWords(userId: Long, wordBookId: Long) {
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

	private fun findUserWordBookBy(userId: Long, wordBookId: Long): UserWordBook? {
		return userWordBookRepository.findByUserIdAndWordBookId(userId, wordBookId)
	}
}
