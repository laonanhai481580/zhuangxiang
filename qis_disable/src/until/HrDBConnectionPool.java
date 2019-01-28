package until;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

public class HrDBConnectionPool {
	private static volatile HrDBConnectionPool dbConnection;
	private ComboPooledDataSource cpds;

	/**
	 * �ڹ��캯����ʼ����ʱ���ȡ��������
	 */
	private HrDBConnectionPool() {
		try {
			/** ͨ�������ļ���ȡ���ݿ����ӵĲ���ֵ **/
			Properties properties = new Properties();
			InputStream in = getClass().getClassLoader().getResourceAsStream(
					"config.properties");
			properties.load(in);
			/** ��ȡ�����ļ��е�ֵ **/
			String driverClassName = properties.getProperty("HR_DRIVER");
			String url = properties.getProperty("HR_URL");
			String username = properties.getProperty("HR_USERNAME");
			String password = properties.getProperty("HR_PASSWORD");
			System.out.println(url);
			System.out.println(username);
			System.out.println(password);
			System.out.println(driverClassName);
			/** ���ݿ����ӳض��� **/
			cpds = new ComboPooledDataSource();
			/** �������ݿ��������� **/
			cpds.setDriverClass(driverClassName);
			/** �������ݿ����ӵ�ַ **/
			cpds.setJdbcUrl(url);
			/** �������ݿ������û��� **/
			cpds.setUser(username);
			/** �������ݿ��������� **/
			cpds.setPassword(password);

			/** ��ʼ��ʱ������������,Ӧ��minPoolSize��maxPoolSize֮��ȡֵ.Ĭ��Ϊ3 **/
			cpds.setInitialPoolSize(3);
			/** ���ӳ��б����������������.Ĭ��Ϊ15 **/
			cpds.setMaxPoolSize(10);
			/** �����ӳ��е���������ʱ��C3POһ���Դ����µ�������Ŀ; **/
			cpds.setAcquireIncrement(1);
			/** �����������������ӳ��еĿ�������,Ĭ��Ϊ0��ʾ�����; **/
			cpds.setIdleConnectionTestPeriod(60);
			/** ������ʱ��,��������ʱ������ӽ�������.Ϊ0����������������.Ĭ��Ϊ0; **/
			cpds.setMaxIdleTime(3000);

			/**
			 * ���������Ĵ���ֻ����Ҫ��ʱ��ʹ�����������Ϊtrue��ô��ÿ��connection�ύ��
			 * ʱ�򶼽�У������Ч�ԡ�����ʹ��idleConnectionTestPeriod��automaticTestTable
			 * �ȷ������������Ӳ��Ե����ܡ�Default: false
			 **/
			cpds.setTestConnectionOnCheckout(true);

			/** �����Ϊtrue��ô��ȡ�����ӵ�ͬʱ��У�����ӵ���Ч�ԡ�Default: false **/
			cpds.setTestConnectionOnCheckin(true);
			/** �����ڴ����ݿ��ȡ�µ�����ʧ�ܺ��ظ����Ի�ȡ�Ĵ�����Ĭ��Ϊ30; **/
			cpds.setAcquireRetryAttempts(30);
			/** ���������м��ʱ��Ĭ��Ϊ1000���� **/
			cpds.setAcquireRetryDelay(1000);
			/**
			 * ��ȡ����ʧ�ܽ����������еȴ���ȡ���ӵ��߳��쳣,
			 * ��������Դ����Ч�ı���,�����´ε���getConnection()��ʱ��������Ի�ȡ����.�����Ϊtrue,
			 * ��ô���Ի�ȡ����ʧ�ܺ������Դ�������Ѿ��Ͽ������ùر�.Ĭ��Ϊfalse
			 **/
			cpds.setBreakAfterAcquireFailure(true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

	}

	/**
	 * ��ȡ���ݿ����Ӷ��󣬵���
	 */
	public static HrDBConnectionPool getInstance() {
		if (dbConnection == null) {
			synchronized (HrDBConnectionPool.class) {
				if (dbConnection == null) {
					dbConnection = new HrDBConnectionPool();
				}
			}
		}
		return dbConnection;
	}

	/**
	 * ��ȡ���ݿ�����
	 * 
	 * @throws SQLException
	 */
	public final synchronized Connection getConnection() throws SQLException {

		return cpds.getConnection();
	}

	/**
	 * finalize()�������������ռ���ɾ������֮ǰ�����������õġ�
	 */
	protected void finalize() throws Throwable {
		DataSources.destroy(cpds);
		super.finalize();
	}
}
