package com.gimslab.wordplay.web

import com.gimslab.wordplay.service.wordbook.WordBook
import com.gimslab.wordplay.service.wordbook.WordBookService
import com.gimslab.wordplay.web.UserInfoControllerCommon.Companion.setUserInfo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE
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

		val wordBooks = convertAndSort(wordBookService.findAllWordBooks(currentUserId(req)))

		val mnv = ModelAndView("word-books")
		setUserInfo(req, userSessionManager, mnv)
		mnv.addObject("wordBooks", wordBooks)
		return mnv
	}

	private fun currentUserId(req: HttpServletRequest) =
			userSessionManager.currentUserId(req)

	private fun convertAndSort(wordBooks: List<WordBook>) =
			wordBooks.sortedByDescending { it.modifiedAt }
					.map { WordBookView(it) }

	data class WordBookView(val id: Long, val title: String, val modifiedAt: String) {
		constructor(wordBook: WordBook) :
				this(wordBook.id!!, wordBook.title, wordBook.modifiedAt.format(ISO_LOCAL_DATE))
	}
}

