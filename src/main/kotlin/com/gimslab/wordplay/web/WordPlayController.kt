package com.gimslab.wordplay.web

import com.gimslab.wordplay.service.wordplay.UserWord
import com.gimslab.wordplay.service.wordplay.Word
import com.gimslab.wordplay.service.wordplay.WordService
import com.gimslab.wordplay.util.ReadabilityHelper.Companion.not
import com.gimslab.wordplay.web.UserInfoControllerCommon.Companion.setUserInfo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
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

	@GetMapping
	fun play(wordBookId: Long, req: HttpServletRequest, resp: HttpServletResponse): ModelAndView {

		// TODO move to interceptor
		userSessionManager.makeSessionFromCookie(req, resp)

		val word = findNextWord(wordBookId)
		val userWord = findUserWord(word, req)

		val mnv = ModelAndView("main")
		setUserInfo(req, userSessionManager, mnv)
		mnv.addObject("wordBookId", wordBookId)
		mnv.setWord(word)
		if (signedInStatus(req))
			mnv.addObject("proficiency", userWord?.proficiency ?: 0)
		return mnv
	}

	@PostMapping
	fun post(gotWord: String, req: HttpServletRequest): String {
		if (not(signedInStatus(req)))
			return "redirect:/signin"
		increaseProficiency(gotWord, req)
		return "redirect:/word-play"
	}

	private fun findNextWord(wordBookId: Long): Word {
		return wordService.findNextWord(wordBookId)
	}

	private fun findUserWord(word: Word, req: HttpServletRequest): UserWord? {
		val userId = userSessionManager.currentUserId(req)
		return if (userId != null)
			wordService.findBy(userId, word.eng)
		else
			null
	}

	private fun increaseProficiency(gotWord: String, req: HttpServletRequest) {
		val userId = userSessionManager.currentUserId(req)
		if (userId != null)
			wordService.increaseProficiency(userId, gotWord)
	}

	private fun signedInStatus(req: HttpServletRequest) = userSessionManager.currentUserId(req) != null
}

private fun ModelAndView.setWord(word: Word) {
	this.addObject("eng", word.eng)
	this.addObject("hint", word.getHint())
	this.addObject("kor", word.kor)
}

