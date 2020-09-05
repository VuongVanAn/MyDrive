package net.codejava.service;

import java.util.List;

import net.codejava.dto.SharingContentDTO;

public interface SharingContentService {
	
	List<SharingContentDTO> findAllByFolderIdAndUser(Long folderId, Long userId);
	
	List<SharingContentDTO> findAllByUserId(Long userId);
	
}
