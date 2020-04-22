package com.gimslab.wordplay.service.wordplay

import javax.persistence.*

@Table(name = "user_word")
@Entity
class UserWord(
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long?,

		val userId: String,
		val wordEng: String,
		var experience: Int?
) {
	constructor(userId: String, wordEng: String) : this(0, userId, wordEng, 0)
}

