package com.mBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mBoard.entity.Board;
import com.mBoard.mapper.BoardMapper;

@Controller
public class BoardController {
	
	@Autowired // 의존성 주입으로 mapper 클래스 변수 생성
	BoardMapper mapper;
	
	
	// 게시물 목록 보기
	@GetMapping("/board/boardList")
	void getBoardList(Model model) {
		
		model.addAttribute("list", mapper.selectList());	
	}
	
	// 게시물 내용 보기
	@GetMapping("/board/boardView")
	void getBoardView(Model model, @RequestParam("seqno") int seqno) {
		
		model.addAttribute("view", mapper.viewList(seqno));	
	}
	
	// 게시물 등록 보기
	@GetMapping("/board/boardRegister")
	void getBoardRegister() {}
	
	// 게시물 등록
	@PostMapping("/board/boardRegister")
	String postBoardRegister(Board board) {
		mapper.insertList(board.getUserid(), board.getMwriter(), board.getMtitle(), board.getMcontent());
		return "redirect:/board/boardList";
	}
	
	// 게시물 수정 보기
	@GetMapping("/board/boardModify")
	void getBoardModify(Model model, @RequestParam("seqno") int seqno) {
		model.addAttribute("view", mapper.viewList(seqno));
	}
	
	//게시물 수정
	@PostMapping("/board/boardModify")
	String postBoardModify(Board board) {
		
		
		mapper.updateList(board.getMwriter(), board.getMtitle(), 
				board.getMcontent(), board.getSeqno());
		return "redirect:/board/boardView?seqno="+board.getSeqno();
		
	}
	
	
	
	@GetMapping("/board/boardDelete")
	String getBoardDelete(@RequestParam("seqno") int seqno) {
		
		mapper.deleteList(seqno);
		return "redirect:/board/boardList";
		
	}

}
