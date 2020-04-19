package com.gimslab.wordplay

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/word-play")
class WordPlayController {

	@GetMapping
//	@ResponseBody
	fun play(): String {
//		return "word....."
		return "a.html"
	}
}
