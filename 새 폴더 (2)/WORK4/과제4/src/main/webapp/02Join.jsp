<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 문제 : 선언문(적절한 클래스 import 하기) -->
<%@ page import="" %>

<%
	/* 문제 : 문자셋 설정 */
%>

<%!
	/* 문제 : 유효성 검증함수 만들기 */
	public boolean isValid(MemberDto dto){
		//1) 각 항목 not null (message : '-' 를 입력하세요 - System.out 으로 출력후 false)
		//2) userid 길이 5자 이하(message : Userid는 5자 이상 입력하셔야 합니다.- System.out 으로 출력후 false)
		//3) 패스워드 유효성 검증(regex : ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,20}$ )
		//- System.out 으로 출력후 false
		
		return true;
	}	
%>    


<!--  
01 문제 : 파라미터 받기 (액션태그 jsp:useBean , jsp:setProperty 로 MemberDto 단위로 받기

<jsp:useBean />
<jsp:setProperty  />

-->

<%
	
	try{
		//-----------------------------
		//02 유효성 검증	
		//-----------------------------
		if(!isValid(dto)){
			//유효하지 않는경우에 -> 01Join.html 로 Forwarding
		}
		//-----------------------------
		//03 서비스 처리(회원가입->DB 저장)
		//-----------------------------
		//03-01 db연결
		//03-02 Tx 시작
		//03-03 동일 계정유무확인
		//03-04 계정정보 저장			
		//03-05 Tx 종료
		//03-06 연결해제
		
		//-----------------------------
		//04 로그인 페이지로 이동
		//-----------------------------
		//04-01 "회원가입을 완료했습니다" 를 system.out 으로 출력
		//04-02 Login.jsp 로 리다이렉트
		
	}
	catch(Exception e){
		//"문제 발생 ROLLBACK" system.out 출력
		//TX ROLLBACK 처리
	}
		
%>