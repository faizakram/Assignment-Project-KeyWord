package com.assignment.web.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.web.dto.PublicationDTO;
import com.assignment.web.dto.ResponseJson;
import com.assignment.web.dto.SearchDTO;
import com.assignment.web.service.PublicationService;
import com.assignment.web.utils.CommonConstant;

/**
 * 
 * Publication Control
 *
 */
@RestController
@RequestMapping(CommonConstant.PUBLICATION_URL)
public class PublicationController {
	@Autowired
	private ResponseJson response;

	@Autowired
	private PublicationService publicationService;
	@Resource(name = CommonConstant.COMMON_MAP_OBJECT)
	private Map<String, Object> model;

	/**
	 * Add Publication
	 * 
	 * @param publicationDTO
	 * @return
	 */
	@PostMapping(CommonConstant.ADD)
	public ResponseJson addPublication(@RequestBody PublicationDTO publicationDTO) {
		response.setResponseDescription(CommonConstant.S0002_SUCCESS_DESCRIPTION);
		model.put(CommonConstant.ID, publicationService.addPublication(publicationDTO));
		response.setResponse(model);
		return response;
	}

	/**
	 * Update Publication
	 * 
	 * @param publicationDTO
	 * @return
	 */
	@PutMapping(CommonConstant.UPDATE)
	public ResponseJson updatePublication(@RequestBody PublicationDTO publicationDTO) {
		response.setResponseDescription(CommonConstant.S0003_SUCCESS_DESCRIPTION);
		model.put(CommonConstant.ID, publicationService.updatePublication(publicationDTO));
		response.setResponse(model);
		return response;
	}

	/**
	 * Get Publication
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(CommonConstant.COMMON_ID)
	public ResponseJson getPublication(@PathVariable Long id) {
		response.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		model.put(CommonConstant.RESULTS, publicationService.getPublication(id));
		response.setResponse(model);
		return response;
	}

	/**
	 * Get Publications
	 * 
	 * @return
	 */
	@GetMapping(CommonConstant.LIST)
	public ResponseJson getPublications() {
		response.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		model.put(CommonConstant.RESULTS, publicationService.getPublications());
		response.setResponse(model);
		return response;
	}
	/**
	 * Delete PUblication
	 * @param id
	 * @return
	 */
	@DeleteMapping(CommonConstant.COMMON_ID)
	public ResponseJson delete(@PathVariable Long id) {
		response.setResponseDescription(CommonConstant.S0004_SUCCESS_DESCRIPTION);
		model.put(CommonConstant.ID, publicationService.delete(id));
		response.setResponse(model);
		return response;
	}
	/**
	 * Search Filter on Publications
	 * @param searchDTO
	 * @return
	 */
	@GetMapping(CommonConstant.SEARCH)
	public ResponseJson searchPublications(SearchDTO searchDTO) {
		response.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		model.put(CommonConstant.RESULTS, publicationService.searchPublications(searchDTO));
		response.setResponse(model);
		return response;
	}
}
