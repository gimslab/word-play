package com.gimslab.wordplay.web

import com.gimslab.wordplay.service.wordbook.WordBookService
import com.gimslab.wordplay.web.UserInfoControllerCommon.Companion.setUserInfo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/word-books")
class WordBookController(
		private val wordBookService: WordBookService,
		private val userSessionManager: UserSessionManager
) {

	@GetMapping
	fun list(req: HttpServletRequest, resp: HttpServletResponse): ModelAndView {

		// TODO move to interceptor
		userSessionManager.makeSessionFromCookie(req, resp)

		val wordBooks = wordBookService.findAllWordBooks()

		val mnv = ModelAndView("word-books")
		setUserInfo(req, userSessionManager, mnv)
		mnv.addObject("wordBooks", wordBooks)
		return mnv
	}
}

