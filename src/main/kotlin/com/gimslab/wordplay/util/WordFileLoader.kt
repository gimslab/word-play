package com.gimslab.wordplay.util

import com.gimslab.wordplay.filerepository.FileBasedRepository.Companion.FIELD_DELIMITER
import com.gimslab.wordplay.service.wordbook.WordBook
import com.gimslab.wordplay.service.wordbook.WordBookRepository
import com.gimslab.wordplay.service.wordplay.Word
import com.gimslab.wordplay.service.wordplay.WordRepository
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.time.ZonedDateTime
import java.util.*

@Service
class WordFileLoader(
		private val wordBookRepository: WordBookRepository,
		private val wordRepository: WordRepository
) {
	fun load() {
		info("word file loading...")
		val files = listFiles()
		info("targetFiles: $files")
		files.forEach {
			loadFile(it)
		}
	}

	private fun loadFile(file: File) {
		val wordBook = saveNewWordBook(file)
		val words = loadWords(file, wordBook)
		words
				.forEach {
					debug("word found: ${it.id}, ${it.wordBookId}, ${it.eng}, ${it.kor}")
					wordRepository.save(it)
				}
		file.renameTo(File(file.canonicalPath + ".done"))
	}

	private fun listFiles(): List<File> {
		val dir = File("data/load-stage")
		debug("dir: ${dir.canonicalPath}")
		val files = dir.listFiles { _, name -> name.endsWith(".tsv") }
		if (files == null)
			return emptyList()
		return files.asList()
	}

	private fun saveNewWordBook(file: File) =
			wordBookRepository.save(
					WordBook(file.nameWithoutExtension))

	private fun loadWords(file: File, wordBook: WordBook): List<Word> {
		val words = mutableListOf<Word>()
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
				words.add(Word(wordBook.id!!, eng, kor))
			} while (line.isNotBlank())
		}
		return words
	}

	private fun info(s: String) {
		println("++ $s")
	}

	private fun debug(s: String) {
		println("++ $s")
	}
}

