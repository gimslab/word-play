package com.gimslab.wordplay.jparepository

import com.gimslab.wordplay.service.signin.User
import com.gimslab.wordplay.service.signin.UsersRepository
import com.gimslab.wordplay.service.wordplay.UserWord
import com.gimslab.wordplay.service.wordplay.UserWordRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersJpaRepository : UsersRepository, JpaRepository<User, Long> {
}