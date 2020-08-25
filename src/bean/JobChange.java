package bean;

import java.sql.Timestamp;

public class JobChange implements java.io.Serializable {
	/**
	 * 岗位调整实体类
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer empno;
	private String oldjob;
	private String newjob;
	private Float oldsal;
	private Float newsal;
	private String cause;
	private Timestamp createtime;
	private Integer allow;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public String getOldjob() {
		return oldjob;
	}

	public void setOldjob(String oldjob) {
		this.oldjob = oldjob;
	}

	public String getNewjob() {
		return newjob;
	}

	public void setNewjob(String newjob) {
		this.newjob = newjob;
	}

	public Float getOldsal() {
		return oldsal;
	}

	public void setOldsal(Float oldsal) {
		this.oldsal = oldsal;
	}

	public Float getNewsal() {
		return newsal;
	}

	public void setNewsal(Float newsal) {
		this.newsal = newsal;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Integer getAllow() {
		return allow;
	}

	public void setAllow(Integer allow) {
		this.allow = allow;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "JobChange [id=" + id + ", empno=" + empno + ", oldjob=" + oldjob + ", newjob=" + newjob + ", oldsal="
				+ oldsal + ", newsal=" + newsal + ", cause=" + cause + ", createtime=" + createtime + ", allow=" + allow
				+ "]";
	}

}
