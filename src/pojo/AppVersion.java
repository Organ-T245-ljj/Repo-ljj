package pojo;

import java.util.Date;

public class AppVersion {
	private int id;//主键id
	private int appId;//appId（来源于：app_info表的主键id）
	private String versionNo;//版本号
	private String versionInfo;//版本介绍
	private int publishStatus;//发布状态（来源于：data_dictionary，1 不发布 2 已发布 3 预发布）
	private String downloadLink;//下载链接
	private double versionSize;//版本大小（单位：M）
	private int createdBy;//创建者（来源于dev_user开发者信息表的用户id）
	private Date creationDate;//创建时间
	private int modifyBy;//更新者（来源于dev_user开发者信息表的用户id）
	private Date modifyDate;//最新更新时间
	private String apkLocPath;//apk文件的服务器存储路径
	private String apkFileName;//上传的apk文件名称
	private String appName;//app名称
	private String publishStatusName;//发布状态
	private int falg;//用于判断
	
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
