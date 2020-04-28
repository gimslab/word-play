package com.gimslab.wordplay.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class RootController {

	@GetMapping("/")
	fun root(): String {
		return "redirect:/word-books"
	}

	@GetMapping("/hello")
	@ResponseBody
	fun hello(): String {
		return "Hello Word Play"
	}
}
