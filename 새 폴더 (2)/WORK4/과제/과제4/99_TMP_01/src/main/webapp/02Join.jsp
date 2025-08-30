<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 문제 : 선언문(적절한 클래스 import 하기) -->
<%@ page import="Dtos.MemberDto,Utils.*,java.util.regex.Pattern.*" %>

<%
	/* 문제 : 문자셋 설정 */
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
%>
	
<%!
	/* 문제 : 유효성 검증함수 만들기 */
	public boolean isValid(MemberDto dto){
		
		if(dto.getUserid().isEmpty()){
			System.out.println("Userid를 입력하세요. : ");
			return false;}
		
		if(dto.getUserid().isEmpty()){
			System.out.println("Userid는 5자 이상 입력하셔야 합니다.");
			return false;}
		//3) 패스워드 유효성 검증(regex : ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,20}$ )
		//- System.out 으로 출력후 false
		
		String expassword = dto.getPassword();
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,20}$";
		if(!expassword.matches(regex)){
			System.out.println("비밀번호는 대/소문자,숫자+특수문자 포함 8~20자 사이로 가능합니다.");
			return false;
		}
		return true;}	
%>    



<!-- 01 문제 : 파라미터 받기 (액션태그 jsp:useBean , jsp:setProperty 로 MemberDto 단위로 받기 -->



<jsp:useBean id="dto" class="Dtos.MemberDto" scope="request"/>
<jsp:setProperty name="dto" property="*" />


<%
	
	try{
		//-----------------------------
		//02 유효성 검증	
		//-----------------------------
		if(!isValid(dto)){
			//유효하지 않는경우에 -> 01Join.html 로 Forwarding
			request.getRequestDispatcher("./01Join.html").forward(request,response);
		}
		//-----------------------------
		//03 서비스 처리(회원가입->DB 저장)
		//-----------------------------
		//03-01 db연결
		DbUtils.conn();
		//03-02 Tx 시작
		DbUtils.TxStart();
		//03-03 동일 계정유무확인
		if(DbUtils.selectMember(dto.getUserid())!=null){
			System.out.println("기존 계정이 존재합니다.");
			request.getRequestDispatcher("./Join.html").forward(request,response);
			return;
		}
		//03-04 계정정보 저장	
		 int result = DbUtils.insertMember(dto);
		if(result<=0){
			System.out.println("회원가입에 실패했습니다.");
			request.getRequestDispatcher("./01Join.html").forward(request,response);
			return;
		}
		
		
		//03-05 Tx 종료
		DbUtils.TxEnd();
		//03-06 연결해제
		DbUtils.disConn();
		
		//-----------------------------
		//04 로그인 페이지로 이동
		//-----------------------------
		//04-01 "회원가입을 완료했습니다" 를 system.out 으로 출력
		//04-02 Login.jsp 로 리다이렉트
		response.sendRedirect("./03Login.jsp");
		System.out.println("회원가입을 완료했습니다.");
	}
	catch(Exception e){
		e.printStackTrace();
		System.out.println("문제 발생 ROLLBACK");
		DbUtils.RollBack();
		//"문제 발생 ROLLBACK" system.out 출력
		//TX ROLLBACK 처리
	}
		
%>