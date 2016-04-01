package com.estsoft.guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.db.DBConnection;
import com.estsoft.guestbook.vo.GuestbookVO;

public class GuestbookDao {
	private DBConnection dbConnection;

	public GuestbookDao( DBConnection dbConnection ) {
		this.dbConnection = dbConnection;
	}
	
	public void insert( GuestbookVO vo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConnection.getConnection();
			String sql = "INSERT INTO guestbook VALUES( null, ?, now(), ?, password(?) )";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1,  vo.getName() );
			pstmt.setString( 2, vo.getMessage() );
			pstmt.setString( 3, vo.getPassword() );
			pstmt.executeUpdate();
		} catch( SQLException ex ) {
			System.out.println( "error:" + ex );
		} finally {
			try{
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			}catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
	}
	
	public void delete( GuestbookVO vo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConnection.getConnection();
			String sql = "DELETE FROM guestbook WHERE no = ? AND passwd = password(?)";
			pstmt = conn.prepareStatement( sql );
			pstmt.setLong( 1,  vo.getNo() );
			pstmt.setString( 2, vo.getPassword() );
			pstmt.executeUpdate();
		} catch( SQLException ex ) {
			System.out.println( "error:" + ex );
		} finally {
			try{
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			}catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
	}
	
	public List<GuestbookVO> getList() {
		List<GuestbookVO> list = new ArrayList<GuestbookVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT no, name, DATE_FORMAT( reg_date, '%Y-%m-%d %p %h:%i:%s' ), message from guestbook ORDER BY reg_date desc";
			rs = stmt.executeQuery( sql );
			while( rs.next() ) {
				Long no = rs.getLong( 1 );
				String name = rs.getString( 2 );
				String regDate = rs.getString( 3 );
				String message = rs.getString( 4 );
				GuestbookVO vo = new GuestbookVO();
				vo.setNo( no );
				vo.setName( name );
				vo.setReg_date( regDate );
				vo.setMessage( message );
				list.add( vo );
			}
		} catch( SQLException ex ) {
			System.out.println( "error: " + ex);
		} finally {
			try{
				if( rs != null ) {
					rs.close();
				}
				if( stmt != null ) {
					stmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			}catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
			
		return list;
	}

}

