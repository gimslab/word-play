package com.gimslab.wordplay.config

import com.gimslab.wordplay.jparepository.JpaRepositoryPackage
import com.gimslab.wordplay.service.WordPlayServicePackage
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@Configuration
@EnableJpaRepositories(basePackageClasses = [JpaRepositoryPackage::class])
@EntityScan(basePackageClasses = [WordPlayServicePackage::class])
class WordPlayConfig

