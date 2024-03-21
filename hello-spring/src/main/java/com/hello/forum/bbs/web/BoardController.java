package com.hello.forum.bbs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hello.forum.bbs.service.BoardService;
import com.hello.forum.bbs.vo.BoardListVO;
import com.hello.forum.bbs.vo.BoardVO;

@Controller
public class BoardController {

	/*
	 * Bean Container 에서 BoardService 타입의 객체를 찾아
	 * 아래 맴버변수에게 할당한다. (DI : Dependency Injection)
	 */
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/list")
	public String viewBoardListPage(Model model) {
		
		// 1. 게시글의 건수와 게시글의 목록을 조회해서
		BoardListVO boardListVO = this.boardService.getAllBoard();
		// 2. /WEB-INF/views/board/boardlist.jsp 페이지에서 게시글의 건수와 게시글의 목록을 전달하고
		// VO 에서 생성한 boardList 를 Key 로 boardListVO 를 객체로 전달한다.
		model.addAttribute("boardList", boardListVO);
		// 3. 화면을 보여준다. -> return 페이지
		
		return "/board/boardlist";
	}
	
	/**
	 * 게시글 작성페이지를 보여주는 URL
	 */
	@GetMapping("/board/write")	// 브라우저에서 링크를 클릭하거나 브라우저 URL을 직접 입력할 때 
	public String viewBoardWritePage() {
		return "/board/boardwrite";
	}
	
	/**
	 * 스프링 애플리케이션을 개발할 때 같은 URL을 정의할 수 없다.
	 * Method 가 다른 경우에만 예외적으로 허용한다.
	 * GET / board / write
	 * POST / board / write
	 * 
	 * 글 등록페이지에서 게시글을 저장하고 "저장" 버튼을 클릭하면
	 * 데이터베이스에 글 정보를 저장(insert) 해야 한다.
	 * 
	 * 사용자가 작성한 글 정보를 알아야한다.
	 * 1. Servlet 처럼 구현. HttpServletRequest 객체
	 * 2. @RequestParam	(Servlet like -> 조금 더 편하게 사용)
	 * 3. Command Object : 보편적으로 많이 사용하는 방법 > 파라미터 처리가 매우 편하다!!!
	 * 4. @PathVariable
	 * 
	 * 세 가지 방법을 다 알아야 한다.
	 * 
	 * @return
	 */
	// 1번 방법 HttpServletRequest 객체
//	@PostMapping("/board/write")
//	public String doBoardWritePage(HttpServletRequest request) {
//		System.out.println("곧 등록 처리를 해야합니다.");
//		/*
//		 * Servlet Like
//		 * HttpServletRequest를 이용
//		 * Intercepter에서 이용
//		 * Filter에서 이용.
//		 */
//		
//		String subject = request.getParameter("subject"); // jsp의 name으로 설정한 것을 파라미터로 가져온다.
//		String email = request.getParameter("email");
//		String content = request.getParameter("content");
//		
//		System.out.println("제목 : " + subject);
//		System.out.println("이메일 : " + email);
//		System.out.println("내용 : " + content);
//		
//		
//		return null;
//	}
	
	
	
	// 2번 방법 @RequestParam
//	@PostMapping("/board/write")	// Spring이 알맞은 파라미터를 자동으로 보내준다.
//	// 컨트롤러로 전송된 파라미터를 하나씩 받아오는 방법.
//	// @RequestParam 이 jsp 의 name 에 할당된 파라미터를 가져온다.
//	// @RequestParam으로 정의된 파라미터는 필수 파라미터!!
//	// 컨트롤러로 전송되는 파라미터의 개수가 몇개 없을 때 사용한다. (예: 3개 미만)
//	public String doBoardWritePage(@RequestParam String subject, @RequestParam String email, @RequestParam String content) {
//		System.out.println("곧 등록 처리를 해야합니다.");
//		/*
//		 * Servlet Like
//		 * HttpServletRequest를 이용
//		 * Intercepter에서 이용
//		 * Filter에서 이용.
//		 */
////		String subject = request.getParameter("subject");	
////		String email = request.getParameter("email");
////		String content = request.getParameter("content");
//		
//		System.out.println("제목 : " + subject);
//		System.out.println("이메일 : " + email);
//		System.out.println("내용 : " + content);
//		
//		
//		return null;
//	}
	
	
	
