package net.codejava.service;

import java.util.List;

import net.codejava.dto.FolderDTO;

public interface FolderService {
	
	List<FolderDTO> findByFolderIdAndUserId(Long folderId, Long userId);
	
	List<FolderDTO> findSharingFolderByUser(Long id);
	
	List<FolderDTO> findByFolderId(Long folderId);

}
