package com.gimslab.wordplay.service.wordplay

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserWordRepository {
	fun getOne(id: Long): UserWord
	fun findByUserIdAndWordBookId(userId: Long, wordBookId: Long): List<UserWord>
	fun save(userWord: UserWord): UserWord
	fun findTop8ByUserIdAndWordBookIdOrderByProficiencyAscModifiedAtAsc(userId: Long, wordBookId: Long): List<UserWord>
	fun findTopOneByOrderByModifiedAtDesc(): UserWord

	@Query(value="""
		select a.* from user_word a
		join user_word_book b on a.userId=b.userId and a.wordBookId=b.wordBookId
		where a.modifiedAt > b.modifiedAt
		limit 1
	""", nativeQuery = true)
	fun findUpdateTarget(): UserWord?

	@Query(value = """
		select sum(proficiency) / count(1) from user_word
		where userId=:userId and wordBookId=:wordBookId
		group by userId, wordBookId
	""", nativeQuery = true)
	fun findAverageProficiencyBy(@Param("userId") userId: Long, @Param("wordBookId") wordBookId: Long): Int
}

