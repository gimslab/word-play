package com.gimslab.wordplay.service.userwordbook

import com.gimslab.wordplay.service.wordplay.UserWord
import com.gimslab.wordplay.service.wordplay.UserWordRepository
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class UserWordService(
		private val userWordRepository: UserWordRepository,
		private val random: Random = Random(System.currentTimeMillis())
) {
	fun findNextUserWord(userId: Long?, wordBookId: Long): UserWord? {
		val founds = userWordRepository.findTop8ByUserIdAndWordBookIdOrderByProficiencyAsc(userId ?: 0, wordBookId)
		return founds.get(random.nextInt(founds.size))
	}
}

