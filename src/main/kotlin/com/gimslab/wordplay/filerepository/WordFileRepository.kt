package com.gimslab.wordplay.filerepository

//@Component
//@EnableScheduling
//class WordFileRepository(
//		private var filename: String?,
//		private var words: List<Word>
//) : WordRepository {
//
//	init {
//		filename = "wordbook5000-all.tsv"
//		loadFromFile()
//	}
//
//	@Scheduled(fixedDelay = 1 * 60 * 1000)
//	private fun loadFromFile() {
//		val file = File(DATA_DIRNAME, filename)
//		println("+++ ${ZonedDateTime.now()} loading file: $file ${file.exists()} ${file.canonicalPath}")
//		if (not(file.exists())) {
//			words = listOf()
//			return
//		}
//
//		val newWords = mutableListOf<Word>()
//		val br = BufferedReader(FileReader(file))
//		br.use {
//			do {
//				val line = br.readLine()
//				if (line.isNullOrBlank())
//					break
//				val st = StringTokenizer(line, FIELD_DELIMITER)
//				if (st.countTokens() < 2) {
//					println("ERROR parsing line: $line")
//					continue
//				}
//				val eng = st.nextToken()
//				val kor = st.nextToken()
//				newWords.add(Word(eng, kor))
//			} while (line.isNotBlank())
//		}
//		this.words = newWords
//	}
//
//	override fun findRandomWord(filename: String): Word {
//		if (filename != this.filename) {
//			this.filename = filename
//			loadFromFile()
//		}
//		val idx = Random(System.currentTimeMillis()).nextInt(words.size)
//		return words[idx]
//	}
//}
//
