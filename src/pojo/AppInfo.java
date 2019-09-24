package pojo;

import java.util.Date;

public class AppInfo {
	private int id;//����id
	private String softwareName;//�������
	private String APKName;//APK���ƣ�Ψһ��
	private String supportROM;//֧��ROM
	private String interfaceLanguage;//��������
	private double softwareSize;//�����С����λ��M��
	private Date updateDate;//��������
	private int devId;//������id����Դ�ڣ�dev_user��Ŀ�����id��
	private String appInfo;//Ӧ�ü��
	private int status;//״̬����Դ�ڣ�data_dictionary��1 ����� 2 ���ͨ�� 3 ��˲�ͨ�� 4 ���ϼ� 5 ���¼ܣ�
	private Date onSaleDate;//�ϼ�ʱ��
	private Date offSaleDate;//�¼�ʱ��
	private int flatformId;//����ƽ̨����Դ�ڣ�data_dictionary��1 �ֻ� 2 ƽ�� 3 ͨ�ã�
	
	private int downloads;//����������λ���Σ�
	private int createdBy;//�����ߣ���Դ��dev_user��������Ϣ����û�id��
	private Date creationDate;//����ʱ��
	private int modifyBy;//�����ߣ���Դ��dev_user��������Ϣ����û�id��
	private Date modifyDate;//���¸���ʱ��
	private int categoryLevel1;//����һ�����ࣨ��Դ�ڣ�data_dictionary��
	private int categoryLevel2;//�����������ࣨ��Դ�ڣ�data_dictionary��
	private int categoryLevel3;//�����������ࣨ��Դ�ڣ�data_dictionary��
	private String logoPicPath;//LOGOͼƬurl·��
	private String logoLocPath;//LOGOͼƬ�ķ������洢·��
	private int versionId;//���µİ汾id
	private String versionNo;//�汾ֵ

	private String statusName;//״ֵ̬
	private String flatformName;//����ƽֵ̨
	private String categoryLevel1Name;//����1������ֵ
	private String categoryLeve21Name;//����1������ֵ
	private String categoryLeve31Name;//����1������ֵ
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getFlatformName() {
		return flatformName;
	}
	public void setFlatformName(String flatformName) {
		this.flatformName = flatformName;
	}
	public String getCategoryLevel1Name() {
		return categoryLevel1Name;
	}
	public void setCategoryLevel1Name(String categoryLevel1Name) {
		this.categoryLevel1Name = categoryLevel1Name;
	}
	public String getCategoryLeve21Name() {
		return categoryLeve21Name;
	}
	public void setCategoryLeve21Name(String categoryLeve21Name) {
		this.categoryLeve21Name = categoryLeve21Name;
	}
	public String getCategoryLeve31Name() {
		return categoryLeve31Name;
	}
	public void setCategoryLeve31Name(String categoryLeve31Name) {
		this.categoryLeve31Name = categoryLeve31Name;
	}
	public int getId() {
		return id;
	}
	public String getSoftwareName() {
		return softwareName;
	}
	public String getAPKName() {
		return APKName;
	}
	public String getSupportROM() {
		return supportROM;
	}
	public String getInterfaceLanguage() {
		return interfaceLanguage;
	}
	public double getSoftwareSize() {
		return softwareSize;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public int getDevId() {
		return devId;
	}
	public String getAppInfo() {
		return appInfo;
	}
	public int getStatus() {
		return status;
	}
	public Date getOnSaleDate() {
		return onSaleDate;
	}
	public Date getOffSaleDate() {
		return offSaleDate;
	}
	public int getFlatformId() {
		return flatformId;
	}
	public int getCategoryLevel3() {
		return categoryLevel3;
	}
	public int getDownloads() {
		return downloads;
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
	public int getCategoryLevel1() {
		return categoryLevel1;
	}
	public int getCategoryLevel2() {
		return categoryLevel2;
	}
	public String getLogoPicPath() {
		return logoPicPath;
	}
	public String getLogoLocPath() {
		return logoLocPath;
	}
	public int getVersionId() {
		return versionId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}
	public void setAPKName(String aPKName) {
		APKName = aPKName;
	}
	public void setSupportROM(String supportROM) {
		this.supportROM = supportROM;
	}
	public void setInterfaceLanguage(String interfaceLanguage) {
		this.interfaceLanguage = interfaceLanguage;
	}
	public void setSoftwareSize(double softwareSize) {
		this.softwareSize = softwareSize;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public void setDevId(int devId) {
		this.devId = devId;
	}
	public void setAppInfo(String appInfo) {
		this.appInfo = appInfo;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setOnSaleDate(Date onSaleDate) {
		this.onSaleDate = onSaleDate;
	}
	public void setOffSaleDate(Date offSaleDate) {
		this.offSaleDate = offSaleDate;
	}
	public void setFlatformId(int flatformId) {
		this.flatformId = flatformId;
	}
	public void setCategoryLevel3(int categoryLevel3) {
		this.categoryLevel3 = categoryLevel3;
	}
	public void setDownloads(int downloads) {
		this.downloads = downloads;
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
	public void setCategoryLevel1(int categoryLevel1) {
		this.categoryLevel1 = categoryLevel1;
	}
	public void setCategoryLevel2(int categoryLevel2) {
		this.categoryLevel2 = categoryLevel2;
	}
	public void setLogoPicPath(String logoPicPath) {
		this.logoPicPath = logoPicPath;
	}
	public void setLogoLocPath(String logoLocPath) {
		this.logoLocPath = logoLocPath;
	}
	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}
}
