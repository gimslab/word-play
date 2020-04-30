package com.gimslab.wordplay.web

import com.gimslab.wordplay.service.signin.UsersRepository
import com.gimslab.wordplay.util.ReadabilityHelper.Companion.not
import org.springframework.stereotype.Service
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class UserSessionManager(
		private val usersRepository: UsersRepository
) {
	companion object {
		const val USER_ID_SESSION_KEY = "userId" // userId is a pk of user table
		const val USER_SIGN_ID_SESSION_KEY = "userSignId" // user signId
		const val USER_ID_COOKIE_NAME = "userId"
	}

	fun makeSessionFromCookie(req: HttpServletRequest, resp: HttpServletResponse) {
		if (userSignedIn(req))
			return
		val cookies = req.cookies ?: arrayOf()
		val c = cookies.asSequence().find { it.name == USER_ID_COOKIE_NAME }
				?: return
		try {
			val userIdLong = c.value.toLong()
			makeUserSession(userIdLong, req, resp)
		} catch (e: NumberFormatException) {
			debug("cannot convert to userId from cookie: $c.value")
			removeUserSession(req, resp)
		}
	}

	fun userSignedIn(req: HttpServletRequest) =
			currentUserId(req) != null

	fun currentUserId(req: HttpServletRequest) =
			req.session.getAttribute(USER_ID_SESSION_KEY) as Long?

	fun currentUserSignId(req: HttpServletRequest) =
			req.session.getAttribute(USER_SIGN_ID_SESSION_KEY) as String?

	fun makeUserSession(userId: Long, req: HttpServletRequest, resp: HttpServletResponse) {
		val userSignIdFound = findUserSignIdBy(userId)
		if (userSignIdFound == null) {
			removeUserSession(req, resp)
			return
		}
		req.session.setAttribute(USER_ID_SESSION_KEY, userId)
		req.session.setAttribute(USER_SIGN_ID_SESSION_KEY, userSignIdFound)
		resp.addCookie(
				Cookie(USER_ID_COOKIE_NAME, userId.toString()))
	}

	private fun findUserSignIdBy(userId: Long): String? {
		val user = usersRepository.getOne(userId)
		return user?.signId
	}

	fun removeUserSession(req: HttpServletRequest, resp: HttpServletResponse) {
		req.session.invalidate()
		val cookie = Cookie(USER_ID_COOKIE_NAME, null)
		cookie.maxAge = 0
		cookie.path = "/"
		resp.addCookie(cookie)
	}

	private fun debug(s: String) {
		println("++ $s")
	}
}
