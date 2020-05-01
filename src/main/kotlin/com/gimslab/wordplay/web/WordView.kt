package com.gimslab.wordplay.web

import com.gimslab.wordplay.service.wordplay.Word

data class WordView(
		val wordId: Long?,
		val wordBookId: Long,
		val eng: String,
		val kor: String
) {
	constructor(word: Word) : this(word.id, word.wordBookId, word.eng, word.kor)

	fun getHint() = eng[0]

	companion object {
		fun error(wordBookId: Long): WordView {
			return WordView(null, wordBookId, "error", "오류")
		}
	}
}


