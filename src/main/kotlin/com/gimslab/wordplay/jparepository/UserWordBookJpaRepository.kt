package com.gimslab.wordplay.jparepository

import com.gimslab.wordplay.service.userwordbook.UserWordBook
import com.gimslab.wordplay.service.userwordbook.UserWordBookRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserWordBookJpaRepository : UserWordBookRepository, JpaRepository<UserWordBook, Long> {
}