package com.gimslab.wordplay.web

import com.gimslab.wordplay.service.userwordbook.UserWordService
import com.gimslab.wordplay.service.wordplay.UserWord
import com.gimslab.wordplay.service.wordplay.UserWordRepository
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
		private val userSessionManager: UserSessionManager,
		private val userWordService: UserWordService
) {

	@GetMapping
	fun play(wordBookId: Long, req: HttpServletRequest, resp: HttpServletResponse): ModelAndView {

		// TODO move to interceptor
		userSessionManager.makeSessionFromCookie(req, resp)

		val userWord = findNextUserWord(wordBookId, req)
		val word = findWord(userWord?.wordId, wordBookId)

		val mnv = ModelAndView("main")
		setUserInfo(req, userSessionManager, mnv)
		mnv.addObject("wordBookId", wordBookId)
		mnv.setWord(word)
		if (signedInStatus(req))
			mnv.addObject("proficiency", userWord?.proficiency ?: 0)
		return mnv
	}

	@PostMapping
	fun post(wordId: Long, wordBookId: Long, req: HttpServletRequest): String {
		if (not(signedInStatus(req)))
			return "redirect:/signin"
		increaseProficiency(wordId, wordBookId, req)
		return "redirect:/word-play?wordBookId=$wordBookId"
	}

	private fun findNextUserWord(wordBookId: Long, req: HttpServletRequest): UserWord? {
		val userId = userSessionManager.currentUserId(req)
		return userWordService.findNextUserWord(userId, wordBookId)
	}

	private fun findWord(wordId: Long?, wordBookId: Long): Word {
		if (wordId == null)
			return Word(wordBookId, "error", "오류")
		val word = wordService.findWordById(wordId)
		return if (word != null)
			word
		else
			Word(wordBookId, "error", "오류")
	}

	private fun increaseProficiency(wordId: Long, wordBookId: Long, req: HttpServletRequest) {
		val userId = userSessionManager.currentUserId(req)
		if (userId != null)
			wordService.increaseProficiency(userId, wordBookId, wordId)
	}

	private fun signedInStatus(req: HttpServletRequest) = userSessionManager.currentUserId(req) != null
}

private fun ModelAndView.setWord(word: Word) {
	this.addObject("wordId", word.id)
	this.addObject("eng", word.eng)
	this.addObject("hint", word.getHint())
	this.addObject("kor", word.kor)
}

