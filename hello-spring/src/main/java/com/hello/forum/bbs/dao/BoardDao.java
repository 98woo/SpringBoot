package com.hello.forum.bbs.dao;

import java.util.List;

import com.hello.forum.bbs.vo.BoardVO;

public interface BoardDao {
	
	/**
	 * DB에 저장된 모든 게시글의 수를 조회한다.
	 * (SELECT COUNT(1) FROM BOARD;)
	 * @return
	 */
	public int getBoardAllCount();
	
	/**
	 * DB에 저장된 모든 게시글의 정보를 조회한다.
	 * (SELECT * FROM BOARD WHERE DEL_YN = 'N';)
	 * @return
	 */
	public List<BoardVO> getAllBoard();
}
