package net.codejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.codejava.model.Content;

public interface ContentRepository extends JpaRepository<Content, Long> {
	
	Content findByName(String name);
	
	List<Content> findByFolderid(Long id);
	
	List<Content> findByUserId(Long id);
	
	@Query("SELECT e FROM Content e WHERE e.folderid = ?1 AND e.userId = ?2")
	List<Content> findByFolderIdAndUserId(Long folderId, Long userId);
	
	@Query("SELECT e FROM Content e WHERE e.userId = ?1 AND e.status = ?2")
	List<Content> findAllByUserAndStatus(Long userId, int status);

}
