package com.gimslab.wordplay.web

import com.gimslab.wordplay.service.signin.SignInService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/signIn")
class SignInController(
		private val signInService: SignInService,
		private val userSessionManager: UserSessionManager
) {

	companion object {
	}

	@GetMapping
	fun signIn(): ModelAndView {
		val mnv = ModelAndView("signIn")
		return mnv
	}

	@PostMapping
	fun signInPost(userId: String, req: HttpServletRequest, resp: HttpServletResponse)
			: String {
		signInService.signIn(userId)
		userSessionManager.makeUserSession(userId, req, resp)
		return "redirect:/word-play"
	}
}

