package com.gimslab.wordplay.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/signOut")
class SignOutController(
		private val userSessionManager: UserSessionManager
) {

	@GetMapping
	fun signOut(req: HttpServletRequest, resp: HttpServletResponse): String {
		userSessionManager.removeUserSession(req, resp)
		return "redirect:/word-play"
	}
}

