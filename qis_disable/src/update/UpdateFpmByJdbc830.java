package update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import until.FpmDBConnectionPool;
import until.HrDBConnectionPool;

public class UpdateFpmByJdbc830 {

	public static void main(String[] args) throws Exception {
		diableNum();
	}

	/**
	 * 
	 * �����˺�
	 */
	public static void diableNum() {
		// �ǽ�����Ա��Ա����
		List<String> lists = lookUpListEmp();
		// ��ְ��Ա�˺�
		Map<String, String> mapDis = dimissionEmp(lists);
		for (Map.Entry<String, String> entry : mapDis.entrySet()) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}

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
			con = FpmDBConnectionPool.getInstance().getConnection();
			System.out.println("Fpm���ݿ����ӳɹ�2��");
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String sql = "UPDATE("
						+ "select * from ("
						+ "select id,login_name, name ,enabled ,"
						+ "case when regexp_like(login_name,'([A-Z]+[0-9])') then '��д��ĸ������' "
						+ "when regexp_like(login_name,'([a-z]+[0-9])') then 'Сд��ĸ������' "
						+ "when regexp_like(login_name,'[0-9]') then '����' "
						+ "when login_name is null then '��ֵ' else '����' end type_login_name "
						+ "from acs_user where enabled=1 "
						+ ")where type_login_name='��д��ĸ������' "
						+ ") set enabled =0 where login_name=?  or  login_name=?";
				pre = con.prepareStatement(sql);
				pre.setString(1, entry.getKey());
				pre.setString(2, entry.getValue());
				pre.executeUpdate();
				pre.close();
			}
			System.out.println("Fpm�����޸ĳɹ�2");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				System.out.println("Fpm���ݿ������ѹرգ�2");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * ��ѯ������ְ������
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
			System.out.println("����OFILM_HRDB���ݿ�ɹ���1");
			String sql = "select ��Ա����, Ա����   from ofnchr.empinfo_lizhi_v  where ��ְ����  is  null";
			pre = con.prepareStatement(sql);
			result = pre.executeQuery();
			while (result.next()) {
				lists.add(result.getString("Ա����"));
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
				System.out.println("OFILM_HRDB���ݿ������ѹرգ�1");
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
			con = FpmDBConnectionPool.getInstance().getConnection();
			System.out.println("����Fpm���ݿ�ɹ���1");
			String sql = "select * from ("
					+ "select id,login_name, name ,enabled ,"
					+ "case when regexp_like(login_name,'([A-Z]+[0-9])') then '��д��ĸ������' "
					+ "when regexp_like(login_name,'([a-z]+[0-9])') then 'Сд��ĸ������' "
					+ "when regexp_like(login_name,'[0-9]') then '����' "
					+ "when login_name is null then '��ֵ' else '����' end type_login_name "
					+ "from acs_user where enabled=1 "
					+ ")where type_login_name='��д��ĸ������'";
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
				System.out.println("Fpm���ݿ������ѹرգ�1");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
