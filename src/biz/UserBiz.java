package biz;

import java.sql.SQLException;
import java.util.Map;

import dao.UserDao;

public class UserBiz {

	private UserDao udao = new UserDao();

	/**
	 * 登录方法
	 * @return 
	 */
	public boolean login(String name,String pwd) throws BizException{
		//对用输入参数判断:参数校验
		if(name == null || name.trim().isEmpty()) {
			//提示方法调用者，这个出错 ==> 抛出一个业务异常
			throw new BizException("请输入用户名!");
		}
		
		if(pwd == null || pwd.trim().isEmpty()) {
			//提示方法调用者，这个出错 ==> 抛出一个业务异常
			throw new BizException("请输入密码!");
		}
		Map<String,Object> emp;
		try {
			//调用dao方法
			emp = udao.selectByLogin(name, pwd);
			System.out.println("登录的员工信息:"+emp);
			//判断结果
			if(emp!=null) {
				return true;
			}else {
				throw new BizException("用户名或密码错误,请重新输入!");
			}
		} catch (SQLException e) {
			// SQL 只能从dao抛出，不能再从biz抛出
			//不同逻辑层抛出不同异常
			//dao ==> SQL ==> SQLException
			//biz ==> 业务逻辑 ==> 自定义的业务异常
			throw new BizException("系统维护中...,请联系管理员!",e);
		}
	}

}
