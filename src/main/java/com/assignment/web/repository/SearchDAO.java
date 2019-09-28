package com.assignment.web.repository;

import java.util.List;

import com.assignment.web.dto.SearchDTO;
import com.assignment.web.model.Publication;

public interface SearchDAO {

	List<Publication> searchPublications(SearchDTO searchDTO);

	Publication save(Publication publication);

}
