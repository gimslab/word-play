package com.gimslab.wordplay.filerepository

import com.gimslab.wordplay.filerepository.FileBasedRepository.Companion.DATA_DIRNAME
import com.gimslab.wordplay.filerepository.FileBasedRepository.Companion.DTTM_FORMATTER
import com.gimslab.wordplay.filerepository.FileBasedRepository.Companion.FIELD_DELIMITER
import com.gimslab.wordplay.service.signin.User
import com.gimslab.wordplay.service.signin.UsersRepository
import com.gimslab.wordplay.util.ReadabilityHelper.Companion.not
import org.springframework.stereotype.Repository
import java.io.*
import java.lang.System.lineSeparator
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Repository
class UsersFileRepository(
		private var users: MutableList<User>
) : UsersRepository {

	companion object {
		const val USERS_FILENAME = "$DATA_DIRNAME/users.tsv"
	}

	init {
		loadFile()
	}

	override fun findById(userId: String): User? {
		if (users.isEmpty())
			return null
		return users.asSequence().find { it.userId == userId }
	}

	override fun addUser(user: User) {
		users.add(user)
		flushToFile()
	}

	private fun loadFile() {
		val file = File(USERS_FILENAME)
		val list = mutableListOf<User>()
		if (not(file.exists())) {
			users = list
			return
		}
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
				val userId = st.nextToken()
				val registerdAt = ZonedDateTime.parse(st.nextToken(), DTTM_FORMATTER)
				list.add(User(userId, registerdAt))
			} while (line.isNotBlank())
		}
		users = list
	}

	private fun flushToFile() {
		val fileOld = File(USERS_FILENAME)
		val fileBak = File("$USERS_FILENAME.bak")
		val fileTmp = File("$USERS_FILENAME.new")
		val bw = BufferedWriter(FileWriter(fileTmp))
		bw.use {
			users.forEach {
				bw.write(it.userId + FIELD_DELIMITER + it.registeredAt.format(DTTM_FORMATTER) + lineSeparator())
			}
		}
		fileOld.renameTo(fileBak)
		fileTmp.renameTo(fileOld)
	}
}

