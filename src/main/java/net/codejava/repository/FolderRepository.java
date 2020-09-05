package net.codejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.codejava.model.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {
	
	List<Folder> findByFolderId(Long folderId);
	
	@Query("SELECT e FROM Folder e WHERE e.folderId = ?1 AND e.userid = ?2")
	List<Folder> findByFolderIdAndUserid(Long folderId, Long userId);
	
	@Query("SELECT e FROM Folder e WHERE e.userid = ?1 AND e.status = ?2")
	List<Folder> findAllByUserAndStatus(Long userId, int status);
	
}
