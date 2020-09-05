package net.codejava.service;

import java.util.List;

import net.codejava.dto.ContentDTO;
import net.codejava.dto.FolderDTO;
import net.codejava.dto.RemainDTO;

public interface RemainService {
	
	List<RemainDTO> findAllByUserId(Long userId);
	
	List<FolderDTO> findAllFolderTrash(Long userId, int status);
	
	List<ContentDTO> findAllContentTrash(Long userId, int status);
	
	String restoreFolder(Long folderId);
	
	String restoreContent(Long contentId);

}
