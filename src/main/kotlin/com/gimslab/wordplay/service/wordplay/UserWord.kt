package com.gimslab.wordplay.service.wordplay

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
		var proficiency: Int
) {
	fun increaseProficiency() {
		proficiency++
	}

	constructor(userId: Long, wordBookId: Long, wordId: Long) :
			this(id = 0, userId = userId, wordBookId = wordBookId, wordId = wordId, proficiency = 0)
}

