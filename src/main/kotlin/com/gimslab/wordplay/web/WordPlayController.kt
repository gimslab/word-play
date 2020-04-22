package com.gimslab.wordplay.web

import com.gimslab.wordplay.service.wordplay.Word
import com.gimslab.wordplay.service.wordplay.WordService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/word-play")
class WordPlayController(
		private val wordService: WordService,
		private val userSessionManager: UserSessionManager
) {
	companion object {
	}

	@GetMapping
	fun play(req: HttpServletRequest, resp: HttpServletResponse): ModelAndView {

		// TODO move to interceptor
		userSessionManager.makeSessionFromCookie(req, resp)

		val word = findNextWord()

		val mnv = ModelAndView("main")
		mnv.setWord(word)
		mnv.addObject("userSignedIn", userSessionManager.userSignedIn(req))
		mnv.addObject("userId", userSessionManager.currentUserId(req))
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

