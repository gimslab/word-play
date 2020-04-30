package com.gimslab.wordplay.jparepository

import com.gimslab.wordplay.service.wordplay.Word
import com.gimslab.wordplay.service.wordplay.WordRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WordJpaRepository : WordRepository, JpaRepository<Word, Long> {
	override fun findByWordBookId(wordBookId: Long): List<Word>
}