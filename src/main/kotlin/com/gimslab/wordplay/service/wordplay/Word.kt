package com.gimslab.wordplay.service.wordplay

data class Word(
		val eng: String,
		val kor: String
) {
	fun getHint() = eng[0]
}