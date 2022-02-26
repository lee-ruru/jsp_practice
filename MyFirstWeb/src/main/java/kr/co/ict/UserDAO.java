package kr.co.ict;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// DAO 클래스는 DB연동을 전담하여 처리합니다.
public class UserDAO {

	// DB접속에 필요한 변수들을 아래에 선언합니다.
	// 커넥션풀 처리로 인한 미사용
	//private String dbType = "com.mysql.cj.jdbc.Driver";
	//private String dbUrl = "jdbc:mysql://localhost:3306/jdbcprac1";
	//private String dbId = "root";
	//private String dbPw = "1111";
	private DataSource ds = null;
	
	// 생성자를 이용해 생성할 때 자동으로 Class.forName()을 실행하게 만듭니다.
	// 어떤 쿼리문을 실행하더라도 공통적으로 활용하는 부분
	//public UserDAO() {
	//	try {
	//		Class.forName(dbType);
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//	}
	//}
	
	// 싱글턴 패턴 처리.
	// 3. DAO 내부에 멤버변수로 UserDAO를 하나 생성해줍니다.
	private static UserDAO dao = new UserDAO();
	// 싱글턴은 요청시마다 DAO를 매번 새로 생성하지 않고, 먼저 하나를 생성해둔 다음
	// 사용자 요청때는 이미 생성된 DAO의 주소값만 공유해서 
	// DAO생성에 필요한 시간을 절약하기 위해 사용합니다.
	// 1. 생성자는 private으로 처리해 외부에서 생성명령을 내릴 수 없게 처리합니다.
	private UserDAO() {
		try {
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 2. static 키워드를 이용해서 단 한번만 생성하고, 그 이후로는
	// 주소를 공유하는 getInstance() 메서드를 생성합니다.
	public static UserDAO getInstance() {
		if(dao == null) {
			dao = new UserDAO();
		}
		return dao;
	}
	
	
	
	
	
	// user_list2.jsp의 코드 로직을 대체해보겠습니다.
	// user_list2.jsp에서 전체 유저 목록을 필요로 하기 때문에
	// 실행 결과로 List<UserVO>를 리턴해줘야 합니다.
	// 역시 SELECT구문을 실행할때에는 리턴자료가 필요하고
	// INSERT, DELETE, UPDATE구문을 실행할때는 리턴자료가 void입니다.
	public List<UserVO> getAllUserList(){
		// try블럭 진입 전 Connection, PreparedStatement, ResultSet 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// try블럭 진입 전에 ArrayList 선언
		List<UserVO> userList = new ArrayList<>();
		try {
			// Connection, PreparedStatement, ResultSet을 선언합니다.
			con = ds.getConnection();
			
			// SELECT * FROM userinfo 실행 및 ResultSet에 저장
			String sql = "SELECT * FROM userinfo";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();

			// UserVO ArrayList에 rs에 든 모든 자료를 저장해주세요.
			while(rs.next()) {
				String uName = rs.getString("uname");
				String uId = rs.getString("uid");
				String uPw = rs.getString("upw");
				String uEmail = rs.getString("uemail");
				UserVO userData = new UserVO(uName, uId, uPw, uEmail);
				userList.add(userData);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
				rs.close();
			} catch(SQLException se) {
				se.printStackTrace();
			}
		}	
		return userList;
	}

	// login_update.jsp의 경우 로그인한 유저 한 명의 데이터만 DB에서 얻어옵니다.
	// 따라서, 그 한 명의 유저 데이터만을 이용해 SELECT구문을 써야합니다.
	// login_update.jsp 상단의 sId 변수에 들어있는 유저명을 이용해 유저데이터를 얻어옵니다.
	public UserVO getUserData(String sId) {
		// 접속로직은 getAllUserList()와 큰 차이가 없고 쿼리문만 좀 다릅니다.
		
		// 1. Connection, PreparedStatement, ResultSet 변수 선언만 해주세요.
		// UserVO 변수 선언
		// try블럭 외부에서 써야하는(Connection, PreparedStatment, ResultSet은 finally블럭에서도 사용)
		// (UserVO는 return구문에서 사용)것들은 try진입 전에 먼저 선언합니다.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVO user = null;
		// 2. try블럭 내부에서 DB연결을 해주세요. 필요한 URL, ID, PW는 상단에 멤버변수로 이미 존재합니다.
		try {
			con = ds.getConnection();
			// 3. 쿼리문을 날려서 rs에 DB에서 가져온 정보를 받아주세요.
			// 쿼리문 : SELECT * FROM userinfo WHERE uid=?
			String sql = "SELECT * FROM userinfo WHERE uid=?";
			pstmt = con.prepareStatement(sql);// 쿼리문 세팅
			pstmt.setString(1, sId);// ?부분 채우기
			
			rs = pstmt.executeQuery(); // DB에 쿼리문 날리고 자료 받아 rs에 저장하기.
			// 4. rs에 저장된 데이터를 UserVO에 담습니다.
			if(rs.next()) {
				String uName = rs.getString("uname");
				String uId = rs.getString("uid");
				String uPw = rs.getString("upw");
				String uEmail = rs.getString("uemail");
				user = new UserVO(uName, uId, uPw, uEmail);
			}
		
		// 5. catch, finally 블럭을 작성해주시고 finally에서 자원회수까지 마쳐주세요.
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// .close()는 무조건 try블럭에 있어야 하기 때문에 finally 내부에서 추가로 try블럭 설정
				con.close(); 
				pstmt.close();
				rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return user;// DB에서 UserVO에 데이터를 받아주신 다음 null대신 받아온 데이터를 리턴하세요.
	}
	
	// updateCheck에 필요한 userUpdate메서드를 아래에 정의해주세요.
	// UPDATE구문을 실행하기 때문에 리턴 자료가 필요없고
	// update_check.jsp에 있는 쿼리문을 실행하기 위해
	// id, pw, name, email정보를 모두 받아옵니다.
	public void updateCheck(String uId, String uPw, String uName, String uEmail) {
		Connection con = null;
		PreparedStatement pstmt = null;
		// ResultSet은 SELECT구문에만 필요함.
		
		try {
			con = ds.getConnection();
			
			String sql =  "UPDATE userinfo SET upw=?, uname=?, uemail=? WHERE uid=?";
			pstmt = con.prepareStatement(sql);// 쿼리문 세팅
			pstmt.setString(1, uPw);
			pstmt.setString(2, uName);
			pstmt.setString(3, uEmail);
			pstmt.setString(4, uId);
						
			pstmt.executeUpdate();//executeQuery(), executeUpdate();
		
		// 5. catch, finally 블럭을 작성해주시고 finally에서 자원회수까지 마쳐주세요.
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// .close()는 무조건 try블럭에 있어야 하기 때문에 finally 내부에서 추가로 try블럭 설정
				con.close(); 
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// member_out.jsp에서 사용할 탈퇴기능을 DAO로 이전시키겠습니다.
	// 메서드명은 deleteUser(String sId) 입니다.
	// DAO파일에 생성하신 후, member_out.jsp에서도 해당 메서드를 쓰도록 고쳐주세요.
	// 1. DAO에 메서드 생성후 바로 코드를 저한테 보내주시고
	// 2. 고친 로직을 실행하는 member_out.jsp의 스크립트릿도 추가로 보내주세요.
	public void deleteUser(String sId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		// ResultSet은 SELECT구문에만 필요함.
		
		try {
			con = ds.getConnection();
			
			String sql =  "DELETE FROM userinfo WHERE uid=?";
			pstmt = con.prepareStatement(sql);// 쿼리문 세팅
			pstmt.setString(1, sId);

			pstmt.executeUpdate();//executeQuery(), executeUpdate();
		
		// 5. catch, finally 블럭을 작성해주시고 finally에서 자원회수까지 마쳐주세요.
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// .close()는 무조건 try블럭에 있어야 하기 때문에 finally 내부에서 추가로 try블럭 설정
				con.close(); 
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// 회원가입 로직 insertUser(String uName, String uId, String uPw, String uEmail)를 처리해주세요.
	public void insertUser(String uName, String uId, String uPw, String uEmail) {
		Connection con = null;
		PreparedStatement pstmt = null;
		// ResultSet은 SELECT구문에만 필요함.
		
		try {
			con = ds.getConnection();
			
			String sql =  "INSERT INTO userinfo VALUES(?,?,?,?)";
			pstmt = con.prepareStatement(sql);// 쿼리문 세팅
			pstmt.setString(1, uName);
			pstmt.setString(2, uId);
			pstmt.setString(3, uPw);
			pstmt.setString(4, uEmail);

			pstmt.executeUpdate();//executeQuery(), executeUpdate();
		
		// 5. catch, finally 블럭을 작성해주시고 finally에서 자원회수까지 마쳐주세요.
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// .close()는 무조건 try블럭에 있어야 하기 때문에 finally 내부에서 추가로 try블럭 설정
				con.close(); 
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}









