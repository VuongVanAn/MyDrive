package net.codejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.codejava.model.SharingContent;

public interface SharingContentRepository extends JpaRepository<SharingContent, Long> {
	
	List<SharingContent> findByUserid(Long userId);
	
	List<SharingContent> findByFolderid(Long folderId);
	
	@Query("SELECT e FROM SharingContent e WHERE e.folderid = ?1 AND e.userid = ?2")
	List<SharingContent> findByFolderIdAndUserid(Long folderId, Long userId);

}
