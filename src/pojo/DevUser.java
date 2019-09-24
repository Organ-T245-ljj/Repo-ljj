package pojo;

import java.util.Date;

public class DevUser {
	private int id;//主键id
	private String devCode;//开发者帐号
	private String devName;//开发者名称SXDC 
	private String devPassword;//开发者密码
	private String devEmail;//开发者电子邮箱
	private String devInfo;//开发者简介
	private int createdBy;//创建者（来源于backend_user用户表的用户id）
	private Date creationDate;//创建时间
	private int modifyBy;//更新者（来源于backend_user用户表的用户id）
	private Date modifyDate;//最新更新时间
	public int getId() {
		return id;
	}
	public String getDevCode() {
		return devCode;
	}
	public String getDevName() {
		return devName;
	}
	public String getDevPassword() {
		return devPassword;
	}
	public String getDevEmail() {
		return devEmail;
	}
	public String getDevInfo() {
		return devInfo;
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
	public void setId(int id) {
		this.id = id;
	}
	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}
	public void setDevName(String devName) {
		this.devName = devName;
	}
	public void setDevPassword(String devPassword) {
		this.devPassword = devPassword;
	}
	public void setDevEmail(String devEmail) {
		this.devEmail = devEmail;
	}
	public void setDevInfo(String devInfo) {
		this.devInfo = devInfo;
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
}
