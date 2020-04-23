package com.gimslab.wordplay.service.wordplay

import javax.persistence.*

@Table(name = "user_word")
@Entity
class UserWord(
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long?,

		val userId: String,
		val word: String,
		var proficiency: Int
) {
	fun increaseProficiency() {
		proficiency++
	}

	constructor(userId: String, word: String) : this(0, userId, word, 1)
}

