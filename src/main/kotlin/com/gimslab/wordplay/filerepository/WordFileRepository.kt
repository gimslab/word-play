package com.gimslab.wordplay.filerepository

import com.gimslab.wordplay.filerepository.FileBasedRepository.Companion.DATA_DIRNAME
import com.gimslab.wordplay.filerepository.FileBasedRepository.Companion.FIELD_DELIMITER
import com.gimslab.wordplay.service.wordplay.Word
import com.gimslab.wordplay.service.wordplay.WordRepository
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.time.ZonedDateTime
import java.util.*
import kotlin.random.Random

@Component
@EnableScheduling
class WordFileRepository(
		private var words: List<Word>
) : WordRepository {

	init {
		loadFromFile()
	}

	@Scheduled(fixedDelay = 1 * 60 * 1000)
	private fun loadFromFile() {
		val file = File(DATA_DIRNAME, "wordbook-level1.tsv")
		println("+++ ${ZonedDateTime.now()} loading file: $file ${file.exists()}")

		val newWords = mutableListOf<Word>()
		val br = BufferedReader(FileReader(file))
		br.use {
			do {
				val line = br.readLine()
				if (line.isNullOrBlank())
					break
				val st = StringTokenizer(line, FIELD_DELIMITER)
				if (st.countTokens() < 2) {
					println("ERROR parsing line: $line")
					continue
				}
				val eng = st.nextToken()
				val kor = st.nextToken()
				newWords.add(Word(eng, kor))
			} while (line.isNotBlank())
		}
		words = newWords
	}

	override fun findRandomWord(): Word {
		val idx = Random(System.currentTimeMillis()).nextInt(words.size)
		return words[idx]
	}
}

