package com.gimslab.wordplay.service.wordbook

import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
@EnableScheduling
class WordBookService(
		val cacheForTitle: MutableMap<Long, String> = mutableMapOf(),
		val wordBookRepository: WordBookRepository
) {
	fun findAllWordBooks() =
			wordBookRepository.findAll()

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

