package com.gimslab.wordplay.jparepository

import com.gimslab.wordplay.service.wordbook.WordBook
import com.gimslab.wordplay.service.wordbook.WordBookRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WordBookJpaRepository : WordBookRepository, JpaRepository<WordBook, Long> {
}