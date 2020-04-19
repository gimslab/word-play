package com.gimslab.wordplay.web

import com.gimslab.wordplay.service.Word
import com.gimslab.wordplay.service.WordService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/word-play")
class WordPlayController(
		private val wordService: WordService
) {

	@GetMapping
	fun play(): ModelAndView {
		val word = findNextWord()
		val mnv = ModelAndView("main")
		mnv.setWord(word)
		return mnv
	}

	private fun findNextWord(): Word {
		return wordService.findNextWord()
	}
}

private fun ModelAndView.setWord(word: Word) {
	this.addObject("eng", word.eng)
	this.addObject("hint", word.getHint())
	this.addObject("kor", word.kor)
}

