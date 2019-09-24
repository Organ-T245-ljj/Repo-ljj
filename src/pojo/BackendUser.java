package pojo;

import java.util.Date;

public class BackendUser {
	private int id;//主键id
	private String userCode;//用户编码
	private String userName;//用户名称
	private int userType;//用户角色类型（来源于数据字典表，分为：超管、财务、市场、运营、销售）
	private int createdBy;//创建者（来源于backend_user用户表的用户id）
	private Date creationDate;//创建时间
	private int modifyBy;//更新者（来源于backend_user用户表的用户id）
	private Date modifyDate;//最新更新时间
	private String userPassword;//;用户密码
	public int getId() {
		return id;
	}
	public String getUserCode() {
		return userCode;
	}
	public String getUserName() {
		return userName;
	}
	public int getUserType() {
		return userType;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public int getModifyBy() {
		return modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
