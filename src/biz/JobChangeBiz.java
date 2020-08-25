package biz;

import java.sql.SQLException;

import bean.JobChange;
import dao.JobChangeDao;
import dao.UserDao;

public class JobChangeBiz {

	private JobChangeDao jDao = new JobChangeDao();

	private UserDao uDao = new UserDao();

	/**
	 * 复杂的数据库操作,包括 2次 修改数据库的操作
	 * @param jc
	 * @throws BizException
	 */
	public void create(JobChange jc) throws BizException {
		// 业务逻辑:参数的校验, 数据的检查
		if (jc.getOldjob() == null || jc.getOldjob().trim().isEmpty()) {
			throw new BizException("请输入原职位!");
		}
		if (jc.getNewjob() == null || jc.getNewjob().trim().isEmpty()) {
			throw new BizException("请输入新职位!");
		}
		try {
			jDao.insert(jc);
		} catch (SQLException e) {
			// 异常转型
			throw new BizException("系统内部错误,请联系管理员!");
		}
	}
}
