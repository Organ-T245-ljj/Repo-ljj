package pojo;

import java.util.Date;

public class DevUser {
	private int id;//����id
	private String devCode;//�������ʺ�
	private String devName;//����������SXDC 
	private String devPassword;//����������
	private String devEmail;//�����ߵ�������
	private String devInfo;//�����߼��
	private int createdBy;//�����ߣ���Դ��backend_user�û�����û�id��
	private Date creationDate;//����ʱ��
	private int modifyBy;//�����ߣ���Դ��backend_user�û�����û�id��
	private Date modifyDate;//���¸���ʱ��
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
