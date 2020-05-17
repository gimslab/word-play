package com.gimslab.wordplay.service.wordbook

import com.gimslab.wordplay.service.userwordbook.UserWordBook
import com.gimslab.wordplay.service.userwordbook.UserWordBookRepository
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
@EnableScheduling
class WordBookService(
		val cacheForTitle: MutableMap<Long, String> = mutableMapOf(),
		val wordBookRepository: WordBookRepository,
		val userWordBookRepository: UserWordBookRepository
) {
	fun findAllWordBooks(userId: Long?): List<WordBook> {
		val userWordBooks = findUserWordBooksBy(userId)
		val wordBooks = wordBookRepository.findAll()
		return (userWordBooks + wordBooks).distinctBy { it.id }
	}

	private fun findUserWordBooksBy(userId: Long?): List<WordBook> {
		return if (userId == null)
			listOf()
		else
			userWordBookRepository.findByUserId(userId).map { toWordBook(it) }
	}


	private fun toWordBook(userWordBook: UserWordBook): WordBook {
		return WordBook(id = userWordBook.wordBookId, title = userWordBook.wordBookTitle,
				createdAt = userWordBook.createdAt, modifiedAt = userWordBook.modifiedAt)
	}

	fun findById(wordBookId: Long) =
			wordBookRepository.getOne(wordBookId)

	fun findCachedWordBookTitleById(wordBookId: Long): String {
		return cacheForTitle.computeIfAbsent(wordBookId) {
			findById(it).title
		}
	}

	@Scheduled(fixedDelay = 1 * 60 * 1000)
	private fun clearCache() {
		cacheForTitle.clear()
		println("++ ${ZonedDateTime.now()} cacheForTitle cleared")
	}
}

