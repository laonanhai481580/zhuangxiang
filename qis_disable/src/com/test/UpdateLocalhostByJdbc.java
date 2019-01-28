package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import until.CcmDBConnectionPool;
import until.ClDBConnectionPool;
import until.FpmDBConnectionPool;
import until.HrDBConnectionPool;
import until.LcmDBConnectionPool;
import until.LocalhostDBConnectionPool;
import until.SqDBConnectionPool;
import until.TpDBConnectionPool;

public class UpdateLocalhostByJdbc {

	public static void main(String[] args) throws Exception {
		diableNum();
	}

	/**
	 * 
	 * �����˺�
	 */
	public static void diableNum(){
		// �ǽ�����Ա��Ա����
		List<String> listUser = lookUpUser();
		// ��������ԱԱ���źͷǽ�����Ա��Ա����
		List<String> lists = lookUpListEmp(listUser);
		// ��ְ��Ա�˺�
		Map<String, String> mapDis = dimissionEmp(lists);
		// ������ְ��Ա�˺�
		updatetSqData(mapDis);
	}

	/**
	 * ���¸���ҵȺenabled �ֶ�
	 * 
	 * @param lists
	 * @return
	 */
	public static String updatetSqData(Map<String, String> map) {
		Connection con = null;
		PreparedStatement pre = null;
		try {
			con = SqDBConnectionPool.getInstance().getConnection();
			System.out.println("Localhost���ݿ����ӳɹ�1��");
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String sql = "UPDATE acs_user  SET  enabled =0  WHERE  login_name =? or login_name =? and ";
				pre = con.prepareStatement(sql);
				pre.setString(1, entry.getKey());
				pre.setString(2, entry.getValue());
				pre.executeUpdate();
				pre.close();
			}
			System.out.println("Localhost�����޸ĳɹ�1");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				System.out.println("Localhost���ݿ������ѹرգ�1");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * ��ǽ���acs_user_rid���е���Ա�˺�
	 * 
	 * @param list
	 * @return
	 */
	public static List<String> lookUpUser() {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		List<String> listUser = new ArrayList<String>();
		try {
			con = SqDBConnectionPool.getInstance().getConnection();
			System.out.println("Localhost���ݿ����ӳɹ�!2");
			String sql = "select login_name from acs_user_rid";
			pre = con.prepareStatement(sql);
			result = pre.executeQuery();
			while (result.next()) {
				listUser.add(result.getString("login_name"));
				// System.out.println(result.getString("login_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				System.out.println("Localhost���ݿ������ѹرգ�2");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listUser;
	}
	
	/**
	 * ��ѯ������ְ������+�ǽ�����Ա��Ա����
	 * 
	 * @return
	 */
	public static List<String> lookUpListEmp(List<String> listUser) {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		List<String> lists = new ArrayList<String>();
		try {
			con = HrDBConnectionPool.getInstance().getConnection();
			System.out.println("����OFILM_HRDB���ݿ�ɹ���3");
			String sql = "select ��Ա����, Ա����   from ofnchr.empinfo_lizhi_v  where ��ְ����  is  null";
			pre = con.prepareStatement(sql);
			result = pre.executeQuery();
			int i = 1;
			while (result.next()) {
				lists.add(result.getString("Ա����"));
			}
			// ���Ϸǽ�����Ա
			for (String str : listUser) {
				lists.add(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (result != null)
					result.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				System.out.println("OFILM_HRDB���ݿ������ѹرգ�3");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lists;
	}

	/**
	 * ����ְ��Ա
	 */
	public static Map<String, String> dimissionEmp(List<String> lists) {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		List<String> listDis = new ArrayList<String>();
		Map<String, String> map = new HashMap<String, String>();
		try {
			con = SqDBConnectionPool.getInstance().getConnection();
			System.out.println("����Localhost���ݿ�ɹ���4");
		 String sql = "select login_name,name  from acs_user where name not  like '%��˾%' and  name  not like '%��%'";
			
			pre = con.prepareStatement(sql);
			result = pre.executeQuery();
			int i = 1;
			String str = "^[A-Z]{3}";
			while (result.next()){
				String login = result.getString("login_name");
				if (login.length() > 3) {
					String d = login.substring(0, 3);
					if (d.matches(str)) {
						String dlogin = login.substring(2);
						map.put(dlogin, login);
						listDis.add(dlogin);
					}else{
						map.put(login, login);
						listDis.add(login);
					}
				} else {
					map.put(login, login);
					listDis.add(login);
				  //System.out.println("login=="+login);
				}
			}
			List<String> existsa = new ArrayList<String>(listDis);
			List<String> existsb = new ArrayList<String>(lists);
			existsa.retainAll(existsb);
			listDis.removeAll(lists);
			for (String st : existsa) {
				map.remove(st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (result != null)
					result.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				System.out.println("OFILM_HRDB���ݿ������ѹرգ�3");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
