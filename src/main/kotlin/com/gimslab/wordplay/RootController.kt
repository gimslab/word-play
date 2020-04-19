package com.gimslab.wordplay

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class RootController {

	@GetMapping("/")
//	@ResponseBody
	fun root(): String {
		return "redirect:/word-play"
	}

	@GetMapping("/hello")
	@ResponseBody
	fun hello(): String {
		return "Hello Word Play"
	}
}
