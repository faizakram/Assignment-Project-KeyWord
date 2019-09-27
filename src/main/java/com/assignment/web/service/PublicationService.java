package com.assignment.web.service;

import java.util.List;

import com.assignment.web.dto.PublicationDTO;
import com.assignment.web.dto.SearchDTO;
/**
 * 
 * Publication Service
 *
 */
public interface PublicationService {
	/**
	 * Add Publication
	 * @param publicationDTO
	 * @return
	 */
	Long addPublication(PublicationDTO publicationDTO);
	/**
	 * Update Publication
	 * @param publicationDTO
	 * @return
	 */
	Long updatePublication(PublicationDTO publicationDTO);
	/**
	 * Get Publication
	 * @param id
	 * @return
	 */
	PublicationDTO getPublication(Long id);
	/**
	 * Get Publications
	 * @return
	 */
	List<PublicationDTO> getPublications();
	/**
	 * Delete Publication 
	 * @param id
	 * @return
	 */
	Long delete(Long id);
	/**
	 * Search Publications
	 * @param searchDTO
	 * @return
	 */
	List<PublicationDTO> searchPublications(SearchDTO searchDTO);

}
