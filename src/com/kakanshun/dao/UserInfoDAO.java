package com.kakanshun.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kakanshun.domain.UserInfo;

public class UserInfoDAO implements Serializable {
	private BaseDAO dao = new BaseDAO();
	
	/**
	 * 创建注册用户
	 * 
	 * @param userName
	 *            用户名
	 * @param passWord
	 *            密码
	 * @param sex
	 *            性别
	 * @param userFace
	 *            头像图片名
	 * @return boolean 返回true表示注册成功
	 */
	public boolean checkReg(String userName, String passWord, boolean sex,
			String userFace) {
		String sql = "insert into userInfo(uName,uPassWord,uSex,uFace) values(?,?,?,?)";
		int result = -1;
		try {
			result = dao.executeUpdate(sql, new Object[] { userName, passWord,
					sex, userFace });
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
			dao.closeConnection();
		}
		return result > 0 ? true : false;
	}

	/**
	 * 根据用户id获取用户信息
	 * @param uid
	 * @return
	 */
	public UserInfo getUserInfoByID(int uid) {
		UserInfo user = new UserInfo();
		String sql = "select * from userInfo where uId=?";
		ResultSet rs = null;
		try {
			rs = dao.executeQuery(sql, new Object[] { uid });
			if (rs != null && rs.next()) {      //结果集不为空，并且结果集有下一条记录
				user.setUid(rs.getInt("uId"));
				user.setUname(rs.getString("uName"));
				user.setUpassword(rs.getString("uPassWord"));
				user.setUsex(rs.getBoolean("uSex"));
				user.setUface(rs.getString("uFace"));
				user.setUregtime(rs.getDate("uRegTime"));
				user.setUtype(rs.getInt("uType"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return user;
	}

	/**
	 * 检查用户登录名和密码
	 * 
	 * @param userName
	 *            用户名
	 * @param passWord
	 *            密码
	 * @return boolean 返回true表示用户合法
	 */
	public boolean checkLogin(String userName, String passWord) {
		String sql = "select * from userInfo where uName=? and uPassWord=? ";
		ResultSet rs = null;
		try {
			rs = dao.executeQuery(sql, new Object[] { userName, passWord });
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			return (rs != null && rs.next()) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return false;
	}

	/**
	 * 根据用户名，获得用户信息
	 * 
	 * @param userName
	 *            用户名
	 * @return UserInfo 返回一个对象
	 */
	public UserInfo getUserInfo(String userName) {
		UserInfo user = new UserInfo();
		String sql = "select * from userInfo where uName=?";
		ResultSet rs = null;
		try {
			rs = dao.executeQuery(sql, new Object[] { userName });
			if (rs != null && rs.next()) {

				user.setUid(rs.getInt("uId"));
				user.setUname(rs.getString("uName"));
				user.setUpassword(rs.getString("uPassWord"));
				user.setUsex(rs.getBoolean("uSex"));
				user.setUface(rs.getString("uFace"));
				user.setUregtime(rs.getDate("uRegTime"));
				user.setUtype(rs.getInt("uType"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return user;
	}

	/**
	 * 根据用户类型id，获得用户名称
	 * 
	 * @param id
	 *            用户类型编号
	 * @return 返回用户类型名称
	 */
	public String getUserTypeNameById(Integer id) {
		switch (id) {
		case 0:
			return "会员";
		case 1:
			return "版主";
		case 2:
			return "管理员";
		}
		return "";
	}

	/**
	 * 根据布尔值获得性别名称
	 * 
	 * @param sex
	 *            布尔值 true：男 false：女
	 * @return 返回性别名
	 */
	public String getSexName(Boolean sex) {
		return sex ? "男" : "女";
	}
	
	public List<UserInfo> findAllUser() {
//		List<UserInfo> list = new ArrayList<UserInfo>();
//		String sql = "select * from userInfo";
//		ResultSet rs = null;
//		try {
//			rs = dao.executeQuery(sql, new Object[] { });
//			while (rs != null && rs.next()) {
//				UserInfo user = new UserInfo();
//				user.setUid(rs.getInt("uId"));
//				user.setUname(rs.getString("uName"));
//				user.setUpassword(rs.getString("uPassWord"));
//				user.setUsex(rs.getBoolean("uSex"));
//				user.setUface(rs.getString("uFace"));
//				user.setUregtime(rs.getDate("uRegTime"));
//				user.setUtype(rs.getInt("uType"));
//				list.add(user);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dao.closeResultSet();
//			dao.closeStatement();
//			dao.closeConnection();
//		}
//		return list;
		
//		prodao.createGetAllUser();    //创建存储过程
		String sql="exec getalluser";
		List<UserInfo> list = new ArrayList<UserInfo>();
		ResultSet rs = null;
		try {
			rs = dao.executeQuery(sql, new Object[] { });
			while (rs != null && rs.next()) {
				UserInfo user = new UserInfo();
				user.setUid(rs.getInt("uId"));
				user.setUname(rs.getString("uName"));
				user.setUpassword(rs.getString("uPassWord"));
				user.setUsex(rs.getBoolean("uSex"));
				user.setUface(rs.getString("uFace"));
				user.setUregtime(rs.getDate("uRegTime"));
				user.setUtype(rs.getInt("uType"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
//		prodao.dropGetAllUser();			//删除存储过程
		return list;
	}
	
	public boolean deleteUserById(int id) {
		List<UserInfo> list = new ArrayList<UserInfo>();
		String sql = "delete from userInfo where uId = ?";
		int result = -1;
		try {
			result = dao.executeUpdate(sql, new Object[] { id });
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return result > 0 ? true : false;
	}
	
	/**
	 * 修改用户权限
	 * @param utype
	 * @param uid
	 * @return
	 */
	public boolean updateUser(int utype,int uid) {
		String sql = "update userInfo set uType = ? where uId = ?";
		int result = -1;
		try {
			result = dao.executeUpdate(sql, new Object[] { utype,uid });
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
			dao.closeConnection();
		}
		return result > 0 ? true : false;
	}
}
