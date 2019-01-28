package com.test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ofilm.DBUtil;

public class DBTest {
	public static void main(String[] args) {
        System.out.println("���ݿ��ԭ����");
        // testQuery3();
        testInsert();
        System.out.println("ִ�в���������");
        //testQuery3();
        testUpdate();
        System.out.println("ִ���޸ĺ������");
        //testQuery3();
        testDelete();
        System.out.println("ִ��ɾ���������");
        //testQuery3();
        System.out.println("�������Ĳ�ѯ1");
        // testQuery2();
        System.out.println("�������Ĳ�ѯ2");
        // testQuery1();
    }



    /**
     * ���Բ���
     */
    private static void testInsert() {
        Map<String, Object> map = new HashMap<>();
        map.put("emp_id", 1013);
        map.put("name", "JDBCUtil����");
        map.put("job", "developer");
        map.put("salary", 10000);
        map.put("hire_date", new java.sql.Date(System.currentTimeMillis()));
        try {
            int count = DBUtil.insert("emp_test", map);
                  } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * ���Ը���
     */
    private static void testUpdate() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "���Ը���");

        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("emp_id", "1013");
        try {
            int count = DBUtil.update("emp_test", map, whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * ����ɾ��
     */
    private static void testDelete() {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("emp_id", 1013);
        whereMap.put("job", "developer");
        try {
            int count = DBUtil.delete("emp_test", whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * ��ѯ��ʽһ
     */
    public static void testQuery1() throws SQLException {
        Map<String,Object> whereMap=new HashMap<String,Object>();
        whereMap.put("salary","10000");
    }
    /**
     * ��ѯ��ʽ��
     */
    public static void testQuery2() throws SQLException {
        String where = "job = ?  AND salary = ? ";
        String[] whereArgs = new String[]{"clerk", "3000"};
    }

    /**
     * ��ѯ��ʽ��
     */
    public static void testQuery3() throws SQLException{
    }
}
