package pojo;

import java.util.Date;

public class AppCategory {
	private int id;//����ID
	private String categoryCode;//�������
	private String categoryName;//��������
	private int parentId;//�����ڵ�id
	private int createdBy;//�����ߣ���Դ��backend_user�û�����û�id��
	private Date creationTime;//����ʱ��
	private int modifyBy;//�����ߣ���Դ��backend_user�û�����û�id��
	private Date modifyDate;//���¸���ʱ��
	public int getId() {
		return id;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public int getParentId() {
		return parentId;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public Date getCreationTime() {
		return creationTime;
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
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
