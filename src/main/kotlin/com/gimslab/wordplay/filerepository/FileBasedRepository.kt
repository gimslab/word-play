package com.gimslab.wordplay.filerepository

import java.time.format.DateTimeFormatter

interface FileBasedRepository {
	companion object {
		const val DATA_DIRNAME = "data"
		const val FIELD_DELIMITER = "\t"
		val DTTM_FORMATTER = DateTimeFormatter.ISO_DATE_TIME
	}

}
