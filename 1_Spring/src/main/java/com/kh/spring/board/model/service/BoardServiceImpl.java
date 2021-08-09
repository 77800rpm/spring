package com.kh.spring.board.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.board.model.dao.BoardDAO;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;

@Service("bService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private BoardDAO bDAO;

	@Override
	public int getListCount() {
		return bDAO.getListCount(sqlSession);
	}

	@Override
	public ArrayList<Board> selectList(PageInfo pi) {
		return bDAO.selectList(sqlSession, pi);
	}

	@Override
	public int insertBoard(Board b) {
		return bDAO.insertBoard(sqlSession, b);
	}

	@Override
	@Transactional
	public Board boardDetail(int bId, boolean check) {	
		int result = 0;
		Board board = null;
		
		if(check) {
			result = bDAO.addReadCount(sqlSession, bId);					
			if(result > 0) {
				board = bDAO.boardDetail(sqlSession, bId);
			}
		} else {
			board = bDAO.boardDetail(sqlSession, bId);
		}
			
		return board;
	}

	@Override
	public int updateBoard(Board b) {
		return bDAO.updateBoard(sqlSession, b);
	}

	@Override
	public int deleteBoard(int bId) {
		return bDAO.deleteBoard(sqlSession, bId);
		
	}

	@Override
	public int insertReply(Reply r) {
		return bDAO.insertReply(sqlSession, r);
	}

	@Override
	public ArrayList<Reply> selectReplyList(int boardId) {
		return bDAO.selectReplyList(sqlSession, boardId);
	}

	@Override
	public ArrayList<Board> selectTopList() {
		return bDAO.selectTopList(sqlSession);
	}

	
}
