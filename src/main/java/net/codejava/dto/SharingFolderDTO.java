package net.codejava.dto;

import java.util.Date;

public class SharingFolderDTO {
	
    private Long id;
	
	private Long userId;
	
	private Long folderId;
	
	private Long folderParentId;
	
	private String date;
	
	private Date createdDate;
	
	private String name;
	
	private int status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public Long getFolderParentId() {
		return folderParentId;
	}

	public void setFolderParentId(Long folderParentId) {
		this.folderParentId = folderParentId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
