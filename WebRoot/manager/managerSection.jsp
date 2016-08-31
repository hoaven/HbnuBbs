<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
<title>板块管理</title>
</head>
<body>
${message}
    <table id="tableColor"  cellpadding="10" cellspacing="0" border=0px align="center">
    	<thead>
    		<th>序号</th>
        	<th>板块名称</th>
            <th>版主</th>
            <th>操作</th>
        </thead>
        <c:forEach items="${sectionlist}" var="c" varStatus="vs">
    	<tr>
    		<td>${vs.index}</td>
            <td>${c.sName}</td>
            <td>${c.mastername}</td>
            <td>
	            <a href="updatesection_${c.id}.do">修改</a> 
	            <a href="deletesection_${c.id}.do">删除</a>
	            <a href="addsection.do">增加</a>
	        </td>
        </tr>
        </c:forEach>
    </table>
  </body>
</html>
