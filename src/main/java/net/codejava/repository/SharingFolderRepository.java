package net.codejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.codejava.model.SharingFolder;

public interface SharingFolderRepository extends JpaRepository<SharingFolder, Long> {
	
	List<SharingFolder> findByUserid(Long userId);
	
	List<SharingFolder> findByFolderid(Long folderId);
	
	List<SharingFolder> findByFolderParentId(Long folderId);
	
	@Query("SELECT e FROM SharingFolder e WHERE e.folderParentId = ?1 AND e.userid = ?2")
	List<SharingFolder> findByFolderIdAndUserid(Long folderId, Long userId);

}
