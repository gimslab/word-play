package com.gimslab.wordplay.web

import com.gimslab.wordplay.service.userwordbook.UserWordBookService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/user-word-book")
class UserWordBookController(
		private val userWordBookService: UserWordBookService,
		private val userSessionManager: UserSessionManager
) {

	@GetMapping
	fun get(wordBookId: Long, req: HttpServletRequest, resp: HttpServletResponse): String {

		// TODO move to interceptor
		userSessionManager.makeSessionFromCookie(req, resp)

		prepareUserWordBook(wordBookId, req)

		return "redirect:word-play?wordBookId=$wordBookId"
	}

	private fun prepareUserWordBook(wordBookId: Long, req: HttpServletRequest) {
		val userId = currentUserId(req) ?: 0
		userWordBookService.prepareUserWordbook(userId, wordBookId)
	}

	private fun currentUserId(req: HttpServletRequest) =
			userSessionManager.currentUserId(req)
}

