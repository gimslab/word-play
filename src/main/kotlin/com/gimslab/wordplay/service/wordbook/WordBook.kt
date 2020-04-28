package com.gimslab.wordplay.service.wordbook

import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "word_book")
@Entity
class WordBook(
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long?,

		val title: String,
		val filename: String,
		var createdTime: LocalDateTime
)

