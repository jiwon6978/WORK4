package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DbUtils {
	//�Ӽ�(DB ������õ� ���)
	//DB CONN DATA
	private static String id = "root";
	private static String pw = "1234";
	private static String url = "jdbc:mysql://localhost:3306/opendatadb";

	//JDBC��������
	private static Connection conn = null; // DBMS �� Ư�� DB�� ����Ǵ� ��ü
	private static PreparedStatement pstmt = null; // SQL Query ���ۿ� ��ü
	private static ResultSet rs = null; // Select ����� ���� ��ü
	
	//���
	public static void conn() throws Exception
	{
		//����̺� Ŭ���� �޸� ���� ����
		//Connection conn ����� Connection ��ü ����
	}
	public static void disConn() throws Exception {
		//rs / pstmt / conn close ó��
	}	
	public static int insertMember(MemberDto memberDto) throws Exception 
	{
		//tbl_member �� dto �� ���� �� int ��ȯ
		return -1;
	}
	public static MemberDto selectMember(String userid) throws Exception{	
		//tbl_member �� userid �� ��ġ�ϴ� ������ �޾ƿ� MemberDto�� ��ȯ  
		return null;
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
