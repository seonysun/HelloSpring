package com.sist.web;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DataBoardController {
	@Autowired
	private DataBoardDAO dao;
	
	//목록
	@GetMapping("databoard/list.do")
	public String databoard_list(String page, Model model) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=dao.databoardTotalPage();
		int rowsize=10;
		int start=rowsize*(curpage-1)+1;
		int end=rowsize*curpage;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<DataBoardVO> list=dao.databoardListData(map);
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		return "databoard/list";
	}
	
	//추가
	@GetMapping("databoard/insert.do")
	public String databoard_insert() {
					//전송할 변수나 받아올 변수가 없으므로 매개변수 없음
		return "databoard/insert";
	}
	
	@PostMapping("databoard/insert_ok.do") //GET, POST 오류 시 405 에러
	public String databoard_insert_ok(DataBoardVO vo) {
		List<MultipartFile> list=vo.getFiles();
		if(list==null) {
			vo.setFilecount(0);
			vo.setFilename("");
			vo.setFilesize("");
		} else {
			String fn=""; //DB에 저장될 파일명 모음
			String fs=""; //DB에 저장될 파일크기 모음
			for(MultipartFile mf:list) {
				String of=mf.getOriginalFilename(); //사용자가 업로드한 파일명
				fn+=of+",";
				fs+=mf.getSize()+","; //사용자가 업로드한 파일 크기
				File file=new File("c:\\download\\"+of); //저장될 파일과 파일명 지정
				try {
					mf.transferTo(file);
					//MultipartFile : Spring 제공 파일 관리 인터페이스 -> 파일 이름, 데이터, 크기
					//File : java.io, 입출력에 필요한 파일이나 디렉터리 제어 -> 접근 권한, 생성 시간, 경로
					/* 참고 https://antstudy.tistory.com/308 */
				} catch(Exception ex) {}
			}
			vo.setFilecount(list.size());
			vo.setFilename(fn.substring(0, fn.lastIndexOf(",")));
			vo.setFilesize(fs.substring(0, fs.lastIndexOf(",")));
		}
		dao.databoardInsert(vo);
		return "redirect:list.do"; //request 초기화 후 화면 이동
	}
	
	//상세
	@GetMapping("databoard/detail.do")
	public String databoard_detail(int no, Model model) {
		DataBoardVO vo=dao.databoardDetailData(no);
		if(vo.getFilecount()>0) {
			String[] fn=vo.getFilename().split(",");
			String[] fs=vo.getFilesize().split(",");
			List<String> nList=Arrays.asList(fn);
								//이미 만들어져있는 배열의 인스턴스 형태로 ArrayList 반환 -> 배열 크기 고정(추가/삭제 불가)
			List<String> sList=Arrays.asList(fs);
			model.addAttribute("nList", nList);
			model.addAttribute("sList", sList);
		}
		model.addAttribute("vo", vo);
		return "databoard/detail";
	}
	
	@GetMapping("databoard/download.do")
	public void databoard_download(String fn, HttpServletResponse response) {
												//다운로드는 내장객체가 아니므로 response 필요
		try {
			File file=new File("c:\\download\\"+fn);
			
			//다운로드 팝업창 띄우기 
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fn, "UTF-8"));
			response.setContentLength((int)file.length());
		    
			//실제 다운로드 실행
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file)); //서버에 존재하는 파일 위치 
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream()); //파일이 다운로드 되는 위치 
			int i=0;
			byte[] buffer=new byte[1024];
			while((i=bis.read(buffer, 0, 1024))!=-1) {
				bos.write(buffer, 0, i);
			}
	        bis.close();
	        bos.close();
		} catch(Exception ex) {}
	}
	
	//수정
	@GetMapping("databoard/update.do")
	public String databoard_update(int no, Model model) {
		DataBoardVO vo=dao.databoardUpdateData(no);
		model.addAttribute("vo", vo);
		return "databoard/update";
	}

	@PostMapping("databoard/update_ok.do")
	public String databoard_update_ok(DataBoardVO vo, RedirectAttributes ra) {
		dao.databoardUpdate(vo);
		ra.addAttribute("no", vo.getNo());
		return "redirect:detail.do";
	}
}
