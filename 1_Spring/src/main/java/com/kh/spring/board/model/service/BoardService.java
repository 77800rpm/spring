package com.kh.spring.board.model.service;

import java.util.ArrayList;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;

public interface BoardService {

	int getListCount();

	ArrayList<Board> selectList(PageInfo pi);

	int insertBoard(Board b);

	Board boardDetail(int bId, boolean check);

	int updateBoard(Board b);

	int deleteBoard(int bId);

	int insertReply(Reply r);

	ArrayList<Reply> selectReplyList(int boardId);

	ArrayList<Board> selectTopList();

}
