package com.sist.dao;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DataBoardVO {
	private int no,hit,filecount;
	private String name,subject,content,pwd,filename,filesize,dbday;
	private Date regdate;
	private List<MultipartFile> files; //업로드된 파일 저장
							//jsp 파일에서 데이터 받을 때 name값 동일하게 설정, List이므로 배열 형태로
}
