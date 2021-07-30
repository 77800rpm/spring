package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.board.model.exception.BoardException;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.common.Pagination;

@Controller
public class BoardController {

	@Autowired
	private BoardService bService;
	
	@RequestMapping("blist.bo")
	public ModelAndView boardList(@RequestParam(value="page", required=false) Integer page, ModelAndView mv) {
		
		int currentPage = 1;
		
		if(page != null) { // 페이지가 존재한다면
			currentPage = page;
		}
		
		int listCount = bService.getListCount();
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		
		ArrayList<Board> list = bService.selectList(pi);
		
//		System.out.println(list);
		if(list != null) {
			mv.addObject("list", list).addObject("pi", pi);
			// mv.addObject("list", list);
			// mv.addObject("pi", pi);
			mv.setViewName("boardListView");
		} else {
			throw new BoardException("게시글 전체 조회에 실패했습니다.");
		}
		
		return mv;
	}
	
	@RequestMapping("binsertView.bo")
	public String boardInsertForm() {
		return "boardInsertForm";
	}
	
	@RequestMapping("binsert.bo")
	public String insertBoard(@ModelAttribute Board b, @RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request) {
//		System.out.println(b);
//		System.out.println(uploadFile);
//		System.out.println(uploadFile.getOriginalFilename()); // 파일 이름
		// 파일 안 넣었을 때 ==> ""
		// 파일 넣었을 때 ==> 파일에 대한 이름
		
//		if(uploadFile.getOriginalFilename().equals("")) {
		if(uploadFile != null && !uploadFile.isEmpty()) {
			String renameFileName = saveFile(uploadFile, request);
			
			if(renameFileName != null) {
				b.setOriginalFileName(uploadFile.getOriginalFilename());
				b.setRenameFileName(renameFileName);
			}
						
		}
		
		int result = bService.insertBoard(b);
		
		if(result > 0) {
			return "redirect:blist.bo";
		} else {
			throw new BoardException("게시글 등록에 실패했습니다.");
		}
			
//		return "redirect:blist.bo";
	}
	
	public String saveFile(MultipartFile file, HttpServletRequest request) {
		// 작은 resources까지 접근해야 하기 때문에 HttpServletRequest 사용함
		String root = request.getSession().getServletContext().getRealPath("resources");
//		System.out.println(root);
//		String savePath = root + "\\buploadFiles"; // \n : 줄바꿈 \t : 탭   \\ : \
		String savePath = root + "/buploadFiles"; // \n : 줄바꿈 \t : 탭   \\ : \
		
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String originFileName = file.getOriginalFilename();
		// lastIndexOf로 원본 확장자 가지고 오기 (. 한 칸 뒤에 있는 문자열 가지고 오기)
		String renameFileName = sdf.format(new Date(System.currentTimeMillis())) + "." 
								+ originFileName.substring(originFileName.lastIndexOf(".") + 1);
		
		String renamePath = folder + "\\" + renameFileName;
		
		
		try {
			file.transferTo(new File(renamePath));
		} catch (Exception e) {
			System.out.println("파일 전송 에러");
			e.printStackTrace();
		}	
		
		return renameFileName;
	}
	
	
	
	
	@RequestMapping("bdetail.bo")
	public ModelAndView boardDetail(@RequestParam("bId") int bId, @RequestParam("page") int page, ModelAndView mv) {
//		System.out.println(bId);
		Board board = bService.boardDetail(bId, true);
//		System.out.println(board);
		
		if(board != null) {
			mv.addObject("board", board);
			mv.addObject("page", page);
			mv.setViewName("boardDetailView");
		} else {
			throw new BoardException("게시글 상세 조회에 실패하였습니다.");
		}
		
		return mv;
	}
	
	@RequestMapping("bupView.bo")
	public String boardUpdateForm(@RequestParam("bId") int bId, @RequestParam("page") int page, Model model) {
		Board board = bService.boardDetail(bId, false); // 이렇게 받아오면 게시글을 수정할 때마다 조회수가 1씩 오른다
		model.addAttribute("board", board).addAttribute("page", page);
		return "boardUpdateForm";
	}
	
	@RequestMapping("bupdate.bo")
	public ModelAndView updateBoard(@ModelAttribute Board b, @RequestParam("page") int page, @RequestParam("reloadFile") MultipartFile reloadFile, HttpServletRequest request, ModelAndView mv) {
		
		
		if(reloadFile != null && !reloadFile.isEmpty()) { // 바꿀 파일이 존재한다면
			if(b.getRenameFileName() != null) { // 이전 파일 존재한다면
				deleteFile(request, b);
			}
			
			String renameFileName = saveFile(reloadFile, request);
			if(renameFileName != null) {
				b.setOriginalFileName(reloadFile.getOriginalFilename());
				b.setRenameFileName(renameFileName);
			}
		}
		
		
//		System.out.println(b);
		// 글 업데이트 o
		// 		파일 업데이트 o
		//			원래 파일 o
		//			원래 파일 x
		//		파일 업데이트 x
		//			원래 파일 o
		//			원래 파일 x
		// 글 업데이트 x
		// 		파일 업데이트 o
		//			원래 파일 o
		//			원래 파일 x
		//		파일 업데이트 x
		//			원래 파일 o
		//			원래 파일 x

		
		int result = bService.updateBoard(b);
		if(result > 0) {
			Board board = bService.boardDetail(b.getBoardId(), false);
			mv.addObject("board", board);
			mv.addObject("page", page);
			mv.setViewName("boardDetailView");
		} else {
			throw new BoardException("게시글 수정에 실패했습니다.");
		}
		
		
		return mv;
	}
	
	public void deleteFile(HttpServletRequest request, Board b) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\buploadFiles";
		
		File f = new File(savePath + "\\" + b.getRenameFileName());
		if(f.exists()) {
			f.delete();
		}
	}
	
	
	@RequestMapping("bdelete.bo")
	public String deleteBoard(@RequestParam("bId") int bId, HttpServletRequest request) {

		Board board = bService.boardDetail(bId, false);
		
		if(board.getOriginalFileName() != null) {
			deleteFile(request, board);
		}
		
		int result = bService.deleteBoard(bId);	
		
		if(result > 0) {
			return "redirect:blist.bo";
		} else {
			throw new BoardException("게시글 삭제에 실패했습니다.");
		}
		
	}
	
}
