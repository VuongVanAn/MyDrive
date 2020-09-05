package net.codejava.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sharingcontent")
public class SharingContent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "userid", insertable = false, updatable = false)
	private User user;
	
	private Long userid;
	
	@ManyToOne
	@JoinColumn(name = "contentid", insertable = false, updatable = false)
	private Content content;
	
	private Long contentid;
	
	private Long folderid;
	
	@Column(name = "createddate")
	private Date createdDate;
	
	private int status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getContentid() {
		return contentid;
	}

	public void setContentid(Long contentid) {
		this.contentid = contentid;
	}

	public Long getFolderid() {
		return folderid;
	}

	public void setFolderid(Long folderid) {
		this.folderid = folderid;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
