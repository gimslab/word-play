package com.gimslab.wordplay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WordplayApplication {
	init {
		for (i in 0..10)
			println("++++++++++++++")
	}
}

fun main(args: Array<String>) {
	runApplication<WordplayApplication>(*args)
}
