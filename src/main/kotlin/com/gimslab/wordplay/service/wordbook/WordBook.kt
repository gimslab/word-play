package com.gimslab.wordplay.service.wordbook

import java.time.ZonedDateTime
import javax.persistence.*

@Table(name = "word_book")
@Entity
class WordBook(
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long?,
		val title: String,
		val createdTime: ZonedDateTime
) {
	constructor(title: String) : this(0, title, ZonedDateTime.now())
}

