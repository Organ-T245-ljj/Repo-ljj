package pojo;

import java.util.Date;

public class AppInfo {
	private int id;//主键id
	private String softwareName;//软件名称
	private String APKName;//APK名称（唯一）
	private String supportROM;//支持ROM
	private String interfaceLanguage;//界面语言
	private double softwareSize;//软件大小（单位：M）
	private Date updateDate;//更新日期
	private int devId;//开发者id（来源于：dev_user表的开发者id）
	private String appInfo;//应用简介
	private int status;//状态（来源于：data_dictionary，1 待审核 2 审核通过 3 审核不通过 4 已上架 5 已下架）
	private Date onSaleDate;//上架时间
	private Date offSaleDate;//下架时间
	private int flatformId;//所属平台（来源于：data_dictionary，1 手机 2 平板 3 通用）
	
	private int downloads;//下载量（单位：次）
	private int createdBy;//创建者（来源于dev_user开发者信息表的用户id）
	private Date creationDate;//创建时间
	private int modifyBy;//更新者（来源于dev_user开发者信息表的用户id）
	private Date modifyDate;//最新更新时间
	private int categoryLevel1;//所属一级分类（来源于：data_dictionary）
	private int categoryLevel2;//所属二级分类（来源于：data_dictionary）
	private int categoryLevel3;//所属三级分类（来源于：data_dictionary）
	private String logoPicPath;//LOGO图片url路径
	private String logoLocPath;//LOGO图片的服务器存储路径
	private int versionId;//最新的版本id
	private String versionNo;//版本值

	private String statusName;//状态值
	private String flatformName;//所属平台值
	private String categoryLevel1Name;//所属1级分类值
	private String categoryLeve21Name;//所属1级分类值
	private String categoryLeve31Name;//所属1级分类值
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
