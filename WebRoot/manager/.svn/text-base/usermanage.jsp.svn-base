<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
<title>用户管理</title>
</head>
<body>
${message}
    <table id="tableColor"  cellpadding="10" cellspacing="0" border=0px align="center">
    	<thead>
    		<th>序号</th>
        	<th>用户名</th>
            <th>用户身份</th>
            <th>操作</th>
        </thead>
        <c:forEach items="${userlist}" var="c" varStatus="vs">
    	<tr>
    		<td>${vs.index}</td>
            <td>${c.uname}</td>
            <td>
            <c:if test="${c.utype==0}">普通用户</c:if>
            <c:if test="${c.utype==1}">版主</c:if>
            <c:if test="${c.utype==2}">管理员</c:if>
            <c:if test="${c.utype==-1}">禁用用户</c:if>
            </td>
            <td>
	            <a href="updateuser_${c.uid}.do">禁用</a> 
	            <a href="deleteuser_${c.uid}.do">删除</a>
	        </td>
        </tr>
        </c:forEach>
    </table>
  </body>
</html>