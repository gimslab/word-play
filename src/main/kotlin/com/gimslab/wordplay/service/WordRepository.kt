package com.gimslab.wordplay.service

import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Repository
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.time.ZonedDateTime
import java.util.*
import kotlin.random.Random

@Repository
@EnableScheduling
class WordRepository(
		private var words: List<Word>
) {
	init {
		loadFromFile()
	}

	@Scheduled(fixedDelay = 1 * 60 * 1000)
	private fun loadFromFile() {
//		val file = File("data/wordbook-sample.tsv")
		val file = File("data/wordbook-level1.tsv")
		println("+++ ${ZonedDateTime.now()} loading file: $file ${file.exists()}")

		val newWords = mutableListOf<Word>()
		val br = BufferedReader(FileReader(file))
		br.use {
			do {
				val line = br.readLine()
				if (line.isNullOrBlank())
					break
				val st = StringTokenizer(line, "\t")
				if (st.countTokens() < 2) {
					println("ERROR parsing line: $line")
					continue
				}
				val eng = st.nextToken()
				val kor = st.nextToken()
//				println("+++ $eng ::: $kor")
				newWords.add(Word(eng, kor))
			} while (line.isNotBlank())
		}
		words = newWords
	}

	fun findRandomWord(): Word {
		val idx = Random(System.currentTimeMillis()).nextInt(words.size)
		return words[idx]
	}
}

