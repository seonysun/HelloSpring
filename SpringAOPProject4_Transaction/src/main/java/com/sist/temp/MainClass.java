package com.sist.temp;
import com.sist.temp.*;

public class MainClass {
	public static void main(String[] args) {
		StudentDAO dao=new StudentDAO();
        StudentVO vo=new StudentVO();
        vo.setHakbun(3);
        vo.setName("이순신");
        vo.setKor(100);
        vo.setEng(89);
        vo.setMath(90);
        StudentVO vo1=new StudentVO();
        vo1.setHakbun(4);
        vo1.setName("춘향이");
        vo1.setKor(90);
        vo1.setEng(90);
        vo1.setMath(90);
        
        dao.studentInsert(vo, vo1);
	}
}
