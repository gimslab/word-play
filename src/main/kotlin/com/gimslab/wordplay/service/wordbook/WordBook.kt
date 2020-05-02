package com.gimslab.wordplay.service.wordbook

import java.time.ZonedDateTime
import java.time.ZonedDateTime.now
import javax.persistence.*

@Table(name = "word_book")
@Entity
class WordBook(
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long?,
		val title: String,
		val createdAt: ZonedDateTime,
		var modifiedAt: ZonedDateTime
) {
	constructor(title: String) : this(0, title, now(), now())
}

