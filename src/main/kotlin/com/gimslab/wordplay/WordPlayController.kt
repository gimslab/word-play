package com.gimslab.wordplay

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import kotlin.random.Random

@Controller
@RequestMapping("/word-play")
class WordPlayController {

	@GetMapping
	fun play(): ModelAndView {
		val word = findNextWord()
		val mnv = ModelAndView("main")
		mnv.setWord(word)
		return mnv
	}

	companion object {
		val words = listOf(
				Word("before", "(시간상으로 ~보다) ~전에"),
				Word("boring", "재미없는, 지루한"),
				Word("safe", "안전한, 안심할 수 있는"),
				Word("area", "지역, 구역"),
				Word("food", "음식, 식량"),
				Word("body", "몸, 신체"),
				Word("leg", "(신체 일부분) 다리"),
				Word("sun", "해, 태양"),
				Word("away", "(시간, 공간적으로) 떨어져"),
				Word("baby", "아기, 새끼"),
				Word("dish", "요리, 접시")
		)
	}

	private fun findNextWord(): Word {
		val i = Random(System.currentTimeMillis()).nextInt(words.size)
		return words[i]
	}
}

private fun ModelAndView.setWord(word: Word) {
	this.addObject("eng", word.eng)
	this.addObject("hint", word.getHint())
	this.addObject("kor", word.kor)
}

