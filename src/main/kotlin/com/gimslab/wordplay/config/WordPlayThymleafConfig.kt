package com.gimslab.wordplay.config

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver
import org.thymeleaf.templatemode.TemplateMode


@Configuration
class WordPlayThymleafConfig(
) : WebMvcConfigurer {

	@Bean
	fun templateResolver(): SpringResourceTemplateResolver? {
		val templateResolver = SpringResourceTemplateResolver()
		templateResolver.prefix = "classpath:templates/"
		templateResolver.suffix = ".html"
		templateResolver.templateMode = TemplateMode.HTML
		templateResolver.isCacheable = true
		return templateResolver
	}

	@Bean
	fun templateEngine(): SpringTemplateEngine? {
		val templateEngine = SpringTemplateEngine()
		templateEngine.setTemplateResolver(templateResolver())
		templateEngine.enableSpringELCompiler = true
		return templateEngine
	}

	@Bean
	fun viewResolver(): ThymeleafViewResolver? {
		val viewResolver = ThymeleafViewResolver()
		viewResolver.templateEngine = templateEngine()
		return viewResolver
	}
}