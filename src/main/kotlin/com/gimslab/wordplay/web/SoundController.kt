package com.gimslab.wordplay.web

import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/sound")
class SoundController {

	companion object {
		const val dir = "data/sound/"
	}

	@GetMapping(produces = ["audio/mpeg"])
	@ResponseBody
	fun getSound(wordId: Long): Resource {
		return FileSystemResource(dir + wordId + ".mp3")
	}
}
