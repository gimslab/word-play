package com.gimslab.wordplay.service.wordplay

interface WordRepository {
	fun findRandomWord(): Word
}

