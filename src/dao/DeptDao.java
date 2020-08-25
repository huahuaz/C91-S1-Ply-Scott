package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.Dept;
import util.DBHelper;

public class DeptDao {

	public List<Map<String, Object>> selectAll() throws SQLException {
		DBHelper dbh = null;
		try {
			dbh = new DBHelper();
			String sql = "select * from dept";
			return dbh.select(sql);
		} finally {
			dbh.close();
		}
	}

	/**
	 * 组合条件查询: 名称 ==> select * from dept where dname like ? 名称+地址 ==> select * from
	 * dept where dname like ? and loc like ? 不带任何查询 ==> select * from dept
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> selectByDept(Dept dept) throws SQLException {
		DBHelper dbh = null;
		try {
			dbh = new DBHelper();
			// 定义语句必须加where 1=1
			String sql = "select * from dept where 1=1";
			// 定义参数集合
			List<Object> params = new ArrayList<>();
			// 判断参数不为空 +添加参数
			if (dept.getDname() != null && dept.getDname().trim().isEmpty() == false) {
				sql += "and dname like ?";
				params.add("%" + dept.getDname() + "%");
			}
			if (dept.getLoc() != null && dept.getLoc().trim().isEmpty() == false) {
				sql += "and loc like ?";
				params.add("%" + dept.getLoc() + "%");
			}
			// 将List集合转换成数组
			Object[] pArray = params.toArray();
			return dbh.select(sql, pArray);
		} finally {
			dbh.close();
		}
	}

	public int insert(String deptno, String dname, String loc) throws SQLException {
		String sql = "insert into dept values(?,?,?)";
		DBHelper dbh = null;
		try {
			dbh = new DBHelper();
			return dbh.update(sql, deptno, dname, loc);
		} finally {
			dbh.close();
		}
	}

	public int updatet(String deptno, String dname, String loc) throws SQLException {
		// 业务潜规则:一般情况下不修改主键值
		String sql = "update dept set dname=?,loc=? where deptno=?";
		DBHelper dbh = null;
		try {
			dbh = new DBHelper();
			return dbh.update(sql, dname, loc, deptno);
		} finally {
			dbh.close();
		}
	}

	/**
	 * 新增 : 判断指定部门是否已存在
	 * 
	 * @param dname
	 * @return
	 * @throws SQLException
	 */
	public int coutByDname(String dname) throws SQLException {
		String sql = "select * from dept where dname=?";
		DBHelper dbh = null;
		try {
			dbh = new DBHelper();
			return dbh.count(sql, dname);
		} finally {
			dbh.close();
		}
	}

	/**
	 * 修改 : 判断指定部门是否已经存在，要排除自己的名称
	 * 
	 * @param dname
	 * @param deptno
	 * @return
	 * @throws SQLException
	 */
	public int coutByDname(String dname, String deptno) throws SQLException {
		String sql = "select * from dept where dname=? and deptno !=?";
		DBHelper dbh = null;
		try {
			dbh = new DBHelper();
			return dbh.count(sql, dname, deptno);
		} finally {
			dbh.close();
		}
	}
}
