package com.gimslab.wordplay.batch

import com.gimslab.wordplay.service.userwordbook.UserWordBookRepository
import com.gimslab.wordplay.service.wordplay.UserWordRepository
import com.gimslab.wordplay.util.Log
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@EnableScheduling
class UserWordBookUpdater(
		val userWordRepository: UserWordRepository,
		val userWordBookRepository: UserWordBookRepository
) {

	companion object : Log

	@Scheduled(fixedDelay = 30 * 1000)
	private fun update() {
		logger.info("++ updating user_word_book ...")
		val updateTarget = userWordRepository.findUpdateTarget()
		if (updateTarget == null) {
			logger.info("++ no update target found.")
			return
		}
		val userWordBook = userWordBookRepository.findByUserIdAndWordBookId(updateTarget.userId, updateTarget.wordBookId)
		if (userWordBook == null) {
			logger.info("++ error. userWordBook cannot be null. userId: {}, wordBookId: {}",
					updateTarget.userId, updateTarget.wordBookId)
			return
		}
		val avgProficiency = userWordRepository.findAverageProficiencyBy(updateTarget.userId, updateTarget.wordBookId)
		userWordBook.updateProficiency(avgProficiency)
		userWordBookRepository.save(userWordBook)
	}
}