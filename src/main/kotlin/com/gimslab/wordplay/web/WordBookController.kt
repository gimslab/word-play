package com.gimslab.wordplay.web

import com.gimslab.wordplay.service.userwordbook.UserWordBook
import com.gimslab.wordplay.service.userwordbook.UserWordBookRepository
import com.gimslab.wordplay.service.wordbook.WordBook
import com.gimslab.wordplay.service.wordbook.WordBookService
import com.gimslab.wordplay.web.UserInfoControllerCommon.Companion.setUserInfo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/word-books")
class WordBookController(
		private val wordBookService: WordBookService,
		private val userSessionManager: UserSessionManager,
		val userWordBookRepository: UserWordBookRepository
) {

	@GetMapping
	fun list(req: HttpServletRequest, resp: HttpServletResponse): ModelAndView {

		// TODO move to interceptor
		userSessionManager.makeSessionFromCookie(req, resp)

		val wordBooks = findWordBooks(req)

		val mnv = ModelAndView("word-books")
		setUserInfo(req, userSessionManager, mnv)
		mnv.addObject("wordBooks", wordBooks)
		return mnv
	}

	private fun findWordBooks(req: HttpServletRequest): List<WordBookView> {
		val userWordBooks = findUserWordBooksBy(currentUserId(req))
		val allWordBooks = findAllWordBooks()
		return (userWordBooks + allWordBooks)
				.distinctBy { it.id }
				.sortedByDescending { it.modifiedAt }
	}

	private fun findUserWordBooksBy(userId: Long?): List<WordBookView> {
		return if (userId == null)
			listOf()
		else
			userWordBookRepository.findByUserId(userId).map { WordBookView(it) }
	}

	private fun findAllWordBooks() =
			wordBookService.findAllWordBooks().map { WordBookView(it) }

	private fun currentUserId(req: HttpServletRequest) =
			userSessionManager.currentUserId(req)

	data class WordBookView(
			val id: Long,
			val title: String,
			val proficiency: Int,
			val modifiedAt: ZonedDateTime,
			val modifiedAtStr: String) {

		constructor(wordBook: WordBook) : this(
				id = wordBook.id!!,
				title = wordBook.title,
				proficiency = 0,
				modifiedAt = wordBook.modifiedAt,
				modifiedAtStr = wordBook.modifiedAt.format(ISO_LOCAL_DATE))

		constructor(uwb: UserWordBook) : this(
				id = uwb.wordBookId,
				title = uwb.wordBookTitle,
				proficiency = uwb.proficiency ?: 0,
				modifiedAt = uwb.modifiedAt,
				modifiedAtStr = uwb.modifiedAt.format(ISO_LOCAL_DATE))
	}
}

