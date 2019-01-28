package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import until.CcmDBConnectionPool;
import until.ClDBConnectionPool;
import until.FpmDBConnectionPool;
import until.LcmDBConnectionPool;
import until.LocalhostDBConnectionPool;
import until.SqDBConnectionPool;
import until.TpDBConnectionPool;

public class sqtest {
	public static void main(String[] args) throws Exception {
		List<String> dd=lookUpListEmp();
		List<String>	listDis=dimissionEmp(dd);
		int i=0;
		System.out.println("12222222222222222222222222222222222222222222222");
		for( String st:listDis){
			System.out.println(st+"="+i++);
			
		}
	}
	/**
	 * 查询集团在职总人数+非禁用人员的员工号
	 * 
	 * @return
	 */
	public static List<String> lookUpListEmp() {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		List<String> lists = new ArrayList<String>();
		try {
			con = LocalhostDBConnectionPool.getInstance().getConnection();
			System.out.println("sq");
			String sql = "select login_name ,name ,enabled  from acs_user where enabled =0";
			pre = con.prepareStatement(sql);
			result = pre.executeQuery();
			int i = 1;
			while (result.next()) {
			//	result.getString("login_name")
			lists.add(result.getString("login_name"));
		    //System.out.println(result.getString("login_name"));
			}
			System.out.println("----------------------");
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
				System.out.println("sq数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lists;
	}

	/**
	 * 求离职人员
	 */
	public static List<String> dimissionEmp(List<String> lists) {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		List<String> listDis = new ArrayList<String>();
		try {
			con = SqDBConnectionPool.getInstance().getConnection();
			System.out.println("连接stu数据库成功！4");
			String sql = "select login_name ,name ,enabled  from acs_user where enabled =0 and login_name is not null ";
			pre = con.prepareStatement(sql);
			result = pre.executeQuery();
			//int i = 0;
			while (result.next()){
				  listDis.add(result.getString("login_name"));
			}
			lists.removeAll(listDis);
			//listDis.removeAll(lists);
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
				System.out.println("stu数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lists;

	}
}
