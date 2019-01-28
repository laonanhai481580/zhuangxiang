package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import until.CcmDBConnectionPool;
import until.ClDBConnectionPool;
import until.FpmDBConnectionPool;
import until.HrDBConnectionPool;
import until.LcmDBConnectionPool;
import until.LocalhostDBConnectionPool;
import until.SqDBConnectionPool;
import until.TpDBConnectionPool;

public class Test2 {

	public static void main(String[] args) {
		sD();
	}

	public static String sD() {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		try {
			con = LocalhostDBConnectionPool.getInstance().getConnection();
			String sql = "select  login_name from acs_user";
			pre = con.prepareStatement(sql);
			resultSet = pre.executeQuery();
			int i = 0;
			while (resultSet.next()) {
				System.out.println(resultSet.getString("login_name"));
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
