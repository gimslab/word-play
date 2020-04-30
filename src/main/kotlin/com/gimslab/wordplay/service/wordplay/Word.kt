package com.gimslab.wordplay.service.wordplay

import javax.persistence.*

@Table(name = "word")
@Entity
class Word(
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long?,
		val wordBookId: Long,
		val eng: String,
		val kor: String
) {
	fun getHint() = eng[0]

	constructor(wordBookId: Long, eng: String, kor: String) : this(0, wordBookId, eng, kor)
}