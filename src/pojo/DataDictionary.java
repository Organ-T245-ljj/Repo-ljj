package pojo;

import java.util.Date;

public class DataDictionary {
	private int id;//����ID
	private String typeCode;//���ͱ���
	private String typeName;//��������
	private int valueId;//����ֵID
	private String valueName;//����ֵName
	private int createdBy;//�����ߣ���Դ��backend_user�û�����û�id��
	private Date creationDate;//����ʱ��
	private int modifyBy;//�����ߣ���Դ��backend_user�û�����û�id��
	private Date modifyDate;//���¸���ʱ��
	public int getId() {
		return id;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public int getValueId() {
		return valueId;
	}
	public String getValueName() {
		return valueName;
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
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public void setValueId(int valueId) {
		this.valueId = valueId;
	}
	public void setValueName(String valueName) {
		this.valueName = valueName;
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
