package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Dtos.MemberDto;


public class DbUtils {

	private static String id = "root";
	private static String pw = "1234";
	private static String url = "jdbc:mysql://localhost:3306/opendatadb";

	//JDBC
	
	private static Connection conn = null; // 
	private static PreparedStatement pstmt = null; //
	private static ResultSet rs = null; // S
	
	//기능
	public static void conn() throws Exception
	{
		//드라이브 클래스 메모리 공간 적재
				//Connection conn 멤버에 Connection 객체 연결
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loading Success");
		conn = DriverManager.getConnection(url,id,pw);
		System.out.println("DB Connected");
		
		
	}
	public static void disConn() throws Exception {
		//rs / pstmt / conn close 처리
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(conn!=null)
			conn.close();
	}	
	public static int insertMember(MemberDto md) throws Exception 
	{
		//tbl_member 에 dto 값 저장 후 int 반환
		pstmt = conn.prepareStatement("INSERT INTO tbl_member values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		pstmt.setString(1,md.getUserid());
		pstmt.setString(2,md.getPassword());
		pstmt.setString(3,md.getRePassword());
		pstmt.setString(4,md.getUsername());
		pstmt.setString(5,md.getZipcode());
		pstmt.setString(6,md.getAddr1());
		pstmt.setString(7,md.getAddr2());
		pstmt.setString(8,md.getPhone1());
		pstmt.setString(9,md.getPhone2());
		pstmt.setString(10,md.getPhone3());
		pstmt.setString(11,md.getTel1());
		pstmt.setString(12,md.getTel2());
		pstmt.setString(13,md.getTel3());
		pstmt.setString(14,md.getEmail());
		pstmt.setString(15,md.getYear());
		pstmt.setString(16,md.getMonth());
		pstmt.setString(17,md.getDay());
		
		
		return pstmt.executeUpdate();
	}
	
	public static MemberDto selectMember(String userid) throws Exception{	
		//tbl_member 에 userid 와 일치하는 데이터 받아와 MemberDto로 반환  
		pstmt = conn.prepareStatement("select * from tbl_member where userid=?");
		pstmt.setString(1, userid);
		rs = pstmt.executeQuery();
		MemberDto dto = null;
		if(rs!=null) {
			if(rs.next()) {
				dto = new MemberDto();
				dto.setUserid(rs.getString("userid"));
				dto.setPassword(rs.getString("Password"));
				dto.setRePassword(rs.getString("repassword"));
				dto.setUsername(rs.getString("username"));
				dto.setZipcode(rs.getString("Zipcode"));
				dto.setAddr1(rs.getString("Addr1"));
				dto.setAddr2(rs.getString("Addr2"));
				dto.setPhone1(rs.getString("Phone1"));
				dto.setPhone2(rs.getString("Phone2"));
				dto.setPhone3(rs.getString("Phone3"));
				dto.setTel1(rs.getString("Tel1"));
				dto.setTel2(rs.getString("Tel2"));
				dto.setTel3(rs.getString("Tel3"));
				dto.setEmail(rs.getString("Email"));
				dto.setYear(rs.getString("Year"));
				dto.setMonth(rs.getString("Month"));
				dto.setDay(rs.getString("Day"));}
		}
		
		return dto;
	}
	
	public static void TxStart() throws Exception{
		if(conn!=null)
			conn.setAutoCommit(false);
	}
	public static void TxEnd() throws Exception {
		if(conn!=null)
			conn.commit();
	}
	public static void RollBack() throws Exception {
		if(conn!=null)
			conn.rollback();	
	}
	
}
