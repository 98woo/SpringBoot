package com.hello.forum.bbs.vo;

import java.util.List;

public class BoardListVO {

	
	/**
	 * DB에서 조회한 게시글의 개수
	 */
	private int boardCnt;
	
	/**
	 * DB에서 조회한 게시글 정보의 목록
	 */
	// List 도 getter setter 로 구현할 수 있다.
	private List<BoardVO> boardList;
	
	
	public int getBoardCnt() {
		return boardCnt;
	}
	public void setBoardCnt(int boardCnt) {
		this.boardCnt = boardCnt;
	}
	public List<BoardVO> getBoardList() {
		return boardList;
	}
	public void setBoardList(List<BoardVO> boardList) {
		this.boardList = boardList;
	}
	
	
}
