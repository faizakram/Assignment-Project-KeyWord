package com.assignment.web.service;

import java.util.List;

import com.assignment.web.dto.CommonDTO;
import com.assignment.web.model.Author;
/*
 * Author Service
 */
public interface AuthorService {
	/**
	 * Add Author
	 * @param author
	 * @return
	 */
	Long addAuthor(CommonDTO author);
	/**
	 * Update Author
	 * @param author
	 * @return
	 */
	Long updateAuthor(CommonDTO author);
	/**
	 * Get Author List
	 * @return
	 */
	List<CommonDTO> authors();
	/**
	 * Delete Author
	 * @param id
	 * @return
	 */
	Long delete(Long id);
	/**
	 * Get Author By Id
	 * @param id
	 * @return
	 */
	CommonDTO getAuthor(Long id);
	/**
	 * Get Author By Id
	 * @param id
	 * @return
	 */
	Author getAuthorById(Long id);

}
