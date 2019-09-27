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

import com.assignment.web.dto.CommonDTO;
import com.assignment.web.dto.ResponseJson;
import com.assignment.web.service.AuthorService;
import com.assignment.web.utils.CommonConstant;
/**
 * 
 * Author Controller
 *
 */
@RestController
@RequestMapping(CommonConstant.AUTH_URL)
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@Autowired
	private ResponseJson response;

	@Resource(name = CommonConstant.COMMON_MAP_OBJECT)
	private Map<String, Object> model;
	/**
	 * Add Author
	 * @param author
	 * @return
	 */
	@PostMapping(CommonConstant.ADD)
	public ResponseJson addAuthor(@RequestBody CommonDTO author) {
		response.setResponseDescription(CommonConstant.S0002_SUCCESS_DESCRIPTION);
		model.put(CommonConstant.ID, authorService.addAuthor(author));
		response.setResponse(model);
		return response;
	}
	/**
	 * Update Author
	 * @param author
	 * @return
	 */
	@PutMapping(CommonConstant.UPDATE)
	public ResponseJson updateAuthor(@RequestBody CommonDTO author) {
		response.setResponseDescription(CommonConstant.S0003_SUCCESS_DESCRIPTION);
		model.put(CommonConstant.ID, authorService.updateAuthor(author));
		response.setResponse(model);
		return response;
	}
	/**
	 * Author List
	 * @return
	 */
	@GetMapping(CommonConstant.LIST)
	public ResponseJson authors() {
		response.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		model.put(CommonConstant.RESULTS, authorService.authors());
		response.setResponse(model);
		return response;
	}
	/**
	 * Author By Id
	 * @param id
	 * @return
	 */
	@GetMapping(CommonConstant.COMMON_ID)
	public ResponseJson getAuthor(@PathVariable Long id) {
		response.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		model.put(CommonConstant.RESULTS, authorService.getAuthor(id));
		response.setResponse(model);
		return response;
	}
	/**
	 * Delete Author By Id
	 * @param id
	 * @return
	 */
	@DeleteMapping(CommonConstant.COMMON_ID)
	public ResponseJson delete(@PathVariable Long id) {
		response.setResponseDescription(CommonConstant.S0004_SUCCESS_DESCRIPTION);
		model.put(CommonConstant.ID, authorService.delete(id));
		response.setResponse(model);
		return response;
	}
}
