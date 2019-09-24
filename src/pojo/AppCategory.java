package pojo;

import java.util.Date;

public class AppCategory {
	private int id;//主键ID
	private String categoryCode;//分类编码
	private String categoryName;//分类名称
	private int parentId;//父级节点id
	private int createdBy;//创建者（来源于backend_user用户表的用户id）
	private Date creationTime;//创建时间
	private int modifyBy;//更新者（来源于backend_user用户表的用户id）
	private Date modifyDate;//最新更新时间
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