	// 3번 방법 Command Object
	// Command Object
	// 파라미터로 전송된 이름과 BoardVO 의 맴버변수의 이름과 같은 것이 있다면
	// 해당 맴버변수에 파라미터의 값을 할당해준다/!! (setter 이용)
	@PostMapping("/board/write")
	public String doBoardWritePage(BoardVO boardVO) {
//		System.out.println("제목 : " + boardVO.getSubject());
//		System.out.println("이메일 : " + boardVO.getEmail());
//		System.out.println("내용 : " + boardVO.getContent());
		
		boolean isCreateSuccess = this.boardService.createNewBoard(boardVO);
		if (isCreateSuccess) {
			System.out.println("글 등록 성공");
		}
		else {
			System.out.println("글 등록 실패");
		}
		
		// DB에 입력한 정보를 입력했으면 입력한 정보를 확인하는 페이지를 확인해야 한다.
		// board/boardlist 페이지를 보여주는 URL로 이동처리를 해야 한다. 
		// redirect:/board/list
		// 스프링은 브라우저에게 /board/list 로 이동하려는 명령을 전송.
		// 명령을 받은 브라우저는 /board/list 로 URL 을 이동시킨다.
		// 스프링 컨트롤러에서 /board/list URL 에 알맞은 처리를 진행한다.
		return "redirect:/board/list"; // 
	}
	
	// browser 에서 URL 을 http://localhost:8080/board/view?id=1 이렇게 전달 되었을 경우 
	// URL 뒤에 ?의 의미 -> Query Parameter 
	// ?id=1 <-- Paramter Key : id, Parameter Value: 1
	// 여러 개를 보낼 경우 id = 1 & subject = abc 등 & 로 연결할 수 있다.
	// ?id=1&subject=abc <-- Parameter Key : id, Parameter Value : 1 / Parameter Key : subject, Parameter Value : abc
	// 1. boardService 에게 파라미터로 전달 받은 id 값을 보내준다.
	// 2. boardService 는 파라미터로 전달 받은 id의 게시글 정보를 조회해서 반환해주면 
	// 3. boardview 페이지에 데이터를 전송해준다.
	// 4. 화면을 보여준다.
	@GetMapping("/board/view")
	public String viewBoardDetailPage(@RequestParam int id, Model model) {
		
		// 1. boardService 에게 파라미터로 전달 받은 id 값을 보내준다.
		// 2. boardService 는 파라미터로 전달 받은 id의 게시글 정보를 조회해서 반환해주면 
		BoardVO boardVO = this.boardService.getOneBoard(id, true);
		
		// 3. boardview 페이지에 데이터를 전송해준다.
		model.addAttribute("boardVO", boardVO);
		// 4. 화면을 보여준다.
		
		return "board/boardview";
	}
	
	// browser 에서 URL 을 http://localhost:8080/board/view?id=1 -> view/1 로 사용해보기. (쿼리파라미터 다르게 써보기)
	
	@GetMapping("/board/modify/{id}")	// 변수의 사용할 경우 {} 사용
	public String viewBoardModifyPage(@PathVariable int id, Model model) {
		// 1. 전달 받은 id의 값을 게시글을 조회한다.
		BoardVO boardVO = this.boardService.getOneBoard(id, false);
		
		// 2. 게시글의 정보를 화면에 보내준다.
		model.addAttribute("boardVO", boardVO);
		// 3. 화면을 보여준다.
		return "board/boardmodify";
	}
	
	/**
	 * 게시글을 수정한다.
	 * @param id 수정할 게시글의 번호
	 * @param boardVO 사용자가 입력한 수정된 게시글의 정보 (제목, 이메일, 내용)
	 * @return
	 */
	@PostMapping("/board/modify/{id}")
	public String doBoardModify(@PathVariable int id, BoardVO boardVO) {
		
		// Command Object 에는 전달된 ID가 없으므로
		// PathVariable 로 전달된 ID를 세팅해준다.
		boardVO.setId(id);
		
		boolean isUpdatedSuccess = this.boardService.updateOneBoard(boardVO);
		
		if (isUpdatedSuccess) {
			System.out.println("수정 성공했습니다.");
		}
		else {
			System.out.println("수정 실패했습니다.");
		}
		
		return "redirect:/board/view?id=" + id;
	}
	
	/*
	 * GET / POST
	 * GET 데이터 조회. (페이지 보여주기, 게시글 정보 보여주기)
	 * POST 데이터 등록. (게시글 등록하기)
	 * PUT 데이터 수정 (게시글 수정하기, 좋아요 처리하기, 추천 처리하기)
	 * DELETE 데이터 삭제 (게시글 삭제하기, 댓글 삭제하기)
	 * 
	 * JSP 의 경우 PUT, DELETE 를 지원하지 않음. 오로지 GET, POST 만 지원.
	 * 		데이터 조회, 등록, 수정, 삭제 GET/POST 를 이용해서 작성.
	 * 
	 * FROM 으로 데이터를 등록하거나 수정할 경우 -> POST
	 * URL이나 링크 등으로 데이터를 조회하거나 삭제할 경우 -> GET
	 */
	
	@GetMapping("/board/delete/{id}")
	public String doDeleteBoard(@PathVariable int id, Model model) {
		
		boolean isDeletedSuccess = this.boardService.deleteOneBoard(id);
		
		if (isDeletedSuccess) {
			System.out.println("게시글 삭제 성공.");
		}
		else {
			System.out.println("게시글 삭제 실패.");
		}
		
		
		
		return "redirect:/board/list";
	}
}
