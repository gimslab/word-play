package com.gimslab.wordplay.web

import com.gimslab.wordplay.service.Word
import com.gimslab.wordplay.service.WordService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/signin")
class SignInController(
		private val wordService: WordService
) {

	@GetMapping
	fun signin(): ModelAndView {
		val mnv = ModelAndView("signin")
		return mnv
	}
}

