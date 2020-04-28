package com.gimslab.wordplay.web

import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

class UserInfoControllerCommon {
	companion object {
		fun setUserInfo(req: HttpServletRequest, userSessionManager: UserSessionManager, mnv: ModelAndView) {
			mnv.addObject("userSignedIn", userSessionManager.userSignedIn(req))
			mnv.addObject("userId", userSessionManager.currentUserId(req))
		}
	}
}
