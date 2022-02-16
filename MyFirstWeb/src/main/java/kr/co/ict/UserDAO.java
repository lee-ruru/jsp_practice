package kr.co.ict;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DAO클래스는 DB연동을 전담하여 처리합니다.
public class UserDAO {

	//DB접속에 필요한 변수들을 아래에 선언합니다.
	private String dbType = "com.mysql.cj.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://localhost:3306/jdbcprac1";
	private String dbId = "root";
	private String dbPw = "mysql";

	// 생성자를 이용해 생성할 때 자동으로 Class.forName()을 실행하게 만듭니다.
	// 어떤 구문을 실행하더라도 동통적으로 활용하는 부분
	public UserDAO() {
		try {
			Class.forName(dbType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// user_list2.jsp의 코드 로직을 대체해보겠습니다.
	// user_list2.jsp에서 전체 유저 목록을 필요로 하기 때문에
	// 실행 결과로 <List(UserVO>를 리턴해줘야합니다.
	// 역시 SELECT구문을 실행할때에는 리턴 자료가 필요하고
	// INSERT, DELETE, UPDATE구문을 실행할때는 리턴자료가 void입니다.
	public List<UserVO> getAllUserList(){
		// try 블럭 진입전에 Connection, PreparedStatement, ResultSet 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//try 블럭 진입 전에 ArrayList 선언
		List<UserVO> userList = new ArrayList<>();
		try {
		// Connection, PreparedStatement, ResultSet을 선언합니다.
		con = DriverManager.getConnection(dbUrl, dbId, dbPw);
				
		// SELECT * FROM userinfo  실행 및  ResultSet에 저장
		String sql = "SELECT * FROM userinfo";
		pstmt = con.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		// UserVO ArrayList에 rs에 든 모든 자료를 저장해주세요.
		while(rs.next()){
			 // 1. ResultSet에서 데이터가져오기
			 String uName = rs.getString("uname");
			 String uId = rs.getString("uid");
			 String uPw = rs.getString("upw");
			 String uEmail = rs.getString("uemail");
			 UserVO userData = new UserVO(uName, uId, uPw, uEmail);
			 userList.add(userData);
		 }
			System.out.println(userList);
		
	}catch(Exception e) {
		e.printStackTrace();
	} finally {
		try {
			con.close();
			pstmt.close();
			rs.close();
		}catch(SQLException se) {
			se.printStackTrace();
		}
	
	}
		return userList;
	}


}