package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import until.HrDBConnectionPool;
import until.SqDBConnectionPool;

public class UpdateLocalhostByJdbc830 {

	public static void main(String[] args) throws Exception {
		diableNum();
	}

	/**
	 * 
	 * 禁用账号
	 */
	public static void diableNum() {
		// 非禁用人员的员工号
		List<String> lists = lookUpListEmp();
		// 离职人员账号
		Map<String, String> mapDis = dimissionEmp(lists);
		// 禁用离职人员账号
		updatetSqData(mapDis);
	}

	/**
	 * 更新该事业群enabled 字段
	 * 
	 * @param lists
	 * @return
	 */
	public static String updatetSqData(Map<String, String> map){
		Connection con = null;
		PreparedStatement pre = null;
		try {
			con = SqDBConnectionPool.getInstance().getConnection();
			System.out.println("Localhost数据库连接成功1！");
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String sql = "UPDATE("
						+ "select * from ("
						+ "select id,login_name, name ,enabled ,"
						+ "case when regexp_like(login_name,'([A-Z]+[0-9])') then '大写字母和数字'"
						+ "when regexp_like(login_name,'([a-z]+[0-9])') then '小写字母和数字'"
						+ "when regexp_like(login_name,'[0-9]') then '数字'"
						+ "when login_name is null then '空值' else '其他' end type_login_name"
						+ "from acs_user where enabled=1"
						+ ")where type_login_name='大写字母和数字' "
						+ ") set enabled =0 where login_name=? or  login_name=?";
				pre = con.prepareStatement(sql);
				pre.setString(1, entry.getKey());
				pre.setString(2, entry.getValue());
				pre.executeUpdate();
				pre.close();
			}
			System.out.println("Localhost数据修改成功1");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				System.out.println("Localhost数据库连接已关闭！1");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 查询集团在职总人数
	 * 
	 * @return
	 */
	public static List<String> lookUpListEmp() {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		List<String> lists = new ArrayList<String>();
		try {
			con = HrDBConnectionPool.getInstance().getConnection();
			System.out.println("连接OFILM_HRDB数据库成功！3");
			String sql = "select 人员编码, 员工号   from ofnchr.empinfo_lizhi_v  where 离职日期  is  null";
			pre = con.prepareStatement(sql);
			result = pre.executeQuery();
			while (result.next()) {
				lists.add(result.getString("员工号"));
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
				System.out.println("OFILM_HRDB数据库连接已关闭！3");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lists;
	}

	/**
	 * 求离职人员
	 */
	public static Map<String, String> dimissionEmp(List<String> lists) {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		List<String> listDis = new ArrayList<String>();
		Map<String, String> map = new HashMap<String, String>();
		try {
			con = SqDBConnectionPool.getInstance().getConnection();
			System.out.println("连接Localhost数据库成功！4");
			String sql = "select * from ("
					+ "select id,login_name, name ,enabled ,"
					+ "case when regexp_like(login_name,'([A-Z]+[0-9])') then '大写字母和数字'"
					+ "when regexp_like(login_name,'([a-z]+[0-9])') then '小写字母和数字'"
					+ "when regexp_like(login_name,'[0-9]') then '数字'"
					+ "when login_name is null then '空值' else '其他' end type_login_name"
					+ "from acs_user where enabled=1"
					+ ")where type_login_name='大写字母和数字'";
			pre = con.prepareStatement(sql);
			result = pre.executeQuery();
			String str = "^[A-Z]{3}";
			while (result.next()) {
				String login = result.getString("login_name");
				if (login.length() > 3) {
					String d = login.substring(0, 3);
					if (d.matches(str)) {
						String dlogin = login.substring(2);
						map.put(dlogin, login);
						listDis.add(dlogin);
					} else {
						map.put(login, login);
						listDis.add(login);
					}
				} else {
					map.put(login, login);
					listDis.add(login);
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
				System.out.println("OFILM_HRDB数据库连接已关闭！3");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
