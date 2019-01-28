package com.ofilm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import until.LocalhostDBConnectionPool;

public class DBUtil {
   /**
    * ����ִ���������޸ģ�ɾ��
    *
    * @param sql      sql���
    * @param bindArgs �󶨲���
    * @return Ӱ�������
    * @throws SQLException SQL�쳣
    */
   public static int executeUpdate(String sql, Object[] bindArgs) throws SQLException {
       /**Ӱ�������**/
       int affectRowCount = -1;
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       try {
           /**�����ݿ����ӳ��л�ȡ���ݿ�����**/
           connection = LocalhostDBConnectionPool.getInstance().getConnection();
           /**ִ��SQLԤ����**/
           preparedStatement = connection.prepareStatement(sql.toString());
           /**���ò��Զ��ύ���Ա����ڳ����쳣��ʱ�����ݿ�ع�**/
           connection.setAutoCommit(false);
           //System.out.println(getExecSQL(sql, bindArgs));
           if (bindArgs != null) {
               /**�󶨲�������sqlռλ���е�ֵ**/
               for (int i = 0; i < bindArgs.length; i++) {
                   preparedStatement.setObject(i + 1, bindArgs[i]);
               }
           }
           /**ִ��sql**/
           affectRowCount = preparedStatement.executeUpdate();
           connection.commit();
           String operate;
           if (sql.toUpperCase().indexOf("DELETE FROM") != -1) {
               operate = "ɾ��";
           } else if (sql.toUpperCase().indexOf("INSERT INTO") != -1) {
               operate = "����";
           } else {
               operate = "�޸�";
           }
           System.out.println("�ɹ�" + operate + "��" + affectRowCount + "��");
           System.out.println();
       } catch (Exception e) {
           if (connection != null) {
               connection.rollback();
           }
           e.printStackTrace();
           throw e;
       } finally {
           if (preparedStatement != null) {
               preparedStatement.close();
           }
           if (connection != null) {
               connection.close();
           }
       }
       return affectRowCount;
   }


