package com.gimslab.wordplay

import com.gimslab.wordplay.util.WordFileLoader
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WordplayApplication(
		private val wordFileLoader: WordFileLoader
) {

	init {
		wordFileLoader.load()
	}
}

fun main(args: Array<String>) {
	runApplication<WordplayApplication>(*args)
}
