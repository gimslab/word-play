package com.gimslab.wordplay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WordplayApplication

fun main(args: Array<String>) {
	runApplication<WordplayApplication>(*args)
}
