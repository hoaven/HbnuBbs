package com.kakanshun.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.kakanshun.domain.SectionInfo;
import com.kakanshun.page.*;

public class SectionInfoDAO {

	private BaseDAO dao = new BaseDAO();
	private ResultSet rs = null;
	private List<NavigationPage> list = new ArrayList<NavigationPage>();

	/**
	 * ���Ӱ��
	 */
	public boolean addSection(SectionInfo section) {
		String sql = "insert into sectionInfo(sName,sMasterId,sParentId) values(?,?,?)";
		int result = -1;
		try {
			result = dao.executeUpdate(sql, new Object[] { section.getSname(),
					section.getSmasterid(), section.getSparentid() });
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
			dao.closeConnection();
		}
		return result > 0 ? true : false;
	}

	/**
	 * �޸İ��
	 * 
	 * @param section
	 * @return
	 */
	public boolean updateSection(SectionInfo section) {
		String sql = "exec updatesectionbyid ?,?,?";
		int result = -1;
		try {
			result = dao.executeUpdate(sql, new Object[] { section.getSname(),
					section.getSmasterid(), section.getSid() });
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
			dao.closeConnection();
		}
		return result > 0 ? true : false;
	}

	/**
	 * ���ݰ��idɾ�����
	 * 
	 * @param section
	 * @return
	 */
	public boolean deleteSection(int sId) {
		String sql = "exec deletesectionbyid ?";
		int result = -1;
		try {
			result = dao.executeUpdate(sql, new Object[] { sId });
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeStatement();
			dao.closeConnection();
		}
		return result > 0 ? true : false;
	}
	
	/**
	 * ���ݸ����id��ȡ�����Ϣ
	 * 
	 * @param sId
	 *            �����
	 * @return List<SectionInfo> ����һ������
	 */
	public List<SectionInfo> getSectionById(Integer sId) {
		List<SectionInfo> list = new ArrayList<SectionInfo>();// ����б�
		String sql = "select * from sectionInfo " + "where sparentId = ?";// ���ݰ���Ų����Ӱ��
		try {
			rs = dao.executeQuery(sql, new Object[] { sId });// ִ�в�ѯ
			while (rs != null && rs.next()) { // ������ҵ���¼
				SectionInfo section = new SectionInfo();// �����Ϣ����
				section.setSid(rs.getInt("sId")); // ���ð����
				section.setSmasterid(rs.getInt("sMasterId"));// ���ð������
				section.setSname(rs.getString("sName")); // ���ð������
				section.setSparentid(rs.getInt("sParentId"));// ���ø������
				section.setStopiccount(rs.getInt("sTopicCount"));// ���ð��������
				list.add(section);// ���Ӱ�����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet(); // �رս����
			dao.closeStatement(); // �رմ�������
			dao.closeConnection(); // �ر����Ӷ���
		}
		return list; // ���ذ���б�
	}

	/**
	 * ����id��ð����Ϣ
	 * 
	 * @param id
	 *            �����
	 * @return List<SectionInfo> ����һ������
	 */
	public SectionInfo getPSectionById(Integer id) {
		SectionInfo section = null;
		String sql = "select * from sectionInfo where sId = ?";
		try {
			rs = dao.executeQuery(sql, new Object[] { id });
			if (rs != null && rs.next()) {
				section = new SectionInfo();

				section.setSid(rs.getInt("sId"));
				section.setSmasterid(rs.getInt("sMasterId"));
				section.setSname(rs.getString("sName"));
				section.setSparentid(rs.getInt("sParentId"));
				section.setStopiccount(rs.getInt("sTopicCount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return section;
	}

	/**
	 * ���ݰ�����ƻ�ð����Ϣ
	 * 
	 * @param sname
	 *            �����
	 * @return List<SectionInfo> ����һ������
	 */
	public SectionInfo getPSectionBySname(String sname) {
		SectionInfo section = null;
		String sql = "select * from sectionInfo where sName = ?";
		try {
			rs = dao.executeQuery(sql, new Object[] { sname });
			if (rs != null && rs.next()) {
				section = new SectionInfo();

				section.setSid(rs.getInt("sId"));
				section.setSmasterid(rs.getInt("sMasterId"));
				section.setSname(rs.getString("sName"));
				section.setSparentid(rs.getInt("sParentId"));
				section.setStopiccount(rs.getInt("sTopicCount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return section;
	}

	/**
	 * ���ݰ��id��õ�����
	 * 
	 * @param sId
	 *            �����
	 * @return List<String> ����һ������ ע��:�����ͬһ�����ж�ε��ô˷�������������clearList()�����һ�εĽ��
	 */
	public List<NavigationPage> getNavigationMenuById(Integer sId) {
		String sql = "select sParentId,sName,sId from sectionInfo where sId = ?";
		try {
			rs = dao.executeQuery(sql, new Object[] { sId });
			if (rs != null && rs.next()) {
				NavigationPage temp = new NavigationPage();
				temp.setParentid(rs.getInt("sParentId"));
				temp.setSid(rs.getInt("sId"));
				temp.setSname(rs.getString("sName"));
				list.add(temp);
				this.getNavigationMenuById(rs.getInt("sParentId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet();
			dao.closeStatement();
			dao.closeConnection();
		}
		return list;
	}

	/**
	 * ���list����
	 */
	public void clearList() {
		this.list.clear();
	}

	/**
	 * ���ݰ��id�ж��Ƿ��Ǹ����
	 * 
	 * @param sId
	 *            �����
	 * @return Boolean ����һ��������
	 */
	public Boolean isParentById(Integer sId) {
		String sql = "select * from sectionInfo where sid = ? and sParentId = 0";
		try {
			rs = dao.executeQuery(sql, new Object[] { sId });
			if (rs != null && rs.next()) {
				return true;
			}
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
	 * ��ȡ���еİ����Ϣ
	 */
	public List<SectionInfo> getAllSections() {
		List<SectionInfo> list = new ArrayList<SectionInfo>();// ����б�
		String sql = "exec getallsection";// ���ݰ���Ų����Ӱ��
		try {
			rs = dao.executeQuery(sql, new Object[] {});// ִ�в�ѯ
			while (rs != null && rs.next()) { // ������ҵ���¼
				SectionInfo section = new SectionInfo();// �����Ϣ����
				section.setSid(rs.getInt("sId")); // ���ð����
				section.setSmasterid(rs.getInt("sMasterId"));// ���ð������
				section.setSname(rs.getString("sName")); // ���ð������
				section.setSparentid(rs.getInt("sParentId"));// ���ø������
				section.setStopiccount(rs.getInt("sTopicCount"));// ���ð��������
				list.add(section);// ���Ӱ�����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeResultSet(); // �رս����
			dao.closeStatement(); // �رմ�������
			dao.closeConnection(); // �ر����Ӷ���
		}
		return list; // ���ذ���б�
	}
}