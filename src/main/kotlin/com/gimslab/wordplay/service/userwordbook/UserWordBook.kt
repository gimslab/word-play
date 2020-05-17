package com.gimslab.wordplay.service.userwordbook

import com.gimslab.wordplay.service.wordbook.WordBook
import java.time.ZonedDateTime
import java.time.ZonedDateTime.now
import javax.persistence.*

@Table(name = "user_word_book")
@Entity
class UserWordBook(
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long?,
		val userId: Long,
		var wordBookId: Long,
		val wordBookTitle: String,
		val createdAt: ZonedDateTime,
		var modifiedAt: ZonedDateTime
) {
	constructor(userId: Long, wordBook: WordBook) : this(id = null,
			userId = userId, wordBookId = wordBook.id!!,
			wordBookTitle = wordBook.title, createdAt = now(), modifiedAt = now())

	fun updateModifiedAt() {
		modifiedAt = now()
	}
}

