package com.ikth.apps.msreserve.reservation.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="FILE_INFO")
public class FileInfo {

	@Id
	private long id;
	
	private String fileName;
	
	private String saveFileName;
	
	private String contentType;
	
	private boolean deleteFlag;
	
	private String createDate;
	
	private String modifyDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
}
