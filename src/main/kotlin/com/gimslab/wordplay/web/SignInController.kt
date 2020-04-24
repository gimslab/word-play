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
@RequestMapping("/signin")
class SignInController(
		private val signInService: SignInService,
		private val userSessionManager: UserSessionManager
) {

	companion object {
		val REGEX_FOR_USERID = Regex("[^a-z]")
	}

	@GetMapping
	fun signIn(): ModelAndView {
		val mnv = ModelAndView("signin")
		return mnv
	}

	@PostMapping
	fun signInPost(userId: String, req: HttpServletRequest, resp: HttpServletResponse)
			: String {
		validate(userId)
		signInService.signIn(userId)
		userSessionManager.makeUserSession(userId, req, resp)
		return "redirect:/word-play"
	}

	private fun validate(userId: String) {
		val lowers = userId.replace(REGEX_FOR_USERID, "")
		if (userId != lowers)
			throw IllegalArgumentException("영문 소문자만 가능합니다.")
	}
}
