package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import until.CcmDBConnectionPool;
import until.ClDBConnectionPool;
import until.FpmDBConnectionPool;
import until.HrDBConnectionPool;
import until.LcmDBConnectionPool;
import until.SqDBConnectionPool;
import until.TpDBConnectionPool;

public class Test {
	public static void main(String[] args) {
		sD();
	}

	public static String sD() {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			con = HrDBConnectionPool.getInstance().getConnection();
			String sql = "select 员工号  from ofnchr.empinfo_lizhi_v  where 离职日期  is   null";
			pre = con.prepareStatement(sql);
			resultSet = pre.executeQuery();
			int i = 0;
			while (resultSet.next()) {
				System.out.println(resultSet.getString("员工号") + "=" + i++);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				System.out.println("数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