  /**
    * ִ�в�ѯ
    *
    * @param sql      Ҫִ�е�sql���
    * @param bindArgs �󶨵Ĳ���
    * @return List<Map<String, Object>>���������
    * @throws SQLException SQLִ���쳣
    */
   public static List<Map<String, Object>> executeQuery(String sql, Object[] bindArgs) throws SQLException {
       List<Map<String, Object>> datas = new ArrayList<>();
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;

       try {
           /**��ȡ���ݿ����ӳ��е�����**/
           connection = LocalhostDBConnectionPool.getInstance().getConnection();
           preparedStatement = connection.prepareStatement(sql);
           if (bindArgs != null) {
               /**����sqlռλ���е�ֵ**/
               for (int i = 0; i < bindArgs.length; i++) {
                   preparedStatement.setObject(i + 1, bindArgs[i]);
               }
           }
          // System.out.println(getExecSQL(sql, bindArgs));
           /**ִ��sql��䣬��ȡ�����**/
           resultSet = preparedStatement.executeQuery();
          // getDatas(resultSet);
           System.out.println();
       } catch (Exception e) {
           e.printStackTrace();
           throw e;
       } finally {
           if (resultSet != null) {
               resultSet.close();
           }
           if (preparedStatement != null) {
               preparedStatement.close();
           }
           if (connection != null) {
               connection.close();
           }
       }
       return datas;
   }
   /**
    * ִ�����ݿ�������
    *
    * @param valueMap  �������ݱ���keyΪ������valueΪ�ж�Ӧ��ֵ��Map����
    * @param tableName Ҫ��������ݿ�ı���
    * @return Ӱ�������
    * @throws SQLException SQL�쳣
    */
   public static int insert(String tableName, Map<String, Object> valueMap) throws SQLException {

       /**��ȡ���ݿ�����Map�ļ�ֵ�Ե�ֵ**/
       Set<String> keySet = valueMap.keySet();
       Iterator<String> iterator = keySet.iterator();
       /**Ҫ������ֶ�sql����ʵ������keyƴ������**/
       StringBuilder columnSql = new StringBuilder();
       /**Ҫ������ֶ�ֵ����ʵ���ǣ�**/
       StringBuilder unknownMarkSql = new StringBuilder();
       Object[] bindArgs = new Object[valueMap.size()];
       int i = 0;
       while (iterator.hasNext()) {
           String key = iterator.next();
           columnSql.append(i == 0 ? "" : ",");
           columnSql.append(key);

           unknownMarkSql.append(i == 0 ? "" : ",");
           unknownMarkSql.append("?");
           bindArgs[i] = valueMap.get(key);
           i++;
       }
       /**��ʼƴ�����sql���**/
       StringBuilder sql = new StringBuilder();
       sql.append("INSERT INTO ");
       sql.append(tableName);
       sql.append(" (");
       sql.append(columnSql);
       sql.append(" )  VALUES (");
       sql.append(unknownMarkSql);
       sql.append(" )");
       return executeUpdate(sql.toString(), bindArgs);
   }
   /**
    * ִ�и��²���
    *
    * @param tableName ����
    * @param valueMap  Ҫ���ĵ�ֵ
    * @param whereMap  ����
    * @return Ӱ�������
    * @throws SQLException SQL�쳣
    */
   public static int update(String tableName, Map<String, Object> valueMap, Map<String, Object> whereMap) throws SQLException {
       /**��ȡ���ݿ�����Map�ļ�ֵ�Ե�ֵ**/
       Set<String> keySet = valueMap.keySet();
       Iterator<String> iterator = keySet.iterator();
       /**��ʼƴ�����sql���**/
       StringBuilder sql = new StringBuilder();
       sql.append("UPDATE ");
       sql.append(tableName);
       sql.append(" SET ");

       /**Ҫ���ĵĵ��ֶ�sql����ʵ������keyƴ������**/
       StringBuilder columnSql = new StringBuilder();
       int i = 0;
       List<Object> objects = new ArrayList<>();
       while (iterator.hasNext()) {
           String key = iterator.next();
           columnSql.append(i == 0 ? "" : ",");
           columnSql.append(key + " = ? ");
           objects.add(valueMap.get(key));
           i++;
       }
       sql.append(columnSql);

       /**���µ�����:Ҫ���ĵĵ��ֶ�sql����ʵ������keyƴ������**/
       StringBuilder whereSql = new StringBuilder();
       int j = 0;
       if (whereMap != null && whereMap.size() > 0) {
           whereSql.append(" WHERE ");
           iterator = whereMap.keySet().iterator();
           while (iterator.hasNext()) {
               String key = iterator.next();
               whereSql.append(j == 0 ? "" : " AND ");
               whereSql.append(key + " = ? ");
               objects.add(whereMap.get(key));
               j++;
           }
           sql.append(whereSql);
       }
       return executeUpdate(sql.toString(), objects.toArray());
   }
   /**
    * ִ��ɾ������
    *
    * @param tableName Ҫɾ���ı���
    * @param whereMap  ɾ��������
    * @return Ӱ�������
    * @throws SQLException SQLִ���쳣
    */
   public static int delete(String tableName, Map<String, Object> whereMap) throws SQLException {
       /**׼��ɾ����sql���**/
       StringBuilder sql = new StringBuilder();
       sql.append("DELETE FROM ");
       sql.append(tableName);

       /**���µ�����:Ҫ���ĵĵ��ֶ�sql����ʵ������keyƴ������**/
       StringBuilder whereSql = new StringBuilder();
       Object[] bindArgs = null;
       if (whereMap != null && whereMap.size() > 0) {
           bindArgs = new Object[whereMap.size()];
           whereSql.append(" WHERE ");
           /**��ȡ���ݿ�����Map�ļ�ֵ�Ե�ֵ**/
           Set<String> keySet = whereMap.keySet();
           Iterator<String> iterator = keySet.iterator();
           int i = 0;
           while (iterator.hasNext()) {
               String key = iterator.next();
               whereSql.append(i == 0 ? "" : " AND ");
               whereSql.append(key + " = ? ");
               bindArgs[i] = whereMap.get(key);
               i++;
           }
           sql.append(whereSql);
       }
       return executeUpdate(sql.toString(), bindArgs);
   }
	
}
