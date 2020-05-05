package com.gimslab.wordplay.web

import com.gimslab.wordplay.service.userwordbook.UserWordService
import com.gimslab.wordplay.service.wordbook.WordBookService
import com.gimslab.wordplay.service.wordplay.UserWord
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
		private val userWordService: UserWordService,
		private val wordBookService: WordBookService
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
		mnv.addObject("word", word)
		mnv.addObject("wordBookTitle", findWordBookTitle(wordBookId))
		mnv.addObject("userWordId", userWord?.id ?: 0)
		mnv.addObject("proficiency", userWord?.proficiency ?: 0)
		return mnv
	}

	private fun findWordBookTitle(wordBookId: Long) =
			wordBookService.findCachedWordBookTitleById(wordBookId)

	@PostMapping
	fun post(wordId: Long, wordBookId: Long, userWordId: Long, req: HttpServletRequest): String {
		if (not(signedInStatus(req)))
			return "redirect:/signin"
		increaseProficiency(wordId, wordBookId, userWordId, req)
		return "redirect:/word-play?wordBookId=$wordBookId"
	}

	private fun findNextUserWord(wordBookId: Long, req: HttpServletRequest): UserWord? {
		val userId = currentUserId(req)
		return userWordService.findNextUserWord(userId, wordBookId)
	}

	private fun findWord(wordId: Long?, wordBookId: Long): WordView {
		if (wordId == null)
			return WordView.error(wordBookId)
		val word = wordService.findWordById(wordId)
		return if (word != null)
			WordView(word)
		else
			WordView.error(wordBookId)
	}

	private fun increaseProficiency(wordId: Long, wordBookId: Long, userWordId: Long, req: HttpServletRequest) {
		val userId = currentUserId(req)
		if (userId != null)
			wordService.increaseProficiency(userId, wordBookId, wordId, userWordId)
	}

	private fun signedInStatus(req: HttpServletRequest) = currentUserId(req) != null

	private fun currentUserId(req: HttpServletRequest) =
			userSessionManager.currentUserId(req)
}


