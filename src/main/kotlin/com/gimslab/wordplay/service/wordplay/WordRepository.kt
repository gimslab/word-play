package com.gimslab.wordplay.service.wordplay

interface WordRepository {
	fun findRandomWord(filename: String): Word
}

