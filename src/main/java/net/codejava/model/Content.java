package net.codejava.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "content")
public class Content {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column
	private String url;
	
	@Column(name = "createddate")
	private Date createdDate;
	
	private String format;
	
	@ManyToOne
	@JoinColumn(name = "folderid", insertable = false, updatable = false)
	private Folder folder;
	
	private Long folderid;
	
	@Column(name = "userid")
	private Long userId;
	
	@Column(name = "docfile")
    @Lob
    private byte[] docFile;
	
	@OneToMany(mappedBy = "content")
	private List<SharingContent> sharingContentList;
	
	@OneToMany(mappedBy = "content")
	private List<Note> noteList;
	
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Long getFolderid() {
		return folderid;
	}

	public void setFolderid(Long folderid) {
		this.folderid = folderid;
	}

	public byte[] getDocFile() {
		return docFile;
	}

	public void setDocFile(byte[] docFile) {
		this.docFile = docFile;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
