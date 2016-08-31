<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk" import="com.kakanshun.domain.*"%>
<%
	UserInfo temp = (UserInfo) session.getAttribute("users");
	if (temp == null) {
		response.sendRedirect("err.htm");
		return;
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<TITLE>管理员控制台登录</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gbk">
<LINK href="${pageContext.request.contextPath}/manager/manager.css" type=text/css rel=stylesheet>

<style type="text/css">
<!--
body {
	margin-top: 250px;
}
-->
</style>

<script language="javascript">
function check() {
 if(document.loginForm.uName.value==""){
    alert("用户名不能为空");
    return false;
 }
 if(document.loginForm.uPass.value==""){
    alert("密码不能为空");
    return false;
 }
}
</script>
</HEAD>
<BODY style="BACKGROUND: #f4f6f7">

	<center>
		<div>
			<h2>管理员控制台登录</h2>

			<form action="login.do" onsubmit="return check()"
				name="loginForm" method="post">
				<input type="hidden" name="action" value="login" />
				<table width="26%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td><img src="${pageContext.request.contextPath}/manager/image/login-top.gif" width="437" height="15">
						</td>
					</tr>
					<tr>
						<td align="center" valign="middle"
							background="image/login-center.gif"><table width="65%"
								border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="37"><label style="FONT-SIZE: 18px;font-family:华文行楷">用户名:</label></td>
									<td><input class=nofocus id=uName
										onBlur="this.className='nofocus';" style="WIDTH: 200px"
										onFocus="this.className='nofocus';" readonly size=25
										value=<%=temp.getUname()%> name=uName>
									</td>
								</tr>
								<tr>
									<td height="37"><label style="FONT-SIZE: 18px;font-family:华文行楷">密 码:</label></td>
									<td><input class=FormBase id=PassWord
										onBlur="this.className='FormBase';" style="WIDTH: 200px"
										onFocus="this.className='FormFocus';" type=password name=uPass>
									</td>
								</tr>
								<tr align="center" valign="middle">
									<td height="53" colspan="2"><input name="submit"
										type=submit id=submit9
										style="BORDER-RIGHT: 0px; BORDER-TOP: 0px; BACKGROUND: url(${pageContext.request.contextPath}/manager/image/button.gif) no-repeat left top; BORDER-LEFT: 0px; WIDTH: 60px; CURSOR: pointer; BORDER-BOTTOM: 0px; HEIGHT: 26px"
										value=""></td>
								</tr>
							</table></td>
					</tr>
					<tr background="${pageContext.request.contextPath}/manager/image/login-center.gif">
						<td></td>
					</tr>
					<tr>
						<td><img src="${pageContext.request.contextPath}/manager/image/login-bottom.gif" width="438" height="17">
						</td>
					</tr>
				</table>
			</form>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</center>

	<DIV style="FONT-SIZE: 11px; PADDING-TOP: 60px; FONT-FAMILY: Arial"
		align=center>
		<HR style="WIDTH: 600px; COLOR: #cccccc; HEIGHT: 1px">
		版权信息
	</DIV>
</BODY>
</HTML>
