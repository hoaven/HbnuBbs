package com.kakanshun.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kakanshun.dao.CheckDAO;
import com.kakanshun.dao.UserInfoDAO;
import com.kakanshun.domain.UserInfo;

@Controller
@RequestMapping("/common")
public class UserController {
	private UserInfoDAO udao = new UserInfoDAO();
	private CheckDAO check_dao = new CheckDAO();
	
	@RequestMapping(value="/regist.do")
	public String Regist(HttpServletRequest request,HttpServletResponse response){
		String userName = "";
		String passWord = "";
		String passWord1 = "";
		String sex = "";
		boolean sexType = false;
		String userFace = "";
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		userName = request.getParameter("uName");
		passWord = request.getParameter("uPass");
		passWord1 = request.getParameter("uPass1");// 重复密码
		// 验证用户名和密码长度
		if (check_dao.checkUserNameIsExist(userName)) {   //返回值是boolean
			request.setAttribute("message", "<script>" + "alert('用户名已经存在');"
					 + "</script>");
			return "common/regist.jsp";
		}
		sex = request.getParameter("gender");
		if(sex!=""&&sex!=null) {
			if (sex.equals("1")) {// 女
				sexType = true;
			} else {// 男
				sexType = false;
			}
		}
		userFace = request.getParameter("head");
		
		if (udao.checkReg(userName, passWord, sexType, userFace)) {
			request.setAttribute("message", "<script>" + "alert('恭喜您，注册成功！');"
					 + "</script>");
			return "/";    //返回主页
		}
		return "common/regist.jsp";
		
	}
	
	@RequestMapping(value="/login.do")
	public String Login(HttpServletRequest request,HttpServletResponse response){
		UserInfo user = null;
		String userName = request.getParameter("uName");
		String passWord = request.getParameter("uPass");
		HttpSession session = request.getSession();//用户登录会话开始
		if (udao.checkLogin(userName, passWord)) {
			user = udao.getUserInfo(userName);
			session.setAttribute("users", user);
			request.setAttribute("message", "<script>" + "alert('恭喜您，登录成功！')"
					 + "</script>");
			return "common/index.jsp";
		} else {
			request.setAttribute("message", "<script>" + "alert('用户名或者密码错误')"
					 + "</script>");
			return "common/index.jsp";
		}
	}
	
	@RequestMapping(value="/exit.do")
	public ModelAndView exit(HttpServletRequest request) {
		HttpSession session = request.getSession();     // 获得会话
		session.invalidate();    //关闭会话
		request.setAttribute("message", "<script>" + "alert('恭喜您，注销用户成功！')"
				 + "</script>");
		ModelAndView mav = new ModelAndView("/");    //返回首页
		return mav;
	}
}
