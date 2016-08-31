package com.kakanshun.dao;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CheckDAO {

	private BaseDAO dao = new BaseDAO();
	private ResultSet rs = null;

	/**
	 * 验证版块名称
	 * 
	 * @param sName
	 * @return
	 */
	public Boolean checkSectionName(String sName) {//校验版块名称
		return sName.matches("[\u4e00-\u9fa50-9a-z]{3,20}");
	}

	/**
	 * 验证用户名
	 * 
	 * @param uName
	 * @return
	 */
	public Boolean checkUserName(String uName) {
		return uName.matches("[\u4e00-\u9fa5A-Za-z0-9]{3,10}");
	}

	/**
	 * 验证用户名是否已经存在
	 * @param uName
	 * @return Boolean 返回一个布尔型 true：存在
	 */
	public Boolean checkUserNameIsExist(String uName) {//验证用户名是否已经存在
		String sql = "select * from userInfo where uName = ?";
		Boolean flag = false;
		try {
			rs = dao.executeQuery(sql, new Object[] { uName });
			if(rs != null && rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();//关闭结果集
			dao.closeStatement();//关闭处理对象
			dao.closeConnection();//关闭连接对象
		}
		return flag;//返回查询结果
	}

	/**
	 * 验证密码
	 * 
	 * @param uPW
	 * @return
	 */
	public Boolean CheckUserPassWord(String uPW) {
		return uPW.matches("[0-9a-zA-Z]{3,20}");
	}

	/**
	 * 验证帖子标题
	 * 
	 * @param topic
	 * @return
	 */
	public Boolean checkTopic(String topic) {
		return topic.matches(".{3,20}");
	}

	/**
	 * 验证帖子正文
	 * 
	 * @param Contents
	 * @return
	 */
	public Boolean checContents(String Contents) {
		return Contents.matches("(.|[\r\n]){1,1000}");//如何匹配多行任意字符
	}

	public static void main(String[] args) {
		String s = "\r\n b a d a \r\n a";
		System.out.print(s.replace("\r\n", "p"));
	}
}
