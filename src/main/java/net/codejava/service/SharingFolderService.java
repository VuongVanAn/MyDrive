package net.codejava.service;

import java.util.List;

import net.codejava.dto.SharingFolderDTO;

public interface SharingFolderService {
	
	List<SharingFolderDTO> findAllByFolderAndUser(Long folderId, Long userId);
	
	List<SharingFolderDTO> findAllByUserId(Long userId);
	
	List<SharingFolderDTO> findAllByFolderId(Long folderId);
	
}
