package com.gimslab.wordplay.web

import org.springframework.stereotype.Service
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class UserSessionManager {
	companion object {
		const val USER_ID_SESSION_KEY = "userId"
		const val USER_ID_COOKIE_NAME = "userId"
	}

	fun makeSessionFromCookie(req: HttpServletRequest, resp: HttpServletResponse) {
		if (userSignedIn(req))
			return
		val cookies = req.cookies ?: arrayOf()
		val c = cookies.asSequence().find { it.name == USER_ID_COOKIE_NAME }
				?: return
		makeUserSession(c.value, req, resp)
	}

	fun userSignedIn(httpServletRequest: HttpServletRequest) =
			currentUserId(httpServletRequest) != null

	fun currentUserId(req: HttpServletRequest) =
			req.session.getAttribute(USER_ID_SESSION_KEY) as String?

	fun makeUserSession(userId: String, req: HttpServletRequest, resp: HttpServletResponse) {
		req.session.setAttribute(USER_ID_SESSION_KEY, userId)
		resp.addCookie(
				Cookie(USER_ID_COOKIE_NAME, userId))
	}

	fun removeUserSession(req: HttpServletRequest, resp: HttpServletResponse) {
		req.session.invalidate()
		val cookie = Cookie(USER_ID_COOKIE_NAME, null)
		cookie.maxAge = 0
		cookie.path = "/"
		resp.addCookie(cookie)
	}
}
