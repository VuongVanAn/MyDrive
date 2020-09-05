package net.codejava.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@Column(name = "username")
    private String userName;

    private String password;
    
    @Column(name = "fullname")
    private String fullName;
    
    private int permission;
    
    @Column(name = "createddate")
    private String createdDate;
    
    @OneToMany(mappedBy = "user")
    private List<Folder> folderList;
    
    @OneToMany(mappedBy = "user")
    private List<SharingFolder> sharingFolderList;
    
    @OneToMany(mappedBy = "user")
    private List<SharingContent> sharingContentList;
    
    @OneToMany(mappedBy = "user")
    private List<Note> noteList;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
