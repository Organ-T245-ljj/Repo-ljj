package pojo;

import java.util.Date;

public class AppVersion {
	private int id;//����id
	private int appId;//appId����Դ�ڣ�app_info�������id��
	private String versionNo;//�汾��
	private String versionInfo;//�汾����
	private int publishStatus;//����״̬����Դ�ڣ�data_dictionary��1 ������ 2 �ѷ��� 3 Ԥ������
	private String downloadLink;//��������
	private double versionSize;//�汾��С����λ��M��
	private int createdBy;//�����ߣ���Դ��dev_user��������Ϣ����û�id��
	private Date creationDate;//����ʱ��
	private int modifyBy;//�����ߣ���Դ��dev_user��������Ϣ����û�id��
	private Date modifyDate;//���¸���ʱ��
	private String apkLocPath;//apk�ļ��ķ������洢·��
	private String apkFileName;//�ϴ���apk�ļ�����
	private String appName;//app����
	private String publishStatusName;//����״̬
	private int falg;//�����ж�
	
	public int getFalg() {
		return falg;
	}
	
	public void setFalg(int falg) {
		this.falg = falg;
	}
	public String getPublishStatusName() {
		return publishStatusName;
	}
	public void setPublishStatusName(String publishStatusName) {
		this.publishStatusName = publishStatusName;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public int getId() {
		return id;
	}
	public int getAppId() {
		return appId;
	}
	public String getVersionNo() {
		return versionNo;
	}
	public String getVersionInfo() {
		return versionInfo;
	}
	public int getPublishStatus() {
		return publishStatus;
	}
	public String getDownloadLink() {
		return downloadLink;
	}
	public double getVersionSize() {
		return versionSize;
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
	public String getApkLocPath() {
		return apkLocPath;
	}
	public String getApkFileName() {
		return apkFileName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public void setVersionInfo(String versionInfo) {
		this.versionInfo = versionInfo;
	}
	public void setPublishStatus(int publishStatus) {
		this.publishStatus = publishStatus;
	}
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
	public void setVersionSize(double versionSize) {
		this.versionSize = versionSize;
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
	public void setApkLocPath(String apkLocPath) {
		this.apkLocPath = apkLocPath;
	}
	public void setApkFileName(String apkFileName) {
		this.apkFileName = apkFileName;
	}
}
