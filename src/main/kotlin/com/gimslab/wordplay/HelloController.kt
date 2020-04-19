package com.gimslab.wordplay

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/hello")
class HelloController {

	@GetMapping
	@ResponseBody
	fun hello(): String {
		return "hello 4/19 14:00"
	}
}
