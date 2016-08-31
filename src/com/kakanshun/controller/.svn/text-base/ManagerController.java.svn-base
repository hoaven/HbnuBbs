package com.kakanshun.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kakanshun.dao.CheckDAO;
import com.kakanshun.dao.SectionInfoDAO;
import com.kakanshun.dao.UserInfoDAO;
import com.kakanshun.domain.SectionInfo;
import com.kakanshun.domain.UserInfo;
import com.kakanshun.page.SectionManger;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	private CheckDAO check_dao = new CheckDAO();
	private UserInfoDAO udao = new UserInfoDAO();
	private SectionInfoDAO section_dao = new SectionInfoDAO();

	@RequestMapping(value = "/login.do")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(); // 返回首页
		UserInfo mUser = null;
		String userName = request.getParameter("uName");
		String passWord = request.getParameter("uPass");

		if (udao.checkLogin(userName, passWord)) {
			mUser = udao.getUserInfo(userName);
			HttpSession session = request.getSession();// 用户登录会话开始
			session.setAttribute("mUsers", mUser);
			mav.setViewName("manager/index.jsp");
		} else {
			request.setAttribute("message", "<script>" + "alert('密码错误');"
					+ "</script>");
			mav.setViewName("manager/login.jsp");
		}
		return mav;
	}

	@RequestMapping(value = "/addsection.do")
	public ModelAndView addSection(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<SectionInfo> list = section_dao.getAllSections();
		request.setAttribute("sectionlist", list);
		mav.setViewName("manager/addSection.jsp");
		return mav;
	}

	@RequestMapping(value = "/updatesection_{id}.do")
	public ModelAndView updateSection(@PathVariable("id") String id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Integer sid = Integer.parseInt(id.trim());
		SectionInfo section = section_dao.getPSectionById(sid);
		UserInfo user = udao.getUserInfoByID(section.getSmasterid());

		List<String> list = new ArrayList<String>();
		String sname = "";
		String master = "";
		if (section != null && section != null) {
			sname = section.getSname();
			master = user.getUname();
		}
		list.add(sname);
		list.add(master);
		list.add(id);

		request.setAttribute("updatesectionlist", list);

		mav.setViewName("manager/editSection.jsp");
		return mav;
	}

	@RequestMapping(value = "/realupdatesection_{id}.do")
	public ModelAndView realupdateSection(@PathVariable("id") String id,
			HttpServletRequest request) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView();
		Integer sid = Integer.parseInt(id.trim());
		SectionInfo section = section_dao.getPSectionById(sid);
		String sName = "";
		if (request.getParameter("sName") != null
				&& request.getParameter("sName") != "") {
			sName = new String(request.getParameter("sName").getBytes(
					"ISO-8859-1"), "UTF-8");// 获得版块名称
		}
		String uName = "";
		if (request.getParameter("uName") != null
				&& request.getParameter("uName") != "") {
			uName = new String(request.getParameter("uName").getBytes(
					"ISO-8859-1"), "UTF-8");// 获得版主名称
		}
		UserInfo user = new UserInfo();
		user = udao.getUserInfo(uName);

		int masterid = 0;
		if (user != null) {
			masterid = user.getUid();
		}

		section.setSname(sName);
		section.setSmasterid(masterid);
		section_dao.updateSection(section);
		mav.setViewName("manager/sectionmanager.do");
		return mav;
	}

	@RequestMapping(value = "/deletesection_{id}.do")
	public ModelAndView udeleteSection(@PathVariable("id") String id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Integer sid = Integer.parseInt(id.trim());
		if(section_dao.deleteSection(sid)) {
			mav.setViewName("manager/sectionmanager.do");
			request.setAttribute("message", "<script type='text/javascript'>alert('删除板块成功');</script>");
		}
		return mav;
	}
	
	@RequestMapping(value = "/realaddsection.do")
	public ModelAndView realaddSection(HttpServletRequest request)
			throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView();
		SectionInfo section = new SectionInfo();
		Integer sParentId = Integer.parseInt(request.getParameter("select"));// 获得父版块编号
		Integer sType = Integer.parseInt(request.getParameter("sType"));// 获得版块类型
		String sName = "";
		if (request.getParameter("sName") != null
				&& request.getParameter("sName") != "") {
			sName = new String(request.getParameter("sName").getBytes(
					"ISO-8859-1"), "UTF-8");// 获得版块名称
		}
		String uName = "";
		if (request.getParameter("uName") != null
				&& request.getParameter("uName") != "") {
			uName = new String(request.getParameter("uName").getBytes(
					"ISO-8859-1"), "UTF-8");// 获得版主名称
		}
		UserInfo user = new UserInfo();
		user = udao.getUserInfo(uName);
		if (user == null) {
			request.setAttribute("message",
					"<script type='text/javascript'>alert('对不起，版主用户不存在！');</script>");
			mav.setViewName("manager/managerSection.jsp");
			return mav;
		}

		int masterid = 0;
		if (user != null) {
			masterid = user.getUid();
		}

		if (section_dao.getPSectionBySname(sName) != null) {
			request.setAttribute("message",
					"<script type='text/javascript'>alert('对不起，该板块已经存在！');</script>");
			mav.setViewName("manager/managerSection.jsp");
			return mav;
		}

		if (sType == 0) { // 论坛版块
			sParentId = 0; // 设置其父版块编号为0
		}
		SectionInfo sectiontemp = section_dao.getPSectionBySname(sName);
		if (sectiontemp != null) {
			section.setSid(sectiontemp.getSid());
		}
		section.setSname(sName);
		section.setSmasterid(masterid);
		section.setSparentid(sParentId);
		section_dao.addSection(section);
		mav.setViewName("manager/sectionmanager.do");
		return mav;
	}

	@RequestMapping(value = "/sectionmanager.do")
	public ModelAndView section(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<SectionInfo> list = section_dao.getAllSections();
		List<SectionManger> sectionlist = new ArrayList<SectionManger>();
		String mastername = "";
		String psection = "";
		if (list != null) {
			for (SectionInfo ai : list) {
				SectionManger sm = new SectionManger();
				sm.setId(ai.getSid());
				sm.setsName(ai.getSname());
				if (ai.getSparentid() != 0) {
					SectionInfo section = section_dao.getPSectionById(ai
							.getSparentid());
					if (section != null) {
						psection = section.getSname();
					}
				}
				sm.setPsection(psection);
				UserInfo user = udao.getUserInfoByID(ai.getSmasterid());
				if (user != null) {
					mastername = user.getUname();
				}
				sm.setMastername(mastername);
				sectionlist.add(sm);
			}
		}

		HttpSession session = request.getSession();

		session.setAttribute("sectionlist", sectionlist);
		session.setMaxInactiveInterval(Integer.MAX_VALUE);

		mav.setViewName("manager/managerSection.jsp");
		System.out.println(sectionlist.size());
		return mav;
	}
	
