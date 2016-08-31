<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%@ page import="com.kakanshun.domain.*"%>

<%
	UserInfo checkLogin = (UserInfo)session.getAttribute("users");
	if(checkLogin == null){
		response.sendRedirect("login.jsp");
	}
 %>
