package com.estsoft.guestbook.test;

import java.util.List;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.guestbook.dao.GuestbookDao;
import com.estsoft.guestbook.vo.GuestbookVO;

public class GuestbookDaoTest {

	public static void main(String[] args) {
//		insertTest();
		getListTest();
	}

	public static void insertTest(){
		GuestbookVO vo = new GuestbookVO();
		vo.setName( "둘리" );
		vo.setReg_date( "20130908" );
		vo.setMessage( "여기는 메시지" );
		vo.setPassword("1234");
		
		GuestbookDao dao = new GuestbookDao( new MySQLWebDBConnection() );
		dao.insert(vo);
	}	
	
	public static void getListTest(){
		GuestbookDao dao = new GuestbookDao( new MySQLWebDBConnection() );
		List<GuestbookVO> list = dao.getList();
		for( GuestbookVO vo : list ) {
			System.out.println( vo );
		}
	}
	
}
