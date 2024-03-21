package com.hello.forum.bbs.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hello.forum.bbs.vo.BoardVO;

/**
 * DB에 쿼리를 전송 및 실행하고 결과를 받아오는 클래스.
 * 
 * SqlSessionDaoSupport : MyBatis 가 Database에 접속을 해서 데이터들을 제어하는 역할을 수행한다.
 * 						 Insert, Select, Update, Delete
 * 
 * @Repository : Bean Container 에 적재를 시키기 위한 힌트. (데이터 접근 제어)
 * @Controller : Spring이 클래스를 객체화 시켜서 Bean Container 에 적재.
 * 둘 다 Spring을 객체화한다.
 */
@Repository
public class BoardDaoImpl extends SqlSessionDaoSupport implements BoardDao {

	
	
	/*
	 * @Autowired : Bean Contaiber / DI (Dependency Injection)
	 * Bean Contaiber : Spring Framwork 가 객체들을 생성해서 보관하는 메모리 공간.
	 * 					@Controller, @Repository
	 * DI (Dependency Injection)
	 * Bean Container 가 보관되어 있는 객체를 필요한 곳에 자동 주입하는 기술.
	 * 실행하는 방법
	 * 1. 생성자를 이용하는 방법
	 * 2. Setter 를 이용하는 방법  --> @Autowired
	 * 3. 맴버변수를 이용하는 방법
	 * 
	 * mybatis 는 Setter 만 사용할 수 있다.
	 */
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	
	
	@Override
	public int getBoardAllCount() {

		
		// session : 서버와 클라이언트가 연결되어 있는 정보.
		// 서버 : Database Server
		// 클라이언트 : 스프링 애플리케이션
		// sqlSession를 받아오는 명령어가 getsqlSession() 이다.
		// namespace 와 맞는 mapper 를 찾아가서 해당 메서드를 실행시킨다.
		// extends SqlsessionDaoSupport 
		// sqlSessionTemplate 에는 yml 에 입력한 db 정보가 들어간다.
		// MyBatis 에 db 정보를 보내야 한다.
		return getSqlSession().selectOne("com.hello.forum.bbs.dao.BoardDao.getBoardAllCount");
	}

	@Override
	public SqlSessionTemplate getSqlSessionTemplate() {
		
		return super.getSqlSessionTemplate();
	}

	@Override
	public List<BoardVO> getAllBoard() {
		
		return getSqlSession().selectList("com.hello.forum.bbs.dao.BoardDao.getAllBoard");
	}



	// VO 데이터클래스에 데이터를 담아서 VO 자체를 데이터로 전달하고 전달 받는다.
	@Override
	public int insertNewBoard(BoardVO boardVO) {
		/*
		 * MyBatis 호출해서 쿼리가 실행될 수 있도록 코드 
		 * 코드에 파라미터가 필요할 경우에 쿼리셀렉터 이후에 파라미터를 전달
		 * 전달할 수 있는 파라미터의 개수 1개 
		 */
		return getSqlSession().insert(BoardDao.NAME_SPACE + ".insertNewBoard", boardVO);
	}



	@Override
	public BoardVO selectOneBoard(int id) {
		
		return getSqlSession().selectOne(BoardDao.NAME_SPACE + ".selectOneBoard", id);
	}



	@Override
	public int increaseViewCount(int id) {
		
		return getSqlSession().update(BoardDao.NAME_SPACE + ".increaseViewCount", id);
	}



	@Override
	public int updateOneBoard(BoardVO boardVO) {
		
		return getSqlSession().update(BoardDao.NAME_SPACE + ".updateOneBoard", boardVO);
	}



	@Override
	public int deleteOneBoard(int id) {
		
		return getSqlSession().update(BoardDao.NAME_SPACE + ".deleteOneBoard", id);
	}
	
}
