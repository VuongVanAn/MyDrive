package net.codejava.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import net.codejava.dto.ContentDTO;

public interface ContentService {
	
	ContentDTO findByName(String name);

	void saveContent(String url, String name, MultipartFile file, Long id, Long userId);
	
	List<ContentDTO> findByFolderAndUser(Long folderId, Long userId);
	
	List<ContentDTO> findSharingContentByUser(Long id);
	
	List<ContentDTO> findByFolderId(Long folderId);

}
