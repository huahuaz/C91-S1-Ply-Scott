package biz;

import java.sql.SQLException;

import dao.DeptDao;

public class DeptBiz {
	private DeptDao dDao = new DeptDao();

	public int create(String deptno, String dname, String loc) throws BizException {
		// 业务逻辑:参数的校验, 数据的检查
		if (deptno == null || deptno.trim().isEmpty()) {
			throw new BizException("请输入部门编号!");
		}
		if (dname == null || dname.trim().isEmpty()) {
			throw new BizException("请输入部门名称!");
		}
		try {
			int count = dDao.coutByDname(dname);
			if (count > 0) {
				throw new BizException("该部门已存在!");
			}
			return dDao.insert(deptno, dname, loc);
		} catch (SQLException e) {
			// 异常转型
			throw new BizException("系统内部错误,请联系管理员!");
		}
	}
	
	public int modify(String deptno, String dname, String loc) throws BizException {
		// 业务逻辑:参数的校验, 数据的检查
		if (deptno == null || deptno.trim().isEmpty()) {
			throw new BizException("请输入部门编号!");
		}
		if (dname == null || dname.trim().isEmpty()) {
			throw new BizException("请输入部门名称!");
		}
		try {
			int count = dDao.coutByDname(dname,deptno);
			if (count > 0) {
				throw new BizException("该部门已存在!");
			}
			return dDao.updatet(deptno, dname, loc);
		} catch (SQLException e) {
			// 异常转型
			throw new BizException("系统内部错误,请联系管理员!");
		}
	}
}