//	usermanager.do
	@RequestMapping(value = "/usermanager.do")
	public ModelAndView usermanage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<UserInfo> userlist = udao.findAllUser();
		List<SectionManger> sectionlist = new ArrayList<SectionManger>();

		HttpSession session = request.getSession();

		session.setAttribute("userlist", userlist);
		session.setMaxInactiveInterval(Integer.MAX_VALUE);

		request.setAttribute("display", "usermanage");
		mav.setViewName("manager/usermanage.jsp");
		return mav;
	}
	
	@RequestMapping(value = "/updateuser_{id}.do")
	public ModelAndView updateuser(@PathVariable("id") String id,
			HttpServletRequest request) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView();
		Integer uid = Integer.parseInt(id.trim());
		UserInfo user = udao.getUserInfoByID(uid);
		udao.updateUser(-1, uid);
		request.setAttribute("message",
				"<script type='text/javascript'>alert('禁用成功！');</script>");
		mav.setViewName("manager/usermanager.do");
		return mav;
	}
	
	@RequestMapping(value = "/deleteuser_{id}.do")
	public ModelAndView deleteuser(@PathVariable("id") String id,
			HttpServletRequest request) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView();
		Integer uid = Integer.parseInt(id.trim());
		udao.deleteUserById(uid);
		System.out.println(uid);
		request.setAttribute("message",
				"<script type='text/javascript'>alert('删除信息成功！');</script>");
		mav.setViewName("manager/usermanager.do");
		return mav;
	}
}
