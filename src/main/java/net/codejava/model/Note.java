package net.codejava.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "note")
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TEXT")
	private String note;

	@Column
	private String date;
	
	@ManyToOne
	@JoinColumn(name = "contentid", insertable = false, updatable = false)
	private Content content;
	
	private Long contentid;
	
	@ManyToOne
	@JoinColumn(name = "folderid", insertable = false, updatable = false)
	private Folder folder;
	
	private Long folderid;
	
	@ManyToOne
	@JoinColumn(name = "userid", insertable = false, updatable = false)
	private User user;
	
	private Long userid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

}
