package dao;

import java.sql.SQLException;

import bean.JobChange;
import util.DBHelper;

public class JobChangeDao {
	public void insert(JobChange jc) throws SQLException {
		/**
		 * 数据库必须有seq_scott 序列
		 */
		String sql1 = "insert into Job_Change values (seq_scott.nextval,"
				+ "?,?,?,?,?,?,sysdate,?)";
		String sql2 = "update emp set job=?, sal=? where empno=?";
		DBHelper dbh = null;
		try {
			dbh = new DBHelper();
			dbh.update(sql1, 
					jc.getEmpno(),
					jc.getOldjob(),
					jc.getNewjob(),
					jc.getOldsal(),
					jc.getNewsal(),
					jc.getCause(),
					jc.getAllow());
			dbh.update(sql2, 
					jc.getNewjob(), 
					jc.getNewsal(), 
					jc.getEmpno());
			// 正常情况提交
			dbh.commit();
		} catch(SQLException e){
			dbh.rollback();
		} finally {
			dbh.close();
		}
	}
}
