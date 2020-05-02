package com.gimslab.wordplay.service.wordplay

import java.time.ZonedDateTime
import java.time.ZonedDateTime.now
import javax.persistence.*

@Table(name = "user_word")
@Entity
class UserWord(
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long?,

		val userId: Long,
		val wordBookId: Long,
		val wordId: Long,
		var proficiency: Int,
		val createdAt: ZonedDateTime,
		var modifiedAt: ZonedDateTime
) {
	fun increaseProficiency() {
		proficiency++
		updateModifiedAt()
	}

	private fun updateModifiedAt() {
		modifiedAt = now()
	}

	constructor(userId: Long, wordBookId: Long, wordId: Long) :
			this(id = 0, userId = userId, wordBookId = wordBookId,
					wordId = wordId, proficiency = 0,
					createdAt = now(), modifiedAt = now())
}

