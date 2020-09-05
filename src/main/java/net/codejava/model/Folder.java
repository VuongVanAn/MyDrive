package net.codejava.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "folder")
public class Folder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(name = "createddate")
	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name = "userid", insertable = false, updatable = false)
	private User user;
	
	private Long userid;
	
	@OneToMany(mappedBy = "folder")
    private List<SharingFolder> sharingFolderList;
	
	@OneToMany(mappedBy = "folder")
    private List<Content> contentList;

	@OneToMany(mappedBy = "folder")
    private List<Note> noteList;
	
	@Column(name = "folderid")
	private Long folderId;
	
	private int status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
